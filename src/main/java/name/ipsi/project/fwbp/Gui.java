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
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20Extractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Gui extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Gui.class);
    public static final String MODULE_VERSION = "0.0.6";

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
        var dtrpgTokenLabel = new Label("DTRPG Application Key:");
        var dtrpgToken = new TextField();
        dtrpgToken.setPrefWidth(300);
        var hyperlink = new Hyperlink("https://www.drivethrurpg.com/account_edit.php");
        hyperlink.setOnAction(event -> getHostServices().showDocument("https://www.drivethrurpg.com/account_edit.php"));
        var dtrpgHelp = new TextFlow(new Text("Please enter your DTRPG Application Key (you can find or create one here: "), hyperlink, new Text(" -> Application Keys)"));
        var foundryDirLabel = new Label("Foundry Directory:");
        var foundryDir = new TextField(foundryDir().resolve("Data").resolve("modules").toAbsolutePath().toString());
        foundryDir.setPrefWidth(400);
        var foundryDirChooserButton = new Button("Choose directory");
        foundryDirChooserButton.setOnAction(event -> {
            var directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose Foundry Directory");
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            var chosenDir = directoryChooser.showDialog(stage);
            foundryDir.setText(chosenDir.toString());
        });
        var button = new Button("Generate Foundry Module");
        button.setOnAction(event -> {
            List<String> errors = new ArrayList<>();
            var selectedBook = bookDropDown.selectionModelProperty().getValue().getSelectedItem();
            var dtrpgTokenText = dtrpgToken.getText();
            if (selectedBook == null || selectedBook.isBlank()) {
                errors.add("You must select a book");
            }

            if (dtrpgTokenText == null || dtrpgTokenText.isBlank()) {
                errors.add("DTRPG Token must be set");
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
                                BookProcessor.processWerewolf20(Path.of(foundryDirText), dtrpgTokenText);
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
                new HBox(15, dtrpgTokenLabel, dtrpgToken),
                new HBox(dtrpgHelp),
                new HBox(15, foundryDirLabel, foundryDir, foundryDirChooserButton),
                new HBox(log),
                new HBox(button)
        );
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add("/css/javafx.css");
        stage.setScene(scene);
        stage.show();
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
