package name.ipsi.project.fwbp.foundry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

public final class Item implements FoundryDocument {
    @JsonProperty("_id")
    private final String id;
    private final String name;
    private final ItemTypes type;
    private final String img;
    private final ItemData data;
    private final String effects;
    private final String folder;
    private final int sort;
    private final Map<String, Integer> permission;
    private final Map<String, String> flags;
    @JsonIgnore
    private final Packs pack;

    public Item(
            @JsonProperty("_id") String id,
            String name,
            ItemTypes type,
            String img,
            ItemData data,
            String effects,
            String folder,
            int sort,
            Map<String, Integer> permission,
            Map<String, String> flags,
            Packs pack
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.img = img;
        this.data = data;
        this.effects = effects;
        this.folder = folder;
        this.sort = sort;
        this.permission = permission;
        this.flags = flags;
        this.pack = pack;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemTypes getType() {
        return type;
    }

    public String getImg() {
        return img;
    }

    public ItemData getData() {
        return data;
    }

    public String getEffects() {
        return effects;
    }

    public String getFolder() {
        return folder;
    }

    public int getSort() {
        return sort;
    }

    public Map<String, Integer> getPermission() {
        return permission;
    }

    public Map<String, String> getFlags() {
        return flags;
    }

    @JsonIgnore
    public Packs getPack() {
        return pack;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Item) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.type, that.type) &&
                Objects.equals(this.img, that.img) &&
                Objects.equals(this.data, that.data) &&
                Objects.equals(this.effects, that.effects) &&
                Objects.equals(this.folder, that.folder) &&
                this.sort == that.sort &&
                Objects.equals(this.permission, that.permission) &&
                Objects.equals(this.flags, that.flags) &&
                Objects.equals(this.pack, that.pack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, img, data, effects, folder, sort, permission, flags, pack);
    }

    @Override
    public String toString() {
        return "Item[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "type=" + type + ", " +
                "img=" + img + ", " +
                "data=" + data + ", " +
                "effects=" + effects + ", " +
                "folder=" + folder + ", " +
                "sort=" + sort + ", " +
                "permission=" + permission + ", " +
                "flags=" + flags + ", " +
                "pack=" + pack + ']';
    }

}
