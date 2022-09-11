package name.ipsi.project.fwbp.foundry.wod.werewolf;

import com.fasterxml.jackson.annotation.JsonProperty;
import name.ipsi.project.fwbp.foundry.core.ItemData;

public record FetishData(
        String description,
        String details,
        String difficulty,
        int gnosis,
        @JsonProperty("iscreated") boolean created,
        @JsonProperty("isequipped") boolean equipped,
        @JsonProperty("ismagical") boolean magical,
        String level,
        int max,
        @JsonProperty("parentid") String parentId,
        FetishType type,
        int value,
        String version,
        String worldAnvil
) implements ItemData {
    public FetishData(String description, int gnosis, String level, FetishType type) {
        this(description, null, String.valueOf(gnosis), gnosis, true, false, false, level, 5, null, type, 0, "2.0.3", null);
    }

    public FetishData(String description, int gnosis, FetishType type) {
        this(description, null, String.valueOf(gnosis), gnosis, true, false, false, "0", 5, null, type, 0, "2.0.3", null);
    }
}
