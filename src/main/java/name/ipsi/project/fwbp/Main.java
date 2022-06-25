package name.ipsi.project.fwbp;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20Extractor;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20FoundryConverter;
import name.ipsi.project.fwbp.foundry.*;
import name.ipsi.project.fwbp.foundry.Module;
import name.ipsi.project.fwbp.foundry.Packs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                var doc = new PdfDocument(new PdfReader("/Volumes/books/Roleplaying/Werewolf the Apocalypse/Werewolf the Apocalypse 20th Anniversary Edition.pdf"));
//                var doc = Downloader.downloadFile(Werewolf20Extractor.BOOK_ID, token);
                var w20 = new Werewolf20Extractor(new PdfDocumentContentParser(doc));
                var entries = w20.process();
                var foundryDocs = new Werewolf20FoundryConverter().process(entries);
                var objectMapper = new ObjectMapper();
                var packs = new HashMap<Packs, List<String>>();
                for (var fdoc : foundryDocs) {
                    List<String> list = packs.computeIfAbsent(fdoc.pack(), o -> new ArrayList<>());
                    list.add(objectMapper.writeValueAsString(fdoc));
                }

                var modulePacks = new ArrayList<ModulePacks>();
                var basePath = Path.of("target", Werewolf20FoundryConverter.MODULE_NAME);
                Files.createDirectories(basePath.resolve("packs"));

                var journalsPath = "./packs/breeds.db";
                var journalFile = basePath.resolve(journalsPath);

                Files.createFile(journalFile);
                for (var s : packs.get(Packs.Breeds)) {
                    Files.writeString(journalFile, s + "\n", StandardOpenOption.APPEND);
                }
                modulePacks.add(new ModulePacks(
                        "journals",
                        "Breeds (W20)",
                        journalsPath,
                        Werewolf20FoundryConverter.MODULE_NAME,
                        DocumentTypes.JOURNAL_ENTRY
                ));

                var itemsPath = "./packs/gifts.db";
                var itemsFile = basePath.resolve(itemsPath);

                Files.createFile(itemsFile);
                for (var s : packs.get(Packs.Gifts)) {
                    Files.writeString(itemsFile, s + "\n", StandardOpenOption.APPEND);
                }
                modulePacks.add(new ModulePacks(
                        "gifts",
                        "Gifts (W20)",
                        itemsPath,
                        Werewolf20FoundryConverter.MODULE_NAME,
                        DocumentTypes.ITEM
                ));

                var weaponsPath = "./packs/weapons.db";
                var weaponsFile = basePath.resolve(weaponsPath);

                Files.createFile(weaponsFile);
                for (var s : packs.get(Packs.Weapons)) {
                    Files.writeString(weaponsFile, s + "\n", StandardOpenOption.APPEND);
                }
                modulePacks.add(new ModulePacks(
                        "weapons",
                        "Weapons (W20)",
                        weaponsPath,
                        Werewolf20FoundryConverter.MODULE_NAME,
                        DocumentTypes.ITEM
                ));

                var module = new Module(
                        Werewolf20FoundryConverter.MODULE_NAME,
                        Werewolf20Extractor.BOOK_NAME,
                        Werewolf20Extractor.BOOK_NAME + " - Automatically extracted from PDF",
                        "0.0.1",
                        "ipsi",
                        "9",
                        "9",
                        modulePacks
                );

                Files.writeString(basePath.resolve("module.json"), objectMapper.writer(new DefaultPrettyPrinter()).writeValueAsString(module));

                break;
            default:
                System.out.println("Unknown book " + selection);
        }
    }
}
