package name.ipsi.project.fwbp.foundry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Journal(
        @JsonProperty("_id") String id,
        String name,
        String content,
        String folder,
        @JsonIgnore Packs pack
) implements FoundryDocument {
}
