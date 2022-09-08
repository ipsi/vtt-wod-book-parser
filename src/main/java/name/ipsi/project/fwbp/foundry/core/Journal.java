package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public record Journal(
        @JsonProperty("_id")
        String id,
        String name,
        String content,
        String folder,
        double sort,
        Map<String, Object> flags,
        List<Page> pages,
        Map<String, DocumentOwnershipLevel> ownership,
        @JsonIgnore
        Packs pack
) implements FoundryDocument {

    public Journal(
            String id,
            String name,
            String content,
            String folder,
            double sort,
            Packs pack
    ) {
        this(
                id,
                name,
                content,
                folder,
                sort,
                Collections.emptyMap(),
                null,
                null,
                pack
        );
    }

    public Journal(
            String id,
            String name,
            String folder,
            double sort,
            Map<String, Object> flags,
            List<Page> pages,
            Map<String, DocumentOwnershipLevel> ownership
    ) {
        this(
                id,
                name,
                null,
                folder,
                sort,
                flags,
                pages,
                ownership,
                null
        );
    }
}
