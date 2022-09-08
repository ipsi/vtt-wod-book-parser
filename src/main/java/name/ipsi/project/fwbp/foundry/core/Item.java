package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.wod.ItemTypes;

import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record Item(@JsonProperty("_id") String id, String name, ItemTypes type, String img, ItemData data,
                   String effects, String folder, int sort, Map<String, Integer> permission, Map<String, String> flags,
                   @JsonIgnore Packs pack) implements FoundryDocument {

}
