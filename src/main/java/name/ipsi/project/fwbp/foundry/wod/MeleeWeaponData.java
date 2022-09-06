package name.ipsi.project.fwbp.foundry.wod;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.Objects;

@JsonPropertyOrder(
        value = {
                "iscreated",
                "version",
                "worldanvil",
                "description",
                "isequipped",
                "ismagical",
                "attack",
                "damage",
                "diff",
                "difficulty",
                "conceal",
                "istwohanded",
                "isnatural",
                "label",
        }
)
public final class MeleeWeaponData implements ItemData {
    @JsonProperty("iscreated")
    private final boolean created;
    private final String version;
    private final String worldanvil;
    private final String description;
    @JsonProperty("isequipped")
    private final boolean equipped;
    @JsonProperty("ismagical")
    private final boolean magical;
    private final Attack attack;
    private final Damage damage;
    private final String diff;
    private final int difficulty;
    private final WeaponConcealment conceal;
    @JsonProperty("istwohanded")
    private final boolean twohanded;
    @JsonProperty("isnatural")
    private final boolean natural;
    private final String label;

    public MeleeWeaponData(
            boolean created,
            String version,
            String worldanvil,
            String description,
            boolean equipped,
            boolean magical,
            Attack attack,
            Damage damage,
            String diff,
            int difficulty,
            WeaponConcealment conceal,
            boolean twohanded,
            boolean natural,
            String label
    ) {
        this.created = created;
        this.version = version;
        this.worldanvil = worldanvil;
        this.description = description;
        this.equipped = equipped;
        this.magical = magical;
        this.attack = attack;
        this.damage = damage;
        this.diff = diff;
        this.difficulty = difficulty;
        this.conceal = conceal;
        this.twohanded = twohanded;
        this.natural = natural;
        this.label = label;
    }

    public boolean isCreated() {
        return created;
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
        return equipped;
    }

    public boolean isIsMagical() {
        return magical;
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
                Objects.equals(this.version, that.version) &&
                Objects.equals(this.worldanvil, that.worldanvil) &&
                Objects.equals(this.description, that.description) &&
                this.equipped == that.equipped &&
                this.magical == that.magical &&
                Objects.equals(this.attack, that.attack) &&
                Objects.equals(this.damage, that.damage) &&
                Objects.equals(this.diff, that.diff) &&
                this.difficulty == that.difficulty &&
                Objects.equals(this.conceal, that.conceal) &&
                this.twohanded == that.twohanded &&
                this.natural == that.natural &&
                Objects.equals(this.label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, version, worldanvil, description, equipped, magical, attack, damage, diff, difficulty, conceal, twohanded, natural, label);
    }

    @Override
    public String toString() {
        return "MeleeWeaponData[" +
                "created=" + created + ", " +
                "version=" + version + ", " +
                "worldanvil=" + worldanvil + ", " +
                "description=" + description + ", " +
                "equipped=" + equipped + ", " +
                "magical=" + magical + ", " +
                "attack=" + attack + ", " +
                "damage=" + damage + ", " +
                "diff=" + diff + ", " +
                "difficulty=" + difficulty + ", " +
                "conceal=" + conceal + ", " +
                "twohanded=" + twohanded + ", " +
                "natural=" + natural + ", " +
                "label=" + label + ']';
    }

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
