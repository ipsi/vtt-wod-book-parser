package name.ipsi.project.fwbp.books.shared;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.foundry.wod.DamageTypes;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;
import name.ipsi.project.fwbp.foundry.wod.WeaponConcealment;

public record MeleeWeapon(
        String id,
        String name,
        String description,
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
    public MeleeWeapon(String name, String description, int difficulty, int damageBonus, DamageTypes damageType, WeaponConcealment concealment, boolean canEntangle, boolean breaksAfterUse, boolean twoHanded, boolean silver, boolean selfDamage, boolean natural) {
        this(FoundryUtils.generateId("melee-weapon", name), name, description, difficulty, damageBonus, damageType, concealment, canEntangle, breaksAfterUse, twoHanded, silver, selfDamage, natural);
    }
}
