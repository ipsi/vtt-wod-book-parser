package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.books.shared.TextEntry;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.List;

public record Fetish(
        String id,
        String name,
        FetishLevel level,
        int gnosis,
        List<TextEntry> description
) implements BookEntry {
    public Fetish(String name, FetishLevel level, int gnosis, List<TextEntry> description) {
        this(FoundryUtils.generateId("fetish", name), name, level, gnosis, description);
    }
}
