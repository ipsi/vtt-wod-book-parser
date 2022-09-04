package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public final class Journal implements FoundryDocument {
    @JsonProperty("_id")
    private final String id;
    private final String name;
    private final String content;
    private final String folder;
    @JsonIgnore
    private final Packs pack;

    public Journal(
            @JsonProperty("_id") String id,
            String name,
            String content,
            String folder,
            Packs pack
    ) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.folder = folder;
        this.pack = pack;
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
                Objects.equals(this.pack, that.pack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, folder, pack);
    }

    @Override
    public String toString() {
        return "Journal[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "content=" + content + ", " +
                "folder=" + folder + ", " +
                "pack=" + pack + ']';
    }

}
