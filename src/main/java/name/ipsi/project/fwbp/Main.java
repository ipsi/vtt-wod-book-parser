package name.ipsi.project.fwbp;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20Extractor;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20FoundryConverter;
import name.ipsi.project.fwbp.dtrpg.Downloader;
import name.ipsi.project.fwbp.foundry.ModuleGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        configureLogging();
        var scanner = new Scanner(System.in);
        var token = System.getenv("DTRPG_TOKEN");
        if (token == null) {
            log.debug("DTRPG_TOKEN env var not found - requesting");
            System.out.println("Please enter your DTRPG Application Key (you can find or create one here: https://www.drivethrurpg.com/account_edit.php -> Application Keys)");
            token = scanner.next();
        } else {
            log.debug("DTRPG_TOKEN env var found");
        }
        System.out.println("Please select a book to convert:");
        System.out.printf("(%d) %s%n", 1, Werewolf20Extractor.BOOK_NAME);
        var selection = scanner.nextInt();
        switch (selection) {
            case 1:
//                var doc = new PdfDocument(new PdfReader("/Volumes/books/Roleplaying/Werewolf the Apocalypse/Werewolf the Apocalypse 20th Anniversary Edition.pdf"));
                log.debug("Downloading file");
                var doc = Downloader.downloadFile(Werewolf20Extractor.BOOK_ID, token);
                log.debug("File downloaded");

                log.debug("Extracting data from PDF");
                var w20 = new Werewolf20Extractor(new PdfDocumentContentParser(doc));
                var rawBookEntries = w20.process();
                log.debug("Extracted data from PDF, creating {} book entries", rawBookEntries.size());
                var foundryDocs = new Werewolf20FoundryConverter().process(rawBookEntries);
                log.debug("Converted entries to Foundry docs, creating {} documents", foundryDocs.size());

                log.debug("Generating module");
                var moduleGenerator = new ModuleGenerator(
                        new ObjectMapper(),
                        Werewolf20FoundryConverter.MODULE_NAME,
                        Werewolf20Extractor.BOOK_NAME,
                        Werewolf20Extractor.BOOK_NAME + " - Automatically extracted from PDF",
                        "0.0.1",
                        "ipsi",
                        "9",
                        "9",
                        foundryDocs
                );
                moduleGenerator.createModule();
                log.info("Module {} generated at {}", Werewolf20FoundryConverter.MODULE_NAME, moduleGenerator.getOutputPath().toAbsolutePath());

                break;
            default:
                System.out.println("Unknown book " + selection);
        }
    }

    private static void configureLogging() {
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
    }
}
