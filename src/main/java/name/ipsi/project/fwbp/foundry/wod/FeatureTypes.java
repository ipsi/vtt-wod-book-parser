package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FeatureTypes {
    @JsonProperty("wod.types.background")
    BACKGROUND,
    @JsonProperty("wod.types.merit")
    MERIT,
    @JsonProperty("wod.types.flaw")
    FLAW;
}
