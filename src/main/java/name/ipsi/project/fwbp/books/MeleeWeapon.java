package name.ipsi.project.fwbp.books;

import name.ipsi.project.fwbp.foundry.DamageTypes;
import name.ipsi.project.fwbp.foundry.FoundryUtils;
import name.ipsi.project.fwbp.foundry.WeaponConcealment;

public record MeleeWeapon(
        String id,
        String name,
        int difficulty,
        int damageBonus,
        DamageTypes damageType,
        WeaponConcealment concealment,
        boolean canEntangle,
        boolean breaksAfterUse,
        boolean twoHanded,
        boolean silver,
        boolean selfDamage,
        boolean natural
) implements BookEntry {
    public MeleeWeapon(String name, int difficulty, int damageBonus, DamageTypes damageType, WeaponConcealment concealment, boolean canEntangle, boolean breaksAfterUse, boolean twoHanded, boolean silver, boolean selfDamage, boolean natural) {
        this(FoundryUtils.generateId(), name, difficulty, damageBonus, damageType, concealment, canEntangle, breaksAfterUse, twoHanded, silver, selfDamage, natural);
    }
}
