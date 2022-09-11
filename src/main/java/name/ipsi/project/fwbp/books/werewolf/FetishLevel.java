package name.ipsi.project.fwbp.books.werewolf;

public enum FetishLevel {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;

    public String foundryLevel() {
        return switch (this) {
            case ONE -> "1";
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
        };
    }
}
