package name.ipsi.project.fwbp.books.werewolf;

public enum Breeds {
    HOMID("Homid"),
    METIS("Metis"),
    LUPUS("Lupus");

    private final String displayName;

    Breeds(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
