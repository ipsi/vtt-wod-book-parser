package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.books.shared.TextEntry;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.List;

public record Talen(
        String id,
        String name,
        int gnosis,
        List<TextEntry> description
) implements BookEntry {
    public Talen(String name, int gnosis, List<TextEntry> description) {
        this(FoundryUtils.generateId("talen", name), name, gnosis, description);
    }
}
