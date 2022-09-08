package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record Adventure(
        @JsonProperty("_id") String id,
        String name,
        String img,
        String description,
        List<Object> actors,
        List<Object> combats,
        List<Item> items,
        List<Object> scenes,
        List<Journal> journal,
        List<Object> tables,
        List<Object> macros,
        List<Object> cards,
        List<Object> playlists,
        List<Folder> folders,
        double sort,
        Map<String, Object> flags,
        String caption,
        @JsonProperty("_stats") Object stats
) implements FoundryDocument {

    public Adventure(
            String id,
            String name,
            String img,
            String description,
            List<Object> actors,
            List<Object> combats,
            List<Item> items,
            List<Object> scenes,
            List<Journal> journal,
            List<Object> tables,
            List<Object> macros,
            List<Object> cards,
            List<Object> playlists,
            List<Folder> folders,
            double sort,
            Map<String, Object> flags,
            String caption,
            Object stats
    ) {
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

    @Override
    public Packs pack() {
        return Packs.Adventures;
    }
}
