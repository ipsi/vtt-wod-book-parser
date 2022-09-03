package name.ipsi.project.fwbp.foundry;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20FoundryConverter;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public final class ModuleGenerator {
    private final ObjectMapper objectMapper;
    private final Path outputPath;
    private final String name;
    private final String title;
    private final String description;
    private final String version;
    private final String author;
    private final String minVersion;
    private final String compatibleVersion;
    private final Collection<FoundryDocument> foundryDocuments;


    public ModuleGenerator(ObjectMapper objectMapper, String name, String title, String description, String version, String author, String minVersion, String compatibleVersion, Collection<FoundryDocument> foundryDocuments) {
        this.objectMapper = objectMapper;
        this.name = name;
        this.title = title;
        this.description = description;
        this.version = version;
        this.author = author;
        this.minVersion = minVersion;
        this.compatibleVersion = compatibleVersion;
        this.foundryDocuments = foundryDocuments;

        this.outputPath = Path.of("modules", name);
    }

    public void createModule() throws IOException {
        if (!Files.isDirectory(outputPath)) {
            System.out.println("Output directory [" + outputPath + "] does not exist - creating");
            Files.createDirectories(outputPath);
        } else {
            System.out.println("Output directory [" + outputPath + "] already exists - deleting");
            Files.walkFileTree(outputPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("Deleting file " + file);
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        }

        var modulePacks = new ArrayList<ModulePacks>();
        Files.createDirectories(outputPath.resolve("packs"));

        modulePacks.add(createPack("Breeds (W20)", "breeds", DocumentTypes.JOURNAL_ENTRY, foundryDocuments.stream().filter(d -> d.getPack().equals(Packs.Breeds))));
        modulePacks.add(createPack("Auspices (W20)", "auspices", DocumentTypes.JOURNAL_ENTRY, foundryDocuments.stream().filter(d -> d.getPack().equals(Packs.Auspices))));
        modulePacks.add(createPack("Tribes (W20)", "tribes", DocumentTypes.JOURNAL_ENTRY, foundryDocuments.stream().filter(d -> d.getPack().equals(Packs.Tribes))));

        modulePacks.add(createPack("Gifts (W20)", "gifts", DocumentTypes.ITEM, foundryDocuments.stream().filter(d -> d.getPack().equals(Packs.Gifts))));

        modulePacks.add(createPack("Weapons (W20)", "weapons", DocumentTypes.ITEM, foundryDocuments.stream().filter(d -> d.getPack().equals(Packs.Weapons))));

        var module = new Module(
                name,
                title,
                description,
                version,
                author,
                minVersion,
                compatibleVersion,
                modulePacks
        );

        Files.writeString(outputPath.resolve("module.json"), objectMapper.writer(new DefaultPrettyPrinter()).writeValueAsString(module));
    }

    private ModulePacks createPack(String label, String packName, DocumentTypes type, Stream<FoundryDocument> foundryDocuments) throws IOException {
        var path = String.format("./packs/%s.db", packName);
        var moduleFile = outputPath.resolve(path);

        Files.createFile(moduleFile);
        foundryDocuments.forEach(doc -> {
            try {
                Files.writeString(moduleFile, objectMapper.writeValueAsString(doc) + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return new ModulePacks(
                packName,
                label,
                path,
                Werewolf20FoundryConverter.MODULE_NAME,
                type
        );
    }
}
