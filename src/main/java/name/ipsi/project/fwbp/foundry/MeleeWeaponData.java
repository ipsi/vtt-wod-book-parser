package name.ipsi.project.fwbp.foundry;

import java.util.Objects;

public final class MeleeWeaponData implements ItemData {
    private final boolean created;
    private final boolean iscreated;
    private final String version;
    private final String worldanvil;
    private final String description;
    private final boolean isEquipped;
    private final boolean isMagical;
    private final Attack attack;
    private final Damage damage;
    private final String diff;
    private final int difficulty;
    private final WeaponConcealment conceal;
    private final boolean twohanded;
    private final boolean istwohanded;
    private final boolean natural;
    private final String label;

    public MeleeWeaponData(
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
    ) {
        this.created = created;
        this.iscreated = iscreated;
        this.version = version;
        this.worldanvil = worldanvil;
        this.description = description;
        this.isEquipped = isEquipped;
        this.isMagical = isMagical;
        this.attack = attack;
        this.damage = damage;
        this.diff = diff;
        this.difficulty = difficulty;
        this.conceal = conceal;
        this.twohanded = twohanded;
        this.istwohanded = istwohanded;
        this.natural = natural;
        this.label = label;
    }

    public boolean isCreated() {
        return created;
    }

    public boolean isIscreated() {
        return iscreated;
    }

    public String getVersion() {
        return version;
    }

    public String getWorldanvil() {
        return worldanvil;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIsEquipped() {
        return isEquipped;
    }

    public boolean isIsMagical() {
        return isMagical;
    }

    public Attack getAttack() {
        return attack;
    }

    public Damage getDamage() {
        return damage;
    }

    public String getDiff() {
        return diff;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public WeaponConcealment getConceal() {
        return conceal;
    }

    public boolean isTwohanded() {
        return twohanded;
    }

    public boolean isIstwohanded() {
        return istwohanded;
    }

    public boolean isNatural() {
        return natural;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (MeleeWeaponData) obj;
        return this.created == that.created &&
                this.iscreated == that.iscreated &&
                Objects.equals(this.version, that.version) &&
                Objects.equals(this.worldanvil, that.worldanvil) &&
                Objects.equals(this.description, that.description) &&
                this.isEquipped == that.isEquipped &&
                this.isMagical == that.isMagical &&
                Objects.equals(this.attack, that.attack) &&
                Objects.equals(this.damage, that.damage) &&
                Objects.equals(this.diff, that.diff) &&
                this.difficulty == that.difficulty &&
                Objects.equals(this.conceal, that.conceal) &&
                this.twohanded == that.twohanded &&
                this.istwohanded == that.istwohanded &&
                this.natural == that.natural &&
                Objects.equals(this.label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, iscreated, version, worldanvil, description, isEquipped, isMagical, attack, damage, diff, difficulty, conceal, twohanded, istwohanded, natural, label);
    }

    @Override
    public String toString() {
        return "MeleeWeaponData[" +
                "created=" + created + ", " +
                "iscreated=" + iscreated + ", " +
                "version=" + version + ", " +
                "worldanvil=" + worldanvil + ", " +
                "description=" + description + ", " +
                "isEquipped=" + isEquipped + ", " +
                "isMagical=" + isMagical + ", " +
                "attack=" + attack + ", " +
                "damage=" + damage + ", " +
                "diff=" + diff + ", " +
                "difficulty=" + difficulty + ", " +
                "conceal=" + conceal + ", " +
                "twohanded=" + twohanded + ", " +
                "istwohanded=" + istwohanded + ", " +
                "natural=" + natural + ", " +
                "label=" + label + ']';
    }

    public record Attack(
            String attribute,
            String ability,
            boolean roll,
            boolean isRollable
    ) {
    }

    public record Damage(
            String attribute,
            int bonus,
            DamageTypes type,
            boolean roll,
            boolean isRollable
    ) {
    }
}
