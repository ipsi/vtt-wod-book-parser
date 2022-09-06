package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;
import java.util.Objects;

@JsonPropertyOrder(alphabetic = true)
public class Folder {
    @JsonProperty("_id")
    private final String id;
    private final String name;
    private final DocumentTypes type;
    private final FolderSortingModes sorting;
    private final double sort;
    private final String color;
    private final Map<String, Object> flags;
    private final String folder;
    private final String description;

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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DocumentTypes getType() {
        return type;
    }

    public FolderSortingModes getSorting() {
        return sorting;
    }

    public double getSort() {
        return sort;
    }

    public String getColor() {
        return color;
    }

    public Map<String, Object> getFlags() {
        return flags;
    }

    public String getFolder() {
        return folder;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder1 = (Folder) o;
        return Double.compare(folder1.sort, sort) == 0 && Objects.equals(id, folder1.id) && Objects.equals(name, folder1.name) && Objects.equals(type, folder1.type) && Objects.equals(sorting, folder1.sorting) && Objects.equals(color, folder1.color) && Objects.equals(flags, folder1.flags) && Objects.equals(folder, folder1.folder) && Objects.equals(description, folder1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, sorting, sort, color, flags, folder, description);
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sorting='" + sorting + '\'' +
                ", sort=" + sort +
                ", color='" + color + '\'' +
                ", flags=" + flags +
                ", folder='" + folder + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
