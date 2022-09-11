package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;
import name.ipsi.project.fwbp.foundry.core.ItemData;

public record FeatureData(
    String description,
    String details,
    @JsonProperty("iscreated") boolean created,
    boolean rollable,
    String level,
    int max,
    @JsonProperty("parentid") String parentId,
    FeatureTypes type,
    int value,
    String version,
    @JsonProperty("worldanvil") String worldAnvil
) implements ItemData {

    public FeatureData(String description, String details, FeatureTypes type) {
        this(description, details, true, type == FeatureTypes.BACKGROUND, "0", 5, "", type, 0, "2.0.3", "");
    }
}
