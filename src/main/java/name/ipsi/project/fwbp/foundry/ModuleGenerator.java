package name.ipsi.project.fwbp.foundry;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import name.ipsi.project.fwbp.books.werewolf.Werewolf20FoundryConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public final class ModuleGenerator {
    public static final Logger log = LoggerFactory.getLogger(ModuleGenerator.class);
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
        log.trace("Creating module directory");
        if (!Files.isDirectory(outputPath)) {
            log.info("Output directory [{}] does not exist - creating", outputPath);
            Files.createDirectories(outputPath);
        } else {
            log.info("Output directory [{}] already exists - deleting", outputPath);
            Files.walkFileTree(outputPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    log.info("Deleting file {}", file);
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        }

        var modulePacks = new ArrayList<ModulePacks>();
        Files.createDirectories(outputPath.resolve("packs"));

        log.trace("Creating pack - Breeds");
        modulePacks.add(createPack("Breeds (W20)", "breeds", DocumentTypes.JOURNAL_ENTRY, foundryDocuments.stream().filter(d -> d.getPack().equals(Packs.Breeds))));
        log.trace("Creating pack - Auspices");
        modulePacks.add(createPack("Auspices (W20)", "auspices", DocumentTypes.JOURNAL_ENTRY, foundryDocuments.stream().filter(d -> d.getPack().equals(Packs.Auspices))));
        log.trace("Creating pack - Tribes");
        modulePacks.add(createPack("Tribes (W20)", "tribes", DocumentTypes.JOURNAL_ENTRY, foundryDocuments.stream().filter(d -> d.getPack().equals(Packs.Tribes))));

        log.trace("Creating pack - Gifts");
        modulePacks.add(createPack("Gifts (W20)", "gifts", DocumentTypes.ITEM, foundryDocuments.stream().filter(d -> d.getPack().equals(Packs.Gifts))));

        log.trace("Creating pack - Weapons");
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

        log.trace("Writing module data to file");
        Files.writeString(outputPath.resolve("module.json"), objectMapper.writer(new DefaultPrettyPrinter()).writeValueAsString(module));
    }

    private ModulePacks createPack(String label, String packName, DocumentTypes type, Stream<FoundryDocument> foundryDocuments) throws IOException {
        var path = String.format("./packs/%s.db", packName);
        var packFile = outputPath.resolve(path);
        log.trace("Creating pack {}, {}, {} at {}", label, packName, type, packFile);

        Files.createFile(packFile);
        foundryDocuments.forEach(doc -> {
            try {
                log.trace("Writing document to pack file");
                Files.writeString(packFile, objectMapper.writeValueAsString(doc) + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        log.trace("Returning module pack");
        return new ModulePacks(
                packName,
                label,
                path,
                Werewolf20FoundryConverter.MODULE_NAME,
                type
        );
    }

    public Path getOutputPath() {
        return outputPath;
    }
}
