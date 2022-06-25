package name.ipsi.project.fwbp.foundry;

public record MeleeWeaponData(
        boolean created,
        boolean iscreated,
        String version,
        String worldanvil,
        String description,
        boolean isEquipped,
        boolean isMagical,
        Attack attack,
        Damage damage,
        String diff,
        int difficulty,
        WeaponConcealment conceal,
        boolean twohanded,
        boolean istwohanded,
        boolean natural,
        String label
) implements ItemData {
    public record Attack(
            String attribute,
            String ability,
            boolean roll,
            boolean isRollable
    ) {}
    public record Damage(
            String attribute,
            int bonus,
            DamageTypes type,
            boolean roll,
            boolean isRollable
    ) {}
}
