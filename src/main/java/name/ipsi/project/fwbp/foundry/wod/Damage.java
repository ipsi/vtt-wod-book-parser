package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Damage(
        String attribute,
        int bonus,
        DamageTypes type,
        @JsonProperty("isrollable") boolean rollable
) {
}
