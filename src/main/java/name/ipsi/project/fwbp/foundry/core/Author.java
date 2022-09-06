package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@JsonPropertyOrder(alphabetic = true)
public class Author {
    private final String name;
    private final String url;
    private final String discord;
    private final Map<String, Object> flags;

    public Author(String name, String url, String discord) {
        this.name = name;
        this.url = url;
        this.discord = discord;
        this.flags = Collections.emptyMap();
    }

    public Author(String name, String url, String discord, Map<String, Object> flags) {
        this.name = name;
        this.url = url;
        this.discord = discord;
        this.flags = flags;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDiscord() {
        return discord;
    }

    public Map<String, Object> getFlags() {
        return flags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) && Objects.equals(url, author.url) && Objects.equals(discord, author.discord) && Objects.equals(flags, author.flags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, discord, flags);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", discord='" + discord + '\'' +
                ", flags=" + flags +
                '}';
    }
}
