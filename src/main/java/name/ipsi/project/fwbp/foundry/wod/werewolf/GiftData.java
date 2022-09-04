package name.ipsi.project.fwbp.foundry.wod.werewolf;

import name.ipsi.project.fwbp.foundry.wod.PowerTypes;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.Objects;

public final class GiftData implements ItemData {
    private final boolean created;
    private final boolean iscreated;
    private final String version;
    private final String worldAnvil;
    private final String description;
    private final PowerTypes type;
    private final int level;
    private final String dice1;
    private final String dice2;
    private final int bonus;
    private final String difficulty;
    private final boolean rollable;
    private final boolean isRollable;
    private final boolean active;
    private final boolean isactive;
    private final String groupName;
    private final String system;

    public GiftData(
            boolean created,
            boolean iscreated,
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
            boolean isRollable,
            boolean active,
            boolean isactive,
            String groupName,
            String system
    ) {
        this.created = created;
        this.iscreated = iscreated;
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
        this.isRollable = isRollable;
        this.active = active;
        this.isactive = isactive;
        this.groupName = groupName;
        this.system = system;
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

    public boolean isIsRollable() {
        return isRollable;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isIsactive() {
        return isactive;
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
                this.iscreated == that.iscreated &&
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
                this.isRollable == that.isRollable &&
                this.active == that.active &&
                this.isactive == that.isactive &&
                Objects.equals(this.groupName, that.groupName) &&
                Objects.equals(this.system, that.system);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, iscreated, version, worldAnvil, description, type, level, dice1, dice2, bonus, difficulty, rollable, isRollable, active, isactive, groupName, system);
    }

    @Override
    public String toString() {
        return "GiftData[" +
                "created=" + created + ", " +
                "iscreated=" + iscreated + ", " +
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
                "isRollable=" + isRollable + ", " +
                "active=" + active + ", " +
                "isactive=" + isactive + ", " +
                "groupName=" + groupName + ", " +
                "system=" + system + ']';
    }


}