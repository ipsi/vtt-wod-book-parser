package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

@JsonPropertyOrder(alphabetic = true)
public record MeleeWeaponData(
        @JsonProperty("iscreated")
        boolean created,
        String version,
        String worldanvil,
        String description,
        @JsonProperty("isequipped")
        boolean equipped,
        @JsonProperty("ismagical")
        boolean magical,
        Attack attack,
        Damage damage,
        String diff,
        int difficulty,
        WeaponConcealment conceal,
        @JsonProperty("istwohanded")
        boolean twohanded,
        @JsonProperty("isnatural")
        boolean natural,
        String label
) implements ItemData {

    public record Attack(
            String attribute,
            String ability,
            @JsonProperty("isrollable") boolean rollable
    ) {
    }

    public record Damage(
            String attribute,
            int bonus,
            DamageTypes type,
            @JsonProperty("isrollable") boolean rollable
    ) {
    }
}
