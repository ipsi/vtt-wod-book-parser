package name.ipsi.project.fwbp;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20Extractor;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20FoundryConverter;
import name.ipsi.project.fwbp.dtrpg.Downloader;
import name.ipsi.project.fwbp.foundry.DocumentTypes;
import name.ipsi.project.fwbp.foundry.Module;
import name.ipsi.project.fwbp.foundry.ModulePacks;
import name.ipsi.project.fwbp.foundry.Packs;

import java.io.IOException;
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
//                var doc = new PdfDocument(new PdfReader("/Volumes/books/Roleplaying/Werewolf the Apocalypse/Werewolf the Apocalypse 20th Anniversary Edition.pdf"));
                var doc = Downloader.downloadFile(Werewolf20Extractor.BOOK_ID, token);
                var w20 = new Werewolf20Extractor(new PdfDocumentContentParser(doc));
                var entries = w20.process();
                var foundryDocs = new Werewolf20FoundryConverter().process(entries);
                var objectMapper = new ObjectMapper();
                var packs = new HashMap<Packs, List<String>>();
                for (var fdoc : foundryDocs) {
                    List<String> list = packs.computeIfAbsent(fdoc.getPack(), o -> new ArrayList<>());
                    list.add(objectMapper.writeValueAsString(fdoc));
                }

                var modulePacks = new ArrayList<ModulePacks>();
                var basePath = Path.of("target", Werewolf20FoundryConverter.MODULE_NAME);
                Files.createDirectories(basePath.resolve("packs"));

                modulePacks.add(createPack(basePath, "Breeds (W20)", "breeds", DocumentTypes.JOURNAL_ENTRY, packs.get(Packs.Breeds)));
                modulePacks.add(createPack(basePath, "Auspices (W20)", "auspices", DocumentTypes.JOURNAL_ENTRY, packs.get(Packs.Auspices)));
                modulePacks.add(createPack(basePath, "Tribes (W20)", "tribes", DocumentTypes.JOURNAL_ENTRY, packs.get(Packs.Tribes)));

                modulePacks.add(createPack(basePath, "Gifts (W20)", "gifts", DocumentTypes.ITEM, packs.get(Packs.Gifts)));

                modulePacks.add(createPack(basePath, "Weapons (W20)", "weapons", DocumentTypes.ITEM, packs.get(Packs.Weapons)));

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

    private static ModulePacks createPack(Path basePath, String label, String packName, DocumentTypes type, List<String> packs) throws IOException {
        var path = String.format("./packs/%s.db", packName);
        var moduleFile = basePath.resolve(path);

        Files.createFile(moduleFile);
        for (var s : packs) {
            Files.writeString(moduleFile, s + "\n", StandardOpenOption.APPEND);
        }
        return new ModulePacks(
                packName,
                label,
                path,
                Werewolf20FoundryConverter.MODULE_NAME,
                type
        );
    }
}
