package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public record Module(
        String name,
        String title,
        String description,
        String version,
        @Deprecated
        String author,
        List<Author> authors,
        @Deprecated
        String minimumCoreVersion,
        @Deprecated
        String compatibleCoreVersion,
        Compatibility compatibility,
        List<ModulePack> packs
) {
    public Module(
            String name,
            String title,
            String description,
            String version,
            List<Author> authors,
            Compatibility compatibility,
            List<ModulePack> packs
    ) {
        this(
                name,
                title,
                description,
                version,
                null,
                authors,
                null,
                null,
                compatibility,
                packs
        );
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
        this(
                name,
                title,
                description,
                version,
                author,
                null,
                minimumCoreVersion,
                compatibleCoreVersion,
                null,
                packs
        );
    }

}
