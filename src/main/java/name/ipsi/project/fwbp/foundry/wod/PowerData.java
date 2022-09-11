package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

@JsonPropertyOrder(alphabetic = true)
public record PowerData(
        int bonus,
        String description,
        String dice1,
        String dice2,
        String difficulty,
        @JsonProperty("isactive") boolean active,
        @JsonProperty("iscreated") boolean created,
        @JsonProperty("isrollable") boolean rollable,
        String level,
        int max,
        @JsonProperty("parentid") String parentId,
        @JsonProperty("spendblood") boolean spendBlood,
        @JsonProperty("spendgnosis") boolean spendGnosis,
        @JsonProperty("spendrage") boolean spendRage,
        @JsonProperty("spendwillpower") boolean spendWillpower,
        String system,
        PowerTypes type,
        int value,
        String version,
        String worldAnvil
) implements ItemData {

    public PowerData(String description, String dice1, String dice2, String difficulty, boolean rollable, String level, PowerTypes type) {
        this(0, description, dice1, dice2, difficulty, false, true, rollable, level, 5, "", false, false, false, false, "", type, 0, "2.0.3", null);
    }

    public PowerData(String description, String dice1, String dice2, String difficulty, boolean rollable, String level, String system, PowerTypes type) {
        this(0, description, dice1, dice2, difficulty, false, true, rollable, level, 5, "", false, false, false, false, system, type, 0, "2.0.3", null);
    }
}