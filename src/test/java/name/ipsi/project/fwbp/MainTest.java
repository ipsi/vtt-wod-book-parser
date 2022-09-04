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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MainTest {

    @Test
    void testMainMethod() throws Exception {
        System.setIn(new ByteArrayInputStream("1".getBytes(Charset.defaultCharset())));

        Main.main(new String[]{});

        var expectedFiles = new TreeMap<Path, String>();
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "module.json"), "f79bddaadad4b45444b91ffab880eacfe091dc49edc4e5b92d9641a66dca321d5ab32460ae1204d1905186bf2d6f90903a1e6088fba4b60cb679b3eecf661dec");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "packs", "auspices.db"), "614157c2c41b18a9ce7d16405c48c3dc0e814690eca875fc3b0635ff599682ca622e26fbc8a0a00c406c9ce469a0efee88aeb903e83d42b31c892745185e2f0c");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "packs", "breeds.db"), "845067b391529bb99dd30f3676ee84534faf4b92d8907f8be880c990c7c442a63c2c057fe1e7132b9a7bc95999282b57412717cfa5a6d715e608836cb994aa86");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "packs", "gifts.db"), "dcceddf58db10eb31339bf6ee2e3c6494361039d3b3026bf2204942691718a1c9cf3fd6391632601d933689b1484a4a5cce6c2ded8607f5a2c53ad57e0d3828c");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "packs", "tribes.db"), "178451bbaedc6772bcbb3b31a0ae3b3642db3eb0652ba1a78172c86fd4f768328a97b64064f164eb4093967b080f4fa18e8d4584bc786acc7ad5568d037e6b13");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "packs", "weapons.db"), "7b6c1b8a581b4610420baf6581c4c6ec0e76d294cbec076572e9a6847f5f46fa57d01b79e46e1ee455eed017181be9faad76bec2aeeddf462ce124ac5083eb0d");

        var actualFiles = new TreeMap<Path, String>();

        Files.walkFileTree(Path.of("modules", "wod-werewolf-20-core"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                try {
                    var md = MessageDigest.getInstance("SHA512");
                    var sum = md.digest(Files.readAllBytes(file));
                    var sb = new StringBuilder(sum.length * 2);
                    for(byte b: sum)
                        sb.append(String.format("%02x", b));
                    actualFiles.put(file, sb.toString());
                    return FileVisitResult.CONTINUE;
                } catch (NoSuchAlgorithmException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        assertIterableEquals(expectedFiles.entrySet(), actualFiles.entrySet());
    }
}