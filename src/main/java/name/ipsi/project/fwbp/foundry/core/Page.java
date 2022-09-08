package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collections;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record Page(
        @JsonProperty("_id") String id,
        String name,
        PageType type,
        PageTitle title,
        PageText text,
        PageImage image,
        PageVideo video,
        String src,
        Object system,
        double sort,
        Map<String, Object> flags,
        Map<String, DocumentOwnershipLevel> ownership
) {
    public static Page createTextPage(String id, String name, String content) {
        return createTextPage(id, name, content, DocumentOwnershipLevel.defaultInherit());
    }

    public static Page createTextPage(String id, String name, String content, Map<String, DocumentOwnershipLevel> ownership) {
        return new Page(
                id,
                name,
                PageType.TEXT,
                new PageTitle(),
                PageText.createHtmlText(content),
                null,
                null,
                null,
                null,
                0.0,
                Collections.emptyMap(),
                ownership
        );
    }

    public Page(
            String id,
            String name,
            PageType type,
            PageTitle title,
            PageText text,
            PageImage image,
            PageVideo video,
            String src,
            Object system,
            double sort,
            Map<String, Object> flags,
            Map<String, DocumentOwnershipLevel> ownership
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.text = text;
        this.image = image;
        this.video = video;
        this.src = src;
        this.system = system;
        this.sort = sort;
        this.flags = flags;
        this.ownership = ownership;
    }
}
