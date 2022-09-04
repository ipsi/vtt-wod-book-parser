package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.List;
import java.util.Map;

public record Breed(
        String id,
        String name,
        String description,
        String nicknames,
        int initialGnosis,
        List<Gift> beginningGifts,
        Deformities deformities,
        RestrictedAbilities restrictedAbilities,
        Map<Rank, List<Gift>> gifts
) implements BookEntry {
    public Breed(String name, String description, String nicknames, int initialGnosis, List<Gift> beginningGifts, Deformities deformities, RestrictedAbilities restrictedAbilities, Map<Rank, List<Gift>> gifts) {
        this(FoundryUtils.generateId("breed", name), name, description, nicknames, initialGnosis, beginningGifts, deformities, restrictedAbilities, gifts);
    }
}
