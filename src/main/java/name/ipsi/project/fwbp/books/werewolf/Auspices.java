package name.ipsi.project.fwbp.books.werewolf;

public enum Auspices {
    RAGABASH("Ragabash"),
    THEURGE("Theurge"),
    PHILODOX("Philodox"),
    GALLIARD("Galliard"),
    AHROUN("Ahroun");

    private final String displayName;

    Auspices(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
