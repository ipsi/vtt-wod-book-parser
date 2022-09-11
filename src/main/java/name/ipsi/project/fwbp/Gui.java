package name.ipsi.project.fwbp;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Context;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20Extractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Gui extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Gui.class);
    public static final String MODULE_VERSION = "0.0.7";

    @Override
    public void start(Stage stage) throws Exception {
        var log = new TextArea();
        log.setEditable(false);
        log.setPrefRowCount(50);
        log.setPrefColumnCount(200);

        var context = (LoggerContext) LoggerFactory.getILoggerFactory();
        var textAreaAppender = new TextAreaAppender(log, context);
        context.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(textAreaAppender);
        textAreaAppender.start();

        var bookLabel = new Label("Book:");
        var bookDropDown = new ComboBox<>(FXCollections.observableArrayList(Werewolf20Extractor.BOOK_NAME));
        var pdfLocationLabel = new Label("Location of PDF:");
        var pdfLocation = new TextField();
        pdfLocation.setPrefWidth(400);
        var pdfLocationButton = new Button("Select PDF File");
        pdfLocationButton.setOnAction(event -> {
            var fileChooser = new FileChooser();
            fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
            fileChooser.setTitle("Choose PDF for for book");
            var pdfLocationText = pdfLocation.getText();
            if (pdfLocationText != null && !pdfLocationText.isBlank()) {
                try {
                    var existingFile = new File(pdfLocationText);
                    if (existingFile.isDirectory()) {
                        fileChooser.setInitialDirectory(existingFile);
                    } else {
                        fileChooser.setInitialFileName(existingFile.getName());
                        fileChooser.setInitialDirectory(existingFile.getParentFile());
                    }
                } catch (Exception e) {
                    // Invalid directory - use default
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                }
            }
            var chosenFile = fileChooser.showOpenDialog(stage);
            if (chosenFile != null) {
                pdfLocation.setText(chosenFile.toString());
            }
        });
        var foundryDirLabel = new Label("Foundry Directory:");
        var foundryDir = new TextField(foundryDir().resolve("Data").resolve("modules").toAbsolutePath().toString());
        foundryDir.setPrefWidth(400);
        var foundryDirChooserButton = new Button("Choose directory");
        foundryDirChooserButton.setOnAction(event -> {
            var directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose Foundry Directory");
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            var chosenDir = directoryChooser.showDialog(stage);
            if (chosenDir != null) {
                foundryDir.setText(chosenDir.toString());
            }
        });
        var button = new Button("Generate Foundry Module");
        button.setOnAction(event -> {
            List<String> errors = new ArrayList<>();
            var selectedBook = bookDropDown.selectionModelProperty().getValue().getSelectedItem();
            var pdfFile = pdfLocation.getText();
            if (selectedBook == null || selectedBook.isBlank()) {
                errors.add("You must select a book");
            }

            try {
                if (pdfFile == null || pdfFile.isBlank()) {
                    errors.add("Path to PDF File must be set");
                } else if (!Files.isRegularFile(Path.of(pdfFile))) {
                    errors.add("PDF file does not exist at path " + pdfFile);
                }
            } catch (InvalidPathException e) {
                errors.add("Invalid PDF file path " + pdfFile);
            }

            if (!errors.isEmpty()) {
                var errStage = new Stage();
                errStage.initModality(Modality.APPLICATION_MODAL);
                var closeButton = new Button("OK");
                closeButton.setOnAction(event1 -> errStage.close());
                var errWrapper = new VBox(10, errors.stream().map(Text::new).toArray(Text[]::new));
                var box = new VBox(30, errWrapper, closeButton);
                box.setPadding(new Insets(20));
                errStage.setScene(new Scene(box));
                errStage.showAndWait();
            } else {
                log.setText("");
                var foundryDirText = foundryDir.getText();
                new Thread(() -> {
                    try {
                        switch (selectedBook) {
                            case Werewolf20Extractor.BOOK_NAME:
                                BookProcessor.processWerewolf20(Path.of(foundryDirText), Path.of(pdfFile));
                                Platform.runLater(() -> {
                                    var successDialog = createMessageDialog("Module Successfully Created");
                                    successDialog.showAndWait();
                                });
                                break;
                            default:
                                // Should be impossible
                                LOGGER.error("Unknown book {}", selectedBook);
                        }
                    } catch (Exception e) {
                        LOGGER.error("Unable to generate Foundry Module", e);
                    }
                }).start();
            }
        });

        VBox root = new VBox(10,
                new HBox(15, bookLabel, bookDropDown),
                new HBox(15, pdfLocationLabel, pdfLocation, pdfLocationButton),
                new HBox(15, foundryDirLabel, foundryDir, foundryDirChooserButton),
                new HBox(log),
                new HBox(button)
        );
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 480);
        scene.getStylesheets().add("/css/javafx.css");
        stage.setScene(scene);
        stage.show();
    }

    private Stage createMessageDialog(String text) {
        var stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        var closeButton = new Button("OK");
        closeButton.setOnAction(event -> stage.close());
        var wrapper = new VBox(10, new Text(text));
        var box = new VBox(30, wrapper, closeButton);
        box.setPadding(new Insets(20));
        stage.setScene(new Scene(box));
        return stage;
    }

    private Path foundryDir() {
        var os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return Path.of(System.getenv("LOCALAPPDATA"), "FoundryVTT");
        } else if (os.contains("mac")) {
            return Path.of(System.getProperty("user.home"), "Library", "Application Support", "FoundryVTT");
        } else {
            var path1 = Path.of("/home", System.getProperty("user.id"), ".local", "share", "FoundryVTT");
            var path2 = Path.of("/home", System.getProperty("user.id"), "FoundryVTT");
            var path3 = Path.of("/local", "FoundryVTT");
            if (Files.isDirectory(path1)) {
                return path1;
            } else if (Files.isDirectory(path2)) {
                return path2;
            } else if (Files.isDirectory(path3)) {
                return path3;
            } else {
                return path1;
            }
        }
    }

    private static class TextAreaAppender extends AppenderBase<ILoggingEvent> {

        private final PatternLayoutEncoder encoder;
        private final TextArea log;

        public TextAreaAppender(TextArea log, Context context) {
            this.log = log;
            encoder = new PatternLayoutEncoder();
            encoder.setCharset(StandardCharsets.UTF_8);
            encoder.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
            encoder.setContext(context);
            encoder.start();
        }

        @Override
        protected void append(ILoggingEvent eventObject) {
            if (eventObject.getLevel().isGreaterOrEqual(Level.INFO)) {
                Platform.runLater(() -> log.appendText(new String(encoder.encode(eventObject), StandardCharsets.UTF_8)));
            }
        }
    }
}
