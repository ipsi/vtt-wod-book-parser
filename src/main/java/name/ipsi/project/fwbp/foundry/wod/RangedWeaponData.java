package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;
import name.ipsi.project.fwbp.foundry.core.ItemData;

public record RangedWeaponData(
        Attack attack,
        Clip clip,
        WeaponConcealment conceal,
        Damage damage,
        String description,
        int difficulty,
        @JsonProperty("iscreated") boolean created,
        @JsonProperty("isequipped") boolean equipped,
        @JsonProperty("ismagical") boolean magical,
        @JsonProperty("istwohanded") boolean twohanded,
        Mode mode,
        String parentId,
        Integer range,
        Integer rate,
        String version,
        String worldanvil
) implements ItemData {

    public RangedWeaponData(Attack attack, Clip clip, WeaponConcealment concealment, Damage damage, String description, Mode mode, Integer range, Integer rate) {
        this(attack, clip, concealment, damage, description, 6, true, false, false, false, mode, null, range, rate, "2.0.3", null);
    }

    public RangedWeaponData(Attack attack, WeaponConcealment concealment, Damage damage, String description, int difficulty) {
        this(attack, null, concealment, damage, description, difficulty, true, false, false, false, null, null,null, null, "2.0.3", null);
    }

    public record Clip(Integer value, Integer max) {}

    public record Mode(
            @JsonProperty("hasreload") boolean reload,
            @JsonProperty("hasburst") boolean burst,
            @JsonProperty("hasfullauto") boolean fullAuto,
            @JsonProperty("hasspray") boolean spray
    ) {}
}
