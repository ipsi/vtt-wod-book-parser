package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Attack(
        String attribute,
        int accuracy,
        String ability,
        @JsonProperty("isrollable") boolean rollable
) {
}
