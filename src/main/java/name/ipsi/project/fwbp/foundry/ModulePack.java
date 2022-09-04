package name.ipsi.project.fwbp.foundry;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ModulePack {
    private final String name;
    private final String label;
    private final String path;
    private final String module;
    private final String system;
    private final DocumentTypes type;

    public ModulePack(
            String name,
            String label,
            String path,
            String module,
            String system,
            DocumentTypes type
    ) {
        this.name = name;
        this.label = label;
        this.path = path;
        this.module = module;
        this.system = system;
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

    public String getSystem() {
        return system;
    }

    public DocumentTypes getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ModulePack) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.label, that.label) &&
                Objects.equals(this.path, that.path) &&
                Objects.equals(this.module, that.module) &&
                Objects.equals(this.system, that.system) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, label, path, module, system, type);
    }

    @Override
    public String toString() {
        return "ModulePacks[" +
                "name=" + name + ", " +
                "label=" + label + ", " +
                "path=" + path + ", " +
                "module=" + module + ", " +
                "system=" + system + ", " +
                "type=" + type + ']';
    }

}
