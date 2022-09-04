package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ItemTypes {
    @JsonProperty("Feature")
    FEATURE,
    @JsonProperty("Melee Weapon")
    MELEE_WEAPON,
    @JsonProperty("Ranged Weapon")
    RANGED_WEAPON,
    @JsonProperty("Armor")
    ARMOR,
    @JsonProperty("Power")
    POWER,
    @JsonProperty("Rote")
    ROTE,
    @JsonProperty("Fetish")
    FETISH
}
