package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class Page {
    @JsonProperty("_id")
    private final String id;
    private final String name;
    private final PageType type;
    private final PageTitle title;
    private final PageText text;
    private final PageImage image;
    private final PageVideo video;
    private final String src;
    private final Object system;
    private final double sort;
    private final Map<String, Object> flags;
    private final Map<String, DocumentOwnershipLevel> ownership;

    public static Page createTextPage(String id, String name, String content) {
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
                DocumentOwnershipLevel.defaultInherit()
        );
    }

    public Page(String id, String name, PageType type, PageTitle title, PageText text, PageImage image, PageVideo video, String src, Object system, double sort, Map<String, Object> flags, Map<String, DocumentOwnershipLevel> ownership) {
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PageType getType() {
        return type;
    }

    public PageTitle getTitle() {
        return title;
    }

    public PageText getText() {
        return text;
    }

    public PageImage getImage() {
        return image;
    }

    public PageVideo getVideo() {
        return video;
    }

    public String getSrc() {
        return src;
    }

    public Object getSystem() {
        return system;
    }

    public double getSort() {
        return sort;
    }

    public Map<String, Object> getFlags() {
        return flags;
    }

    public Map<String, DocumentOwnershipLevel> getOwnership() {
        return ownership;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Double.compare(page.sort, sort) == 0 && Objects.equals(id, page.id) && Objects.equals(name, page.name) && type == page.type && Objects.equals(title, page.title) && Objects.equals(text, page.text) && Objects.equals(image, page.image) && Objects.equals(video, page.video) && Objects.equals(src, page.src) && Objects.equals(system, page.system) && Objects.equals(flags, page.flags) && Objects.equals(ownership, page.ownership);
    }

    @Override
    public String toString() {
        return "Page{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", title=" + title +
                ", text=" + text +
                ", image=" + image +
                ", video=" + video +
                ", src='" + src + '\'' +
                ", system=" + system +
                ", sort=" + sort +
                ", flags=" + flags +
                ", ownership=" + ownership +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, title, text, image, video, src, system, sort, flags, ownership);
    }

}
