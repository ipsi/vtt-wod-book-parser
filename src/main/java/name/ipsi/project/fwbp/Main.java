package name.ipsi.project.fwbp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20Extractor;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20FoundryConverter;
import name.ipsi.project.fwbp.dtrpg.Downloader;
import name.ipsi.project.fwbp.foundry.ModuleGenerator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        var token = System.getenv("DTRPG_TOKEN");
        if (token == null) {
            System.out.println("Please enter your DTRPG Application Key (you can find or create one here: https://www.drivethrurpg.com/account_edit.php -> Application Keys)");
            token = scanner.next();
        }
        System.out.println("Please select a book to convert:");
        System.out.printf("(%d) %s%n", 1, Werewolf20Extractor.BOOK_NAME);
        var selection = scanner.nextInt();
        switch (selection) {
            case 1:
//                var doc = new PdfDocument(new PdfReader("/Volumes/books/Roleplaying/Werewolf the Apocalypse/Werewolf the Apocalypse 20th Anniversary Edition.pdf"));
                var doc = Downloader.downloadFile(Werewolf20Extractor.BOOK_ID, token);

                var w20 = new Werewolf20Extractor(new PdfDocumentContentParser(doc));
                var rawBookEntries = w20.process();
                var foundryDocs = new Werewolf20FoundryConverter().process(rawBookEntries);

                new ModuleGenerator(
                        new ObjectMapper(),
                        Werewolf20FoundryConverter.MODULE_NAME,
                        Werewolf20Extractor.BOOK_NAME,
                        Werewolf20Extractor.BOOK_NAME + " - Automatically extracted from PDF",
                        "0.0.1",
                        "ipsi",
                        "9",
                        "9",
                        foundryDocs
                ).createModule();

                break;
            default:
                System.out.println("Unknown book " + selection);
        }
    }
}
