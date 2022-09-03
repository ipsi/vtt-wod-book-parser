package name.ipsi.project.fwbp;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MainTest {

    @Test
    void testMainMethod() throws Exception {
        System.setIn(new ByteArrayInputStream("1".getBytes(Charset.defaultCharset())));

        Main.main(new String[]{});

        var expectedFiles = new ArrayList<Path>();
        expectedFiles.add(Path.of("modules", "wod-werewolf-20-core", "module.json"));
        expectedFiles.add(Path.of("modules", "wod-werewolf-20-core", "packs", "auspices.db"));
        expectedFiles.add(Path.of("modules", "wod-werewolf-20-core", "packs", "breeds.db"));
        expectedFiles.add(Path.of("modules", "wod-werewolf-20-core", "packs", "gifts.db"));
        expectedFiles.add(Path.of("modules", "wod-werewolf-20-core", "packs", "tribes.db"));
        expectedFiles.add(Path.of("modules", "wod-werewolf-20-core", "packs", "weapons.db"));
        expectedFiles.sort(Comparator.naturalOrder());

        var actualFiles = new ArrayList<Path>();

        Files.walkFileTree(Path.of("modules", "wod-werewolf-20-core"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                actualFiles.add(file);
                return FileVisitResult.CONTINUE;
            }
        });
        actualFiles.sort(Comparator.naturalOrder());

        assertIterableEquals(expectedFiles, actualFiles);
    }
}