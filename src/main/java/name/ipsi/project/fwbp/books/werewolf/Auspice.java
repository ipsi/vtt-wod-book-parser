package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.books.shared.PowerCollection;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.Collections;
import java.util.List;

public record Auspice(
        String id,
        String name,
        List<String> altNames,
        String description,
        int initialRage,
        String stereotype,
        String quote,
        List<PowerCollection<Rank, Gift>> gifts
) implements BookEntry {
    public Auspice(String name, List<String> altNames, String description, int initialRage, String stereotype, String quote, List<PowerCollection<Rank, Gift>> gifts) {
        this(FoundryUtils.generateId("auspice", name), name, altNames, description, initialRage, stereotype, quote, gifts);
    }

    public List<Gift> beginningGifts() {
        return gifts().stream()
                .filter(pc -> pc.group().equals(Rank.ONE))
                .findFirst()
                .map(PowerCollection::powers)
                .orElse(Collections.emptyList());
    }
}
