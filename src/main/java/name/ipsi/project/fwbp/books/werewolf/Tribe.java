package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.books.shared.PowerCollection;
import name.ipsi.project.fwbp.books.shared.Stereotype;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.Collections;
import java.util.List;

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
        List<Stereotype<Tribes>> stereotypes,
        String quote,
        List<PowerCollection<Rank, Gift>> gifts
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
            List<Stereotype<Tribes>> stereotypes,
            String quote,
            List<PowerCollection<Rank, Gift>> gifts
    ) {
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

    public List<Gift> beginningGifts() {
        return gifts().stream()
                .filter(pc -> pc.group().equals(Rank.ONE))
                .findFirst()
                .map(PowerCollection::powers)
                .orElse(Collections.emptyList());
    }
}
