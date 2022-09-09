package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import name.ipsi.project.fwbp.books.werewolf.Tribes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public final class ModuleGenerator {
    public static final Logger log = LoggerFactory.getLogger(ModuleGenerator.class);
    private final ObjectMapper objectMapper;
    private final Path outputPath;
    private final String id;
    private final String title;
    private final String description;
    private final String version;
    private final Map<String, byte[]> images;

    public ModuleGenerator(ObjectMapper objectMapper, String id, String title, String description, String version, Map<String, byte[]> images) {
        this(
                Path.of("modules"),
                objectMapper,
                id,
                title,
                description,
                version,
                images
        );
    }

    public ModuleGenerator(Path modulesDir, ObjectMapper objectMapper, String id, String title, String description, String version, Map<String, byte[]> images) {
        this.objectMapper = objectMapper;
        this.id = id;
        this.title = title;
        this.description = description;
        this.version = version;
        this.images = images;

        this.outputPath = modulesDir.resolve(id);
    }

    public void createModule(Adventure adventure) throws IOException {
        ensureModuleDirectory();

        Files.createDirectories(outputPath.resolve("packs"));

        log.trace("Creating pack - Adventure");
        var pack = createPack("Werewolf: the Apocalypse 20th Anniversary Edition", "w20", DocumentTypes.ADVENTURE, Stream.of(adventure));

        var module = new Module(
                id,
                title,
                description,
                version,
                Collections.singletonList(new Author(
                        "Andrew Thorburn",
                        "",
                        "ipsi#2461"
                )),
                new Compatibility(
                        "10",
                        "10",
                        "10"
                ),
                "w20.css",
                Collections.singletonList(pack)
        );

        try (var is = getClass().getResourceAsStream("/css/w20.css")) {
            if (is == null) {
                throw new RuntimeException("Unable to find /css/w20.css on classpath");
            }
            Files.write(outputPath.resolve("w20.css"), is.readAllBytes());
        }

        writeImages();

        log.trace("Writing module data to file");
        var prettyPrinter = new DefaultPrettyPrinter()
                .withObjectIndenter(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE.withLinefeed("\n"));

        Files.writeString(outputPath.resolve("module.json"), objectMapper.writer(prettyPrinter).writeValueAsString(module), StandardCharsets.UTF_8);
    }

    private void writeImages() throws IOException {
        var imagesDirectory = outputPath.resolve("images");
        if (!Files.isDirectory(imagesDirectory)) {
            Files.createDirectories(imagesDirectory);
        }

        Files.write(imagesDirectory.resolve("adventure-cover.jpeg"), images.get("26:Im1"));

        for(var t : Tribes.values()) {
            Files.write(imagesDirectory.resolve(t.urlName() + "-splash.jpeg"), images.get(String.format("%d:%s", t.imagePage(), t.imageName())));
        }
    }

    private void ensureModuleDirectory() throws IOException {
        log.trace("Creating module directory");
        if (!Files.isDirectory(outputPath)) {
            log.info("Output directory [{}] does not exist - creating", outputPath);
            Files.createDirectories(outputPath);
        } else {
            log.info("Output directory [{}] already exists - deleting", outputPath);
            Files.walkFileTree(outputPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    log.debug("Deleting file {}", file);
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    private ModulePack createPack(String label, String packName, DocumentTypes type, Stream<FoundryDocument> foundryDocuments) throws IOException {
        var path = String.format("./packs/%s.db", packName);
        var packFile = outputPath.resolve(path);
        log.trace("Creating pack {}, {}, {} at {}", label, packName, type, packFile);

        Files.createFile(packFile);
        foundryDocuments.forEach(doc -> {
            try {
                log.trace("Writing document to pack file");
                Files.writeString(packFile, objectMapper.writeValueAsString(doc) + "\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        log.trace("Returning module pack");
        return new ModulePack(
                packName,
                label,
                path,
                type == DocumentTypes.ITEM || type == DocumentTypes.ADVENTURE ? "worldofdarkness" : null,
                type
        );
    }

    public Path getOutputPath() {
        return outputPath;
    }
}
