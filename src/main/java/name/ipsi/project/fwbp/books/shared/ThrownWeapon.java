package name.ipsi.project.fwbp.books.shared;

import name.ipsi.project.fwbp.foundry.core.FoundryUtils;
import name.ipsi.project.fwbp.foundry.wod.DamageTypes;
import name.ipsi.project.fwbp.foundry.wod.WeaponConcealment;

import java.util.List;

public record ThrownWeapon(
        String id,
        String name,
        List<TextEntry> description,
        int difficulty,
        boolean includeStrength,
        int damageBonus,
        DamageTypes damageType,
        WeaponConcealment concealment
) implements BookEntry {
    public ThrownWeapon(String name, List<TextEntry> description, int difficulty, boolean includeStrength, int damageBonus, DamageTypes damageType, WeaponConcealment concealment) {
        this(FoundryUtils.generateId("thrown-weapon", name), name, description, difficulty, includeStrength, damageBonus, damageType, concealment);
    }
}
