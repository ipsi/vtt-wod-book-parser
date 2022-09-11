package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

@JsonPropertyOrder(alphabetic = true)
public record PowerData(@JsonProperty("iscreated") boolean created, String version, String worldAnvil,
                        String description, PowerTypes type, int level, String dice1, String dice2, int bonus,
                        String difficulty, @JsonProperty("isrollable") boolean rollable,
                        @JsonProperty("isactive") boolean active, String groupName, String system) implements ItemData {


}