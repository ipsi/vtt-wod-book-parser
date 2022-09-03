package name.ipsi.project.fwbp;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

        var expectedFiles = new ArrayList<String>();
        expectedFiles.add("modules/wod-werewolf-20-core/module.json");
        expectedFiles.add("modules/wod-werewolf-20-core/packs/auspices.db");
        expectedFiles.add("modules/wod-werewolf-20-core/packs/breeds.db");
        expectedFiles.add("modules/wod-werewolf-20-core/packs/gifts.db");
        expectedFiles.add("modules/wod-werewolf-20-core/packs/tribes.db");
        expectedFiles.add("modules/wod-werewolf-20-core/packs/weapons.db");
        expectedFiles.sort(Comparator.naturalOrder());

        var actualFiles = new ArrayList<String>();

        Files.walkFileTree(Path.of("modules", "wod-werewolf-20-core"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                actualFiles.add(file.toString());
                return FileVisitResult.CONTINUE;
            }
        });
        actualFiles.sort(Comparator.naturalOrder());

        assertIterableEquals(expectedFiles, actualFiles);
    }
}