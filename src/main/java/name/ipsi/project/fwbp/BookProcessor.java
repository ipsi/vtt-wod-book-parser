package name.ipsi.project.fwbp;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import name.ipsi.project.fwbp.books.werewolf.Tribes;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20Extractor;
import name.ipsi.project.fwbp.dtrpg.Downloader;
import name.ipsi.project.fwbp.foundry.core.ImageType;
import name.ipsi.project.fwbp.foundry.core.ModuleGenerator;
import name.ipsi.project.fwbp.foundry.core.PdfImage;
import name.ipsi.project.fwbp.foundry.wod.werewolf.Werewolf20FoundryConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BookProcessor {
    public static final String MODULE_VERSION = "0.0.6";

    private static final Logger LOGGER = LoggerFactory.getLogger(BookProcessor.class);

    public static void processWerewolf20(Path modulesDir, Path pdfFile) throws Exception {
        LOGGER.info("Reading PDF file [{}]", pdfFile.toAbsolutePath());
        var doc = new PdfDocument(new PdfReader(pdfFile.toAbsolutePath().toString()));
        LOGGER.info("PDF File read and converted to PdfDocument");

        processWerewolf20(modulesDir, doc);
    }

    public static void processWerewolf20(Path modulesDir, String dtrpgTokenText) throws Exception {
        LOGGER.info("Downloading file");
        var doc = Downloader.downloadFile(Werewolf20Extractor.BOOK_ID, dtrpgTokenText);
        LOGGER.info("File downloaded");

        processWerewolf20(modulesDir, doc);
    }

    public static void processWerewolf20(Path modulesDir, PdfDocument doc) throws Exception {
        var images = extractImagesFromPdf(doc);

        LOGGER.info("Extracting data from PDF");
        PdfDocumentContentParser parser = new PdfDocumentContentParser(doc);
        var foo = parser.processContent(138, new RegexBasedLocationExtractionStrategy(".*\n")).getResultantLocations();
        var w20 = new Werewolf20Extractor(parser);
        var rawBookEntries = w20.process();
        LOGGER.info("Extracted data from PDF, creating {} book entries", rawBookEntries.size());

        LOGGER.info("Generating Adventure");
        var adventure = new Werewolf20FoundryConverter().processAsAdventure(rawBookEntries);

        var pdfImages = new ArrayList<PdfImage>();
        for(var t : Tribes.values()) {
            pdfImages.add(new PdfImage(
                    t.urlName() + "-splash.jpeg",
                    ImageType.OTHER,
                    images.get(String.format("%d:%s", t.imagePage(), t.imageName()))
            ));
        }

        pdfImages.add(PdfImage.createCoverImage(images.get(Werewolf20Extractor.COVER_IMAGE_ID)));

        var moduleGenerator = new ModuleGenerator(
                modulesDir,
                Werewolf20FoundryConverter.CORE_FILE_PREFIX,
                Werewolf20FoundryConverter.MODULE_NAME,
                Werewolf20Extractor.BOOK_NAME,
                Werewolf20Extractor.BOOK_NAME + " - Automatically extracted from PDF",
                MODULE_VERSION,
                pdfImages
        );

        LOGGER.info("Generating module");
        moduleGenerator.createModule(adventure);

        LOGGER.info("Module \"{}\" generated at {}", Werewolf20FoundryConverter.MODULE_NAME, moduleGenerator.getOutputPath().toAbsolutePath());
    }

    private static Map<String, byte[]> extractImagesFromPdf(PdfDocument doc) {
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
        return images;
    }
}
