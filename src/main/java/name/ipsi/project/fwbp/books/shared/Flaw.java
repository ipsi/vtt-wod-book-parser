package name.ipsi.project.fwbp.books.shared;

import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.List;

public record Flaw(String id, String section, String name, List<TextEntry> description, int cost, int max) implements BookEntry {

    public Flaw(String section, String name, List<TextEntry> description, int cost, int max) {
        this(FoundryUtils.generateId("garou-flaw", name), section, name, description, cost, max > 0 ? max : cost);
    }
}
