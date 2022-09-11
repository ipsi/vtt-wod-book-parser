package name.ipsi.project.fwbp.books.werewolf;

public enum RiteLevel {
    MINOR,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;

    public String displayName() {
        return switch (this) {
            case MINOR -> "Minor";
            case ONE -> "One";
            case TWO -> "Two";
            case THREE -> "Three";
            case FOUR -> "Four";
            case FIVE -> "Five";
        };
    }

    public int foundryLevel() {
        return switch (this) {
            case MINOR, ONE -> 1;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
        };
    }
}