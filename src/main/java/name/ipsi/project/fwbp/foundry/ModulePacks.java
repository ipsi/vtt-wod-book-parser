package name.ipsi.project.fwbp.foundry;

import java.util.Objects;

public final class ModulePacks {
    private final String name;
    private final String label;
    private final String path;
    private final String module;
    private final DocumentTypes type;

    public ModulePacks(
            String name,
            String label,
            String path,
            String module,
            DocumentTypes type
    ) {
        this.name = name;
        this.label = label;
        this.path = path;
        this.module = module;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getPath() {
        return path;
    }

    public String getModule() {
        return module;
    }

    public DocumentTypes getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ModulePacks) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.label, that.label) &&
                Objects.equals(this.path, that.path) &&
                Objects.equals(this.module, that.module) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, label, path, module, type);
    }

    @Override
    public String toString() {
        return "ModulePacks[" +
                "name=" + name + ", " +
                "label=" + label + ", " +
                "path=" + path + ", " +
                "module=" + module + ", " +
                "type=" + type + ']';
    }

}
