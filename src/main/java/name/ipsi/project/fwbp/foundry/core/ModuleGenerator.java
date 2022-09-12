package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

public final class ModuleGenerator {
    public static final Logger log = LoggerFactory.getLogger(ModuleGenerator.class);
    private final ObjectMapper objectMapper;
    private final Path outputPath;
    private final String coreFilePrefix;
    private final String id;
    private final String title;
    private final String description;
    private final String version;
    private final Collection<PdfImage> images;

    public ModuleGenerator(
            Path modulesDir,
            String coreFilePrefix,
            String id,
            String title,
            String description,
            String version,
            Collection<PdfImage> images
    ) {
        this.objectMapper = new ObjectMapper();

        this.outputPath = modulesDir.resolve(id);
        this.coreFilePrefix = coreFilePrefix;
        this.id = id;
        this.title = title;
        this.description = description;
        this.version = version;
        this.images = images;
    }

    public void createModule(Adventure adventure) throws IOException {
        ensureModuleDirectory();

        Files.createDirectories(outputPath.resolve("packs"));

        log.trace("Creating pack - Adventure");
        var pack = createPack(title, coreFilePrefix, DocumentTypes.ADVENTURE, Stream.of(adventure));

        var coreJavascript = coreFilePrefix + ".js";
        var coreStylesheet = coreFilePrefix + ".css";
        var module = new Module(
                id,
                title,
                description,
                version,
                Collections.singletonList(new Author(
                        "Andrew Thorburn",
                        "https://github.com/ipsi/vtt-wod-book-parser",
                        "ipsi#2461"
                )),
                new Compatibility(
                        "10",
                        "10",
                        "10"
                ),
                coreJavascript,
                coreStylesheet,
                Collections.singletonList(pack)
        );

        try (var is = getClass().getResourceAsStream("/css/" + coreStylesheet)) {
            if (is == null) {
                throw new RuntimeException("Unable to find /css/" + coreStylesheet + " on classpath");
            }
            Files.write(outputPath.resolve(coreStylesheet), is.readAllBytes());
        }

        try (var is = getClass().getResourceAsStream("/js/" + coreJavascript)) {
            if (is == null) {
                throw new RuntimeException("Unable to find /js/" + coreJavascript + " on classpath");
            }
            Files.write(outputPath.resolve(coreJavascript), is.readAllBytes());
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

        var coverImageSeen = false;
        for (var i : images) {
            Files.write(imagesDirectory.resolve(i.name()), i.data());
            if (i.type() == ImageType.COVER) {
                coverImageSeen = true;
            }
        }

        if (!coverImageSeen) {
            throw new RuntimeException("Cover image must be provided");
        }
    }

    private void ensureModuleDirectory() throws IOException {
        log.trace("Creating module directory");
        if (!Files.isDirectory(outputPath)) {
            log.info("Output directory [{}] does not exist - creating", outputPath);
            Files.createDirectories(outputPath);
        } else {
            log.debug("Output directory already exists");
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
