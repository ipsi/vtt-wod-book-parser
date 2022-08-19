package name.ipsi.project.fwbp.books;

import name.ipsi.project.fwbp.books.werewolf.Rank;
import name.ipsi.project.fwbp.books.werewolf.Tribes;
import name.ipsi.project.fwbp.foundry.FoundryUtils;

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
        List<Stereotype> stereotypes,
        Map<Rank, List<Gift>> gifts
) implements BookEntry {
    public Tribe(Tribes name, String description, String appearance, String kinfolkAndTerritory, String totem, String characterCreation, int initialWillpower, List<Stereotype> stereotypes, Map<Rank, List<Gift>> gifts) {
        this(FoundryUtils.generateId(), name, description, appearance, kinfolkAndTerritory, totem, characterCreation, initialWillpower, stereotypes, gifts);
    }
}
