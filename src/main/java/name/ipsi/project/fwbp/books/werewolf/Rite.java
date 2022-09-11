package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.books.shared.TextEntry;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.List;

public record Rite(
        String id,
        String name,
        RiteType type,
        RiteLevel level,
        RollData rollData,
        List<TextEntry> description,
        List<TextEntry> system
) implements BookEntry {
    public Rite(String name, RiteType type, RiteLevel level, RollData rollData, List<TextEntry> description, List<TextEntry> system) {
        this(FoundryUtils.generateId("rite-garou", name), name, type, level, rollData, description, system);
    }
}
