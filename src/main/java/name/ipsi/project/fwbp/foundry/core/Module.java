package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public record Module(
        String id,
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
        List<String> esmodules,
        List<String> styles,
        List<ModulePack> packs
) {
    public Module(
            String id,
            String title,
            String description,
            String version,
            List<Author> authors,
            Compatibility compatibility,
            String stleSheet,
            List<ModulePack> packs
    ) {
        this(
                id,
                title,
                description,
                version,
                null,
                authors,
                null,
                null,
                compatibility,
                Collections.emptyList(),
                Collections.singletonList(stleSheet),
                packs
        );
    }

}
