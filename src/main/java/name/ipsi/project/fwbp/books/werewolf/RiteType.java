package name.ipsi.project.fwbp.books.werewolf;

public enum RiteType {
    ACCORD(new RollData("Charisma", "Rituals", "7")),
    CAERN(null), // Technically yes, but attribute + ability varies
    DEATH(new RollData("Charisma", "Rituals", "varies")),
    MYSTIC(new RollData("Wits", "Rituals", "7")),
    PUNISHMENT(new RollData("Charisma", "Rituals", "7")),
    RENOWN(new RollData("Charisma", "Rituals", "6")),
    SEASONAL(new RollData("Stamina", "Rituals", "varies")),
    MINOR(null);

    private final RollData rollData;

    RiteType(RollData rollData) {
        this.rollData = rollData;
    }

    public String displayName() {
        return switch (this) {
            case ACCORD -> "Accord";
            case CAERN -> "Caern";
            case DEATH -> "Death";
            case MYSTIC -> "Mystic";
            case PUNISHMENT -> "Punishment";
            case RENOWN -> "Renown";
            case SEASONAL -> "Seasonal";
            case MINOR -> "Minor";
        };
    }

    public RollData rollData() {
        return rollData;
    }
}
