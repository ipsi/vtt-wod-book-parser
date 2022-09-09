package name.ipsi.project.fwbp;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20Extractor;
import name.ipsi.project.fwbp.dtrpg.Downloader;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;
import name.ipsi.project.fwbp.foundry.core.ModuleGenerator;
import name.ipsi.project.fwbp.foundry.wod.werewolf.Werewolf20FoundryConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        configureLogging();

        if (args.length == 0) {
            Gui.launch(Gui.class, args);
            return;
        } else if (args[0].equals("--adventure")) {
            log.info("Building adventure with CLI GUI (primarily intended for automated testing)");
        } else {
            log.warn("Unknown arg {} - only accepts --adventure or no args", args[0]);
            System.exit(1);
        }

        var scanner = new Scanner(System.in);
        var token = System.getenv("DTRPG_TOKEN");
        if (token == null) {
            log.debug("DTRPG_TOKEN env var not found - requesting");
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
//                var doc = new PdfDocument(new PdfReader("/Volumes/books/Roleplaying/Werewolf the Apocalypse/Werewolf the Apocalypse 20th Anniversary Edition.pdf"));
                log.debug("Downloading file");
                var doc = Downloader.downloadFile(Werewolf20Extractor.BOOK_ID, token);
                log.debug("File downloaded");

                var images = new HashMap<String, byte[]>();

                for (int i = 1; i <= doc.getNumberOfPages(); i++) {
                    var p = doc.getPage(i);
                    for (var rn : Arrays.asList(PdfName.ExtGState, PdfName.Pattern, PdfName.XObject)) {
                        var r = p.getResources().getResource(rn);
                        if (r != null) {
                            for (var a : r.entrySet()) {
                                if (a.getValue() instanceof PdfStream pdfStream) {
                                    images.put(String.format("%d:%s", i, a.getKey().getValue()), pdfStream.getBytes());
                                }
                            }
                        }
                    }
                }

                log.debug("Extracting data from PDF");
                var w20 = new Werewolf20Extractor(new PdfDocumentContentParser(doc));
                var rawBookEntries = w20.process();
                log.debug("Extracted data from PDF, creating {} book entries", rawBookEntries.size());

                var moduleGenerator = new ModuleGenerator(
                        new ObjectMapper(),
                        Werewolf20FoundryConverter.MODULE_NAME,
                        Werewolf20Extractor.BOOK_NAME,
                        Werewolf20Extractor.BOOK_NAME + " - Automatically extracted from PDF",
                        "0.0.1",
                        images
                );

                log.debug("Generating Adventure");
                var adventure = new Werewolf20FoundryConverter().processAsAdventure(rawBookEntries);
                log.debug("Generating module");
                moduleGenerator.createModule(adventure);

                log.info("Module {} generated at {}", Werewolf20FoundryConverter.MODULE_NAME, moduleGenerator.getOutputPath().toAbsolutePath());

                break;
            default:
                System.out.println("Unknown book " + selection);
        }

        writeIds();
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

        context.getLogger("com.itextpdf").setLevel(Level.WARN);
        context.getLogger("com.github.jknack.handlebars").setLevel(Level.WARN);
    }

    private static void writeIds() throws IOException {
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
