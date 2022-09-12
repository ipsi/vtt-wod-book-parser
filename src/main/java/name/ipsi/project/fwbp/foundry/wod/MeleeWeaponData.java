package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

@JsonPropertyOrder(alphabetic = true)
public record MeleeWeaponData(
        Attack attack,
        WeaponConcealment conceal,
        Damage damage,
        String description,
        String difficulty,
        @JsonProperty("iscreated")
        boolean created,
        @JsonProperty("isequipped")
        boolean equipped,
        @JsonProperty("ismagical")
        boolean magical,
        @JsonProperty("isnatural")
        boolean natural,
        @JsonProperty("istwohanded")
        boolean twohanded,
        String parentId,
        String version,
        String worldanvil
) implements ItemData {

    public MeleeWeaponData(Attack attack, WeaponConcealment conceal, Damage damage, String description, String difficulty, boolean natural, boolean twohanded) {
        this(attack, conceal, damage, description, difficulty, true, false, false, natural, twohanded, "", "2.0.3", "");
    }

}
