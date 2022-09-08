package name.ipsi.project.fwbp.foundry.wod.werewolf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;
import name.ipsi.project.fwbp.foundry.wod.PowerTypes;

@JsonPropertyOrder(alphabetic = true)
public record GiftData(@JsonProperty("iscreated") boolean created, String version, String worldAnvil,
                       String description, PowerTypes type, int level, String dice1, String dice2, int bonus,
                       String difficulty, @JsonProperty("isrollable") boolean rollable,
                       @JsonProperty("isactive") boolean active, String groupName, String system) implements ItemData {


}