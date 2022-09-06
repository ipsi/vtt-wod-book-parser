package name.ipsi.project.fwbp.books.werewolf;

public enum Rank {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX;

    public static Rank from(int i) {
        return switch (i) {
            case 1 -> ONE;
            case 2 -> TWO;
            case 3 -> THREE;
            case 4 -> FOUR;
            case 5 -> FIVE;
            case 6 -> SIX;
            default -> throw new RuntimeException("Unknown Rank " + i);
        };
    }

    public String displayName() {
        return switch (this) {
            case ONE -> "One";
            case TWO -> "Two";
            case THREE -> "Three";
            case FOUR -> "Four";
            case FIVE -> "Five";
            case SIX -> "Six";
        };
    }

    public String loreName() {
        return switch (this) {
            case ONE -> "Cliath";
            case TWO -> "Fostern";
            case THREE -> "Adren";
            case FOUR -> "Athro";
            case FIVE -> "Elder";
            case SIX -> "Legend";
        };
    }

    public int sortKey() {
        return switch (this) {
            case ONE -> 1;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
        };
    }
}
