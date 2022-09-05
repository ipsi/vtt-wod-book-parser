package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Journal implements FoundryDocument {
    @JsonProperty("_id")
    private final String id;
    private final String name;
    private final String content;
    private final String folder;
    private final double sort;
    private final Map<String, Object> flags;
    private final List<Page> pages;
    private final Map<String, DocumentOwnershipLevel> ownership;
    @JsonIgnore
    private final Packs pack;

    public Journal(
            @JsonProperty("_id") String id,
            String name,
            String content,
            String folder,
            double sort,
            Packs pack
    ) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.folder = folder;
        this.sort = sort;
        this.flags = Collections.emptyMap();
        this.pages = null;
        this.ownership = null;
        this.pack = pack;
    }

    public Journal(String id, String name, String folder, double sort, Map<String, Object> flags, List<Page> pages, Map<String, DocumentOwnershipLevel> ownership) {
        this.id = id;
        this.name = name;
        this.folder = folder;
        this.sort = sort;
        this.flags = flags;
        this.pages = pages;
        this.ownership = ownership;

        this.content = null;
        this.pack = null;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getFolder() {
        return folder;
    }

    public double getSort() {
        return sort;
    }

    public Map<String, Object> getFlags() {
        return flags;
    }

    public List<Page> getPages() {
        return pages;
    }

    public Map<String, DocumentOwnershipLevel> getOwnership() {
        return ownership;
    }

    @JsonIgnore
    public Packs getPack() {
        return pack;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Journal) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.content, that.content) &&
                Objects.equals(this.folder, that.folder) &&
                Objects.equals(this.sort, that.sort) &&
                Objects.equals(this.flags, that.flags) &&
                Objects.equals(this.pages, that.pages) &&
                Objects.equals(this.ownership, that.ownership) &&
                Objects.equals(this.pack, that.pack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, folder, sort, flags, pages, ownership, pack);
    }

    @Override
    public String toString() {
        return "Journal[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "content=" + content + ", " +
                "folder=" + folder + ", " +
                "sort=" + sort + ", " +
                "flags=" + flags + ", " +
                "pages=" + pages + ", " +
                "ownership=" + ownership + ", " +
                "pack=" + pack + ']';
    }

}
