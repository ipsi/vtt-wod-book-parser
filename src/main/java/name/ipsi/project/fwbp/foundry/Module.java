package name.ipsi.project.fwbp.foundry;

import java.util.List;
import java.util.Objects;

public final class Module {
    private final String name;
    private final String title;
    private final String description;
    private final String version;
    private final String author;
    private final String minimumCoreVersion;
    private final String compatibleCoreVersion;
    private final List<ModulePacks> packs;

    public Module(
            String name,
            String title,
            String description,
            String version,
            String author,
            String minimumCoreVersion,
            String compatibleCoreVersion,
            List<ModulePacks> packs
    ) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.version = version;
        this.author = author;
        this.minimumCoreVersion = minimumCoreVersion;
        this.compatibleCoreVersion = compatibleCoreVersion;
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

    public String getAuthor() {
        return author;
    }

    public String getMinimumCoreVersion() {
        return minimumCoreVersion;
    }

    public String getCompatibleCoreVersion() {
        return compatibleCoreVersion;
    }

    public List<ModulePacks> getPacks() {
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
                Objects.equals(this.minimumCoreVersion, that.minimumCoreVersion) &&
                Objects.equals(this.compatibleCoreVersion, that.compatibleCoreVersion) &&
                Objects.equals(this.packs, that.packs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, title, description, version, author, minimumCoreVersion, compatibleCoreVersion, packs);
    }

    @Override
    public String toString() {
        return "Module[" +
                "name=" + name + ", " +
                "title=" + title + ", " +
                "description=" + description + ", " +
                "version=" + version + ", " +
                "author=" + author + ", " +
                "minimumCoreVersion=" + minimumCoreVersion + ", " +
                "compatibleCoreVersion=" + compatibleCoreVersion + ", " +
                "packs=" + packs + ']';
    }

}
