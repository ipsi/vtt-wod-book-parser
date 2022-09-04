package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PowerTypes {
    @JsonProperty("wod.types.gift")
    GIFT,
    @JsonProperty("wod.types.rite")
    RITE,
    @JsonProperty("wod.types.charm")
    CHARM,
    @JsonProperty("wod.types.power")
    POWER
}
