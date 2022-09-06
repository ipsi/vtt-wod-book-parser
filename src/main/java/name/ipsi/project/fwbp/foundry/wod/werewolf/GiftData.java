package name.ipsi.project.fwbp.foundry.wod.werewolf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;
import name.ipsi.project.fwbp.foundry.wod.PowerTypes;

import java.util.Objects;

@JsonPropertyOrder(alphabetic = true)
public final class GiftData implements ItemData {
    @JsonProperty("iscreated")
    private final boolean created;
    private final String version;
    private final String worldAnvil;
    private final String description;
    private final PowerTypes type;
    private final int level;
    private final String dice1;
    private final String dice2;
    private final int bonus;
    private final String difficulty;
    @JsonProperty("isrollable")
    private final boolean rollable;
    @JsonProperty("isactive")
    private final boolean active;
    private final String groupName;
    private final String system;

    public GiftData(
            boolean created,
            String version,
            String worldAnvil,
            String description,
            PowerTypes type,
            int level,
            String dice1,
            String dice2,
            int bonus,
            String difficulty,
            boolean rollable,
            boolean active,
            String groupName,
            String system
    ) {
        this.created = created;
        this.version = version;
        this.worldAnvil = worldAnvil;
        this.description = description;
        this.type = type;
        this.level = level;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.bonus = bonus;
        this.difficulty = difficulty;
        this.rollable = rollable;
        this.active = active;
        this.groupName = groupName;
        this.system = system;
    }

    public boolean isCreated() {
        return created;
    }

    public String getVersion() {
        return version;
    }

    public String getWorldAnvil() {
        return worldAnvil;
    }

    public String getDescription() {
        return description;
    }

    public PowerTypes getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public String getDice1() {
        return dice1;
    }

    public String getDice2() {
        return dice2;
    }

    public int getBonus() {
        return bonus;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public boolean isRollable() {
        return rollable;
    }

    public boolean isActive() {
        return active;
    }

    public String groupName() {
        return groupName;
    }

    public String getSystem() {
        return system;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GiftData) obj;
        return this.created == that.created &&
                Objects.equals(this.version, that.version) &&
                Objects.equals(this.worldAnvil, that.worldAnvil) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.type, that.type) &&
                this.level == that.level &&
                Objects.equals(this.dice1, that.dice1) &&
                Objects.equals(this.dice2, that.dice2) &&
                this.bonus == that.bonus &&
                Objects.equals(this.difficulty, that.difficulty) &&
                this.rollable == that.rollable &&
                this.active == that.active &&
                Objects.equals(this.groupName, that.groupName) &&
                Objects.equals(this.system, that.system);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, version, worldAnvil, description, type, level, dice1, dice2, bonus, difficulty, rollable, active, groupName, system);
    }

    @Override
    public String toString() {
        return "GiftData[" +
                "created=" + created + ", " +
                "version=" + version + ", " +
                "worldAnvil=" + worldAnvil + ", " +
                "description=" + description + ", " +
                "type=" + type + ", " +
                "level=" + level + ", " +
                "dice1=" + dice1 + ", " +
                "dice2=" + dice2 + ", " +
                "bonus=" + bonus + ", " +
                "difficulty=" + difficulty + ", " +
                "rollable=" + rollable + ", " +
                "active=" + active + ", " +
                "groupName=" + groupName + ", " +
                "system=" + system + ']';
    }


}