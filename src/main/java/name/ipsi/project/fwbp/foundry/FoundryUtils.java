package name.ipsi.project.fwbp.foundry;

import java.util.Random;
import java.util.stream.Collectors;

public class FoundryUtils {
    public static String generateId() {
        var r = new Random();
        return r.ints(16, 0, 36)
                .mapToObj(i -> Integer.toString(i, 36))
                .collect(Collectors.joining(""));
    }
}
