package name.ipsi.project.fwbp.foundry;

import java.util.Map;

public record Folder(
        String id,
        String name,
        DocumentTypes type,
        String parent,
        FolderSortingModes sorting,
        int sort,
        String color,
        Map<String, String> flags
) {
    public Folder(String name, DocumentTypes type, String parent, FolderSortingModes sorting, int sort, String color, Map<String, String> flags) {
        this(FoundryUtils.generateId(), name, type, parent, sorting, sort, color, flags);
    }
}
