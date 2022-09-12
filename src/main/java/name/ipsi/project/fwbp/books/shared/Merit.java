package name.ipsi.project.fwbp.books.shared;

import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.List;

public record Merit(String id, String section, String name, List<TextEntry> description, int cost, int max) implements BookEntry {
    public Merit(String section, String name, List<TextEntry> description, int cost, int max) {
        this(FoundryUtils.generateId("garou-merit", name), section, name, description, cost, max > 0 ? max : cost);
    }
}
