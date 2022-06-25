package name.ipsi.project.fwbp.foundry;

import java.util.List;

public record Module(
        String name,
        String title,
        String description,
        String version,
        String author,
        String minimumCoreVersion,
        String compatibleCoreVersion,
        List<ModulePacks> packs
) {
}
