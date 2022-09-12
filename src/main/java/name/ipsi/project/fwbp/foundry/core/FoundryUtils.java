package name.ipsi.project.fwbp.foundry.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
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
                var parts = line.split("\0");
                if (parts.length != 2) {
                    throw new RuntimeException("Expected ID file line [" + line + "] to match format <key>\0<id>");
                }
                ids.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateId(String group, String name) {
        var key = String.format("%s:%s", group, name);
        if (ids.containsKey(key)) {
            return ids.get(key);
        } else {
            log.warn("No existing ID found for [{}] - generating new", key);
            var r = new Random();
            var id = r.ints(16, 0, 36)
                    .mapToObj(i -> Integer.toString(i, 36))
                    .collect(Collectors.joining(""));
            ids.put(key, id);
            return id;
        }
    }

    public static Map<String, String> getIds() {
        return Collections.unmodifiableMap(ids);
    }
}
