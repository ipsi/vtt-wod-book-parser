package name.ipsi.project.fwbp.books.shared;

import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

public record Background(
        String id,
        String name,
        String description,
        Table ratings
) implements BookEntry {
    public Background(String name, String description, Table ratings, String system) {
        this(FoundryUtils.generateId("background-" + system, name), name, description, ratings);
    }
}
