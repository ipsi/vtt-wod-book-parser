package name.ipsi.project.fwbp.foundry;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Module {
    private final String name;
    private final String title;
    private final String description;
    private final String version;
    @Deprecated
    private final String author;
    private final List<Author> authors;
    @Deprecated
    private final String minimumCoreVersion;
    @Deprecated
    private final String compatibleCoreVersion;
    private final Compatibility compatibility;
    private final List<ModulePack> packs;

    public Module(
            String name,
            String title,
            String description,
            String version,
            List<Author> authors,
            Compatibility compatibility,
            List<ModulePack> packs
    ) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.version = version;
        this.author = null;
        this.authors = authors;
        this.minimumCoreVersion = null;
        this.compatibleCoreVersion = null;
        this.compatibility = compatibility;
        this.packs = packs;
    }

    public Module(
            String name,
            String title,
            String description,
            String version,
            String author,
            String minimumCoreVersion,
            String compatibleCoreVersion,
            List<ModulePack> packs
    ) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.version = version;
        this.author = author;
        this.authors = null;
        this.minimumCoreVersion = minimumCoreVersion;
        this.compatibleCoreVersion = compatibleCoreVersion;
        this.compatibility = null;
        this.packs = packs;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    @Deprecated
    public String getAuthor() {
        return author;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Deprecated
    public String getMinimumCoreVersion() {
        return minimumCoreVersion;
    }

    @Deprecated
    public String getCompatibleCoreVersion() {
        return compatibleCoreVersion;
    }

    public Compatibility getCompatibility() {
        return compatibility;
    }

    public List<ModulePack> getPacks() {
        return packs;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Module) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.version, that.version) &&
                Objects.equals(this.author, that.author) &&
                Objects.equals(this.authors, that.authors) &&
                Objects.equals(this.minimumCoreVersion, that.minimumCoreVersion) &&
                Objects.equals(this.compatibleCoreVersion, that.compatibleCoreVersion) &&
                Objects.equals(this.compatibility, that.compatibility) &&
                Objects.equals(this.packs, that.packs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, title, description, version, author, authors, minimumCoreVersion, compatibleCoreVersion, compatibility, packs);
    }

    @Override
    public String toString() {
        return "Module[" +
                "name=" + name + ", " +
                "title=" + title + ", " +
                "description=" + description + ", " +
                "version=" + version + ", " +
                "author=" + author + ", " +
                "authors=" + authors + ", " +
                "minimumCoreVersion=" + minimumCoreVersion + ", " +
                "compatibleCoreVersion=" + compatibleCoreVersion + ", " +
                "compatibility=" + compatibility + ", " +
                "packs=" + packs + ']';
    }

}
