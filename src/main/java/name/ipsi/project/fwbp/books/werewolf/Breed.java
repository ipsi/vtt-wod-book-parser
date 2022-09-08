package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.books.shared.PowerCollection;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.Collections;
import java.util.List;

public record Breed(
        String id,
        String name,
        String description,
        String nicknames,
        int initialGnosis,
        Deformities deformities,
        RestrictedAbilities restrictedAbilities,
        List<PowerCollection<Rank, Gift>> gifts
) implements BookEntry {
    public Breed(String name, String description, String nicknames, int initialGnosis, Deformities deformities, RestrictedAbilities restrictedAbilities, List<PowerCollection<Rank, Gift>> gifts) {
        this(FoundryUtils.generateId("breed", name), name, description, nicknames, initialGnosis, deformities, restrictedAbilities, gifts);
    }

    public List<Gift> beginningGifts() {
        return gifts().stream()
                .filter(pc -> pc.group().equals(Rank.ONE))
                .findFirst()
                .map(PowerCollection::powers)
                .orElse(Collections.emptyList());
    }
}
