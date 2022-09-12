package name.ipsi.project.fwbp.books.shared;

import name.ipsi.project.fwbp.foundry.core.FoundryUtils;
import name.ipsi.project.fwbp.foundry.wod.DamageTypes;
import name.ipsi.project.fwbp.foundry.wod.WeaponConcealment;

import java.util.List;

public record RangedWeapon(
        String id,
        String name,
        List<TextEntry> description,
        int damage,
        DamageTypes damageType,
        WeaponConcealment concealment,
        Integer range,
        Integer rate,
        Integer clip,
        boolean canReload,
        boolean hasBurst,
        boolean hasFullAuto,
        boolean hasSpray
) implements BookEntry {
    public RangedWeapon(
            String name,
            List<TextEntry> description,
            int damage,
            DamageTypes damageType,
            WeaponConcealment concealment,
            Integer range,
            Integer rate,
            Integer clip,
            boolean canReload,
            boolean hasBurst,
            boolean hasFullAuto,
            boolean hasSpray
    ) {
        this(
                FoundryUtils.generateId("ranged-weapon", name),
                name,
                description,
                damage,
                damageType,
                concealment,
                range,
                rate,
                clip,
                canReload,
                hasBurst,
                hasFullAuto,
                hasSpray
        );
    }
}
