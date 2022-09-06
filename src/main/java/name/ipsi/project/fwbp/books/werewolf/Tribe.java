package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.List;
import java.util.Map;

public record Tribe(
        String id,
        Tribes name,
        String description,
        String appearance,
        String kinfolkAndTerritory,
        String totem,
        String characterCreation,
        int initialWillpower,
        String backgroundRestrictions,
        String derangement,
        Map<Tribes, String> stereotypes,
        String quote,
        Map<Rank, List<Gift>> gifts
) implements BookEntry {
    public Tribe(
            Tribes name,
            String description,
            String appearance,
            String kinfolkAndTerritory,
            String totem,
            String characterCreation,
            int initialWillpower,
            String backgroundRestrictions,
            String derangement,
            Map<Tribes, String> stereotypes,
            String quote,
            Map<Rank, List<Gift>> gifts) {
        this(
                FoundryUtils.generateId("tribe", name.displayName()),
                name,
                description,
                appearance,
                kinfolkAndTerritory,
                totem,
                characterCreation,
                initialWillpower,
                backgroundRestrictions,
                derangement,
                stereotypes,
                quote,
                gifts);
    }
}
