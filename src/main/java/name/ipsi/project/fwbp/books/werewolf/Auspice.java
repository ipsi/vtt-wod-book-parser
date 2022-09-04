package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.List;
import java.util.Map;

public record Auspice(
        String id,
        Auspices name,
        List<String> altNames,
        String description,
        int initialRage,
        String stereotype,
        String quote,
        Map<Rank, List<Gift>> gifts
) implements BookEntry {
    public Auspice(Auspices name, List<String> altNames, String description, int initialRage, String stereotype, String quote, Map<Rank, List<Gift>> gifts) {
        this(FoundryUtils.generateId("auspice", name.displayName()), name, altNames, description, initialRage, stereotype, quote, gifts);
    }
}
