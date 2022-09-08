package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record Folder(@JsonProperty("_id") String id, String name, DocumentTypes type, FolderSortingModes sorting,
                     double sort, String color, Map<String, Object> flags, String folder, String description) {
    public Folder(String id, String name, DocumentTypes type, FolderSortingModes sorting, double sort, String color, Map<String, Object> flags, String folder, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.sorting = sorting;
        this.sort = sort;
        this.color = color;
        this.flags = flags;
        this.folder = folder;
        this.description = description;
    }
}
