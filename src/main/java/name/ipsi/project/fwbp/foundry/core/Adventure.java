package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Adventure implements FoundryDocument {

    @JsonProperty("_id")
    private final String id;
    private final String name;
    private final String img;
    private final String description;
    private final List<Object> actors;
    private final List<Object> combats;
    private final List<Item> items;
    private final List<Object> scenes;
    private final List<Journal> journal;
    private final List<Object> tables;
    private final List<Object> macros;
    private final List<Object> cards;
    private final List<Object> playlists;
    private final List<Folder> folders;
    private final double sort;
    private final Map<String, Object> flags;
    private final String caption;
    @JsonProperty("_stats")
    private final Object stats;

    public Adventure(String id, String name, String img, String description, List<Object> actors, List<Object> combats, List<Item> items, List<Object> scenes, List<Journal> journal, List<Object> tables, List<Object> macros, List<Object> cards, List<Object> playlists, List<Folder> folders, double sort, Map<String, Object> flags, String caption, Object stats) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.description = description;
        this.actors = actors;
        this.combats = combats;
        this.items = items;
        this.scenes = scenes;
        this.journal = journal;
        this.tables = tables;
        this.macros = macros;
        this.cards = cards;
        this.playlists = playlists;
        this.folders = folders;
        this.sort = sort;
        this.flags = flags;
        this.caption = caption;
        this.stats = stats;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public List<Object> getActors() {
        return actors;
    }

    public List<Object> getCombats() {
        return combats;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Object> getScenes() {
        return scenes;
    }

    public List<Journal> getJournal() {
        return journal;
    }

    public List<Object> getTables() {
        return tables;
    }

    public List<Object> getMacros() {
        return macros;
    }

    public List<Object> getCards() {
        return cards;
    }

    public List<Object> getPlaylists() {
        return playlists;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public double getSort() {
        return sort;
    }

    public Map<String, Object> getFlags() {
        return flags;
    }

    public String getCaption() {
        return caption;
    }

    public Object getStats() {
        return stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adventure adventure = (Adventure) o;
        return Double.compare(adventure.sort, sort) == 0 && Objects.equals(id, adventure.id) && Objects.equals(name, adventure.name) && Objects.equals(img, adventure.img) && Objects.equals(description, adventure.description) && Objects.equals(actors, adventure.actors) && Objects.equals(combats, adventure.combats) && Objects.equals(items, adventure.items) && Objects.equals(scenes, adventure.scenes) && Objects.equals(journal, adventure.journal) && Objects.equals(tables, adventure.tables) && Objects.equals(macros, adventure.macros) && Objects.equals(cards, adventure.cards) && Objects.equals(playlists, adventure.playlists) && Objects.equals(folders, adventure.folders) && Objects.equals(flags, adventure.flags) && Objects.equals(caption, adventure.caption) && Objects.equals(stats, adventure.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, img, description, actors, combats, items, scenes, journal, tables, macros, cards, playlists, folders, sort, flags, caption, stats);
    }

    @Override
    public String toString() {
        return "Adventure{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", actors=" + actors +
                ", combats=" + combats +
                ", items=" + items +
                ", scenes=" + scenes +
                ", journal=" + journal +
                ", tables=" + tables +
                ", macros=" + macros +
                ", cards=" + cards +
                ", playlists=" + playlists +
                ", folders=" + folders +
                ", sort=" + sort +
                ", flags=" + flags +
                ", caption='" + caption + '\'' +
                ", stats=" + stats +
                '}';
    }

    @Override
    public Packs getPack() {
        return Packs.Adventures;
    }
}
