package name.ipsi.project.fwbp.foundry.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class FoundryUtils {

    private static final Logger log = LoggerFactory.getLogger(FoundryUtils.class);

    private static final Map<String, String> ids;

    static {
        ids = new TreeMap<>();
        var inputStream = FoundryUtils.class.getResourceAsStream("/id-list.txt");
        if (inputStream == null) {
            throw new RuntimeException("Unable to find list of IDs");
        }
        try (var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            for (var line = reader.readLine(); line != null; line = reader.readLine()) {
                var parts = line.split(",");
                if (parts.length != 2) {
                    throw new RuntimeException("Expected ID file line [" + line + "] to match format <id>,<hash>");
                }
                ids.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateId(String group, String name) {
        var toHash = String.format("%s:%s", group, name);
        var hash = generateHash(toHash);
        if (ids.containsKey(hash)) {
            return ids.get(hash);
        } else {
            log.warn("No existing ID found for [{}] - generating new", toHash);
            var r = new Random();
            var id = r.ints(16, 0, 36)
                    .mapToObj(i -> Integer.toString(i, 36))
                    .collect(Collectors.joining(""));
            ids.put(hash, id);
            return id;
        }
    }

    private static String generateHash(String toHash) {
        try {
            var hasher = MessageDigest.getInstance("SHA512");
            var hash = hasher.digest(toHash.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> getIds() {
        return Collections.unmodifiableMap(ids);
    }
}
