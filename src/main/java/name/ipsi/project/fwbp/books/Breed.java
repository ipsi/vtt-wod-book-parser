package name.ipsi.project.fwbp.books;

import name.ipsi.project.fwbp.books.werewolf.Deformities;
import name.ipsi.project.fwbp.books.werewolf.Rank;
import name.ipsi.project.fwbp.books.werewolf.RestrictedAbilities;
import name.ipsi.project.fwbp.foundry.FoundryUtils;

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
        this(FoundryUtils.generateId(), name, description, nicknames, initialGnosis, beginningGifts, deformities, restrictedAbilities, gifts);
    }
}
