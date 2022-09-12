package name.ipsi.project.fwbp;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20Extractor;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        configureLogging();

        if (args.length == 0) {
            Gui.launch(Gui.class, args);
            return;
        } else if (args[0].equals("--preprocess")) {
            Preprocessor.process(Path.of("raw_text/w20.json"), Werewolf20Extractor.BOOK_ID);
            return;
        } else if (args[0].equals("--adventure")) {
            log.info("Building adventure with CLI GUI (primarily intended for automated testing)");
        } else {
            log.warn("Unknown arg {} - only accepts --adventure or no args", args[0]);
            System.exit(1);
        }

        var filePath = System.getenv("PDF_FILE_PATH");
        var scanner = new Scanner(System.in);
        var token = System.getenv("DTRPG_TOKEN");
        if (token == null && filePath == null) {
            log.debug("DTRPG_TOKEN env var not found and no file provided - requesting");
            System.out.println("Please enter your DTRPG Application Key (you can find or create one here: https://www.drivethrurpg.com/account_edit.php -> Application Keys)");
            token = scanner.next();
            scanner.close();
        } else {
            log.debug("DTRPG_TOKEN env var found");
        }

        System.out.println("Please select a book to convert:");
        System.out.printf("(%d) %s%n", 1, Werewolf20Extractor.BOOK_NAME);
        var selection = scanner.nextInt();
        switch (selection) {
            case 1:
                var modulesDir = Path.of("modules");
                if (filePath == null) {
                    BookProcessor.processWerewolf20(modulesDir, token);
                } else {
                    BookProcessor.processWerewolf20(modulesDir, Path.of(filePath));
                }
                break;
            default:
                System.out.println("Unknown book " + selection);
        }

        writeIds();
    }

    static void configureLogging() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        String logLevel = System.getenv("LOG_LEVEL");
        if (logLevel != null) {
            Level level = switch (logLevel) {
                case "trace" -> Level.TRACE;
                case "debug" -> Level.DEBUG;
                case "info" -> Level.INFO;
                case "warn" -> Level.WARN;
                case "error" -> Level.ERROR;
                default -> {
                    System.out.println("Unknown log level " + logLevel);
                    yield Level.INFO;
                }
            };
            context.getLogger(Logger.ROOT_LOGGER_NAME).setLevel(level);
        } else {
            context.getLogger(Logger.ROOT_LOGGER_NAME).setLevel(Level.INFO);
        }

        context.getLogger("com.itextpdf").setLevel(Level.WARN);
        context.getLogger("com.github.jknack.handlebars").setLevel(Level.WARN);
    }

    static void writeIds() throws IOException {
        var ids = FoundryUtils.getIds();
        var outputFile = Path.of("src", "main", "resources", "id-list.txt");
        if (Files.exists(outputFile)) {
            var fileContents = ids.entrySet().stream()
                    .map(e -> String.format("%s,%s", e.getKey(), e.getValue()))
                    .collect(Collectors.joining("\n"));
            Files.writeString(outputFile, fileContents, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } else {
            log.debug("Cannot find IDs file [{}] - assuming not running in dev environment", outputFile);
        }
    }
}
