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
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "module.json"), "2fd8faa0ff39629fa42a18a65503a504170e678c995667375767753f30fc49f5da0f8888b05eae8426439be5c66bcca0b01e7ead3833b7b89cebf0b6427a1917");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "packs", "w20.db"), "8ee3e63b8e803f5af72f64fba05e6b289f05ac5e919827f695b56554a783a98b7625f2b3e36f6fcd09fc34f5f1a88cd09e8d0016ee9e7bda08cf273925fe7b8f");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "adventure-cover.jpeg"), "17d71cdd62f4c930b803fbcf1fab02a3d0244eb7fa1d4359ab088381b1288f1bb191117ec60d3075528732ff9ce4888545e6b66b0d681b00b68b79a214daee1c");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "black-furies-splash.jpeg"), "0c2f71fba6238336509d84672af71bee9344c477e0696ddd2f4dcf7d53193a1df4726f59f78135d653411f9e44dddbb08f54c65cb552a3ae3ce358dc1e2a4dea");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "bone-gnawers-splash.jpeg"), "1f870258146f3846f2f8a38aa76aa42145ab41d2318fdbcc2429d727480e723618152f40e3e082d4c5c9df9ae01816b4a015f34b8f22a5739e02de04d000f25a");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "children-of-gaia-splash.jpeg"), "b3264c4755acf4b8d092dda81d29cad136ae28049191af51dbc092b15d9ca0d7bda33e9cd6d2d3ecd68824d650081a3a3075702af1e4e6fb7e9a2b30ac599f78");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "fianna-splash.jpeg"), "0338f1cb4128261c9a0c0b426db3356960fc467ba0e6df8e3cb0e1195c306568aa219545f3d1ab79d3b3a654ea1618e21774d72cfabf1541396aca81390e97c2");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "get-of-fenris-splash.jpeg"), "caed63939c6f01947800d62bc86f3c26cc8353b7b33b4fedd49111b0370ce06340432946269080b9109f947c25d7a7efc9b0ad8ab75f4abd3a973b298fc762a0");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "glass-walkers-splash.jpeg"), "0b30d9b9d06c8550481b13c915dff882a84992bfeb2f75a1ac08cbb2df43cffb0caa3b2eadbbf265595be8866c789791410cb9ad6f7b13a5198e49828686b3b2");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "red-talons-splash.jpeg"), "3091a905bdd095c8e5d96d10f90199d0cf6fd2ca34e029bc15a249bc346074b4dda843373106f74b2672aee5a52aa0c4bd23e53acb5590d16359d5f334f62abb");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "shadow-lords-splash.jpeg"), "d86bfa7aa711169664c25005e3e82926dde31745a7b2890180ff9802adae1a82eb1e32f38bcd11878e9b68190c105682a8600bc78f3fe0b2c88bd9fdc5ec954a");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "silent-sriders-splash.jpeg"), "9a9e2272830fa219559edaa25e55eb1303b9ccb1499cbc71ef1056bdc430929585fc03fb68e2e1c6a69d0c3bc11250f41f521a10724b7d56dfee123a08bcb9ea");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "silver-fangs-splash.jpeg"), "e8f7e7cc8cdc4c616abf10a94ba3ef147b179eed24dc71e4f79073def0bbbc1b142d1423adada31ca85dfd4562157835488101998b6b97ca68b50d53fb178fe7");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "stargazers-splash.jpeg"), "dffb5b1d46dac00b5a96218f165b44aa9db15d4e6ecf58efa7c643cb88dfdfc49334d590afbb7f1caccf192ee788ada69bf3a52d9d96fd9dd34b4a0cfa06feb7");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "uktena-splash.jpeg"), "51c1feae75021337847365ace2fae3e2ccd17d1f908e9f9a15fe434cfc9dbfc74f7d19511fb9ad0eeff1b5a4af366c97134a76c472ce309a5f54fb9177a8d996");
        expectedFiles.put(Path.of("modules", "wod-werewolf-20-core", "images", "wendigo-splash.jpeg"), "d8898992d1744a9ae5b7cf3e8ed2e923896e463d87bbfc52dd65cbf9df12d0369c266c09dcdd4a92adc3579ebfe7eba9e1fab0d7761f8f6c87099fa10fb88e0a");

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