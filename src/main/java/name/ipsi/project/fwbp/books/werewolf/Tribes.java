package name.ipsi.project.fwbp.books.werewolf;

public enum Tribes {
    BLACK_FURIES("Black Furies", 81, "Im0"),
    BONE_GNAWERS("Bone Gnawers", 83, "Im1"),
    CHILDREN_OF_GAIA("Children of Gaia", 85, "Im0"),
    FIANNA("Fianna", 87, "Im0"),
    GET_OF_FENRIS("Get of Fenris", 89, "Im0"),
    GLASS_WALKERS("Glass Walkers", 91, "Im0"),
    RED_TALONS("Red Talons", 93, "Im0"),
    SHADOW_LORDS("Shadow Lords", 95, "Im0"),
    SILENT_STRIDERS("Silent Sriders", 97, "Im0"),
    SILVER_FANGS("Silver Fangs", 99, "Im0"),
    STARGAZERS("Stargazers", 101, "Im0"),
    UKTENA("Uktena", 103, "Im0"),
    WENDIGO("Wendigo", 105, "Im0");

    private final String displayName;
    private final int imagePage;
    private final String imageName;

    Tribes(String displayName, int imagePage, String imageName) {
        this.displayName = displayName;
        this.imagePage = imagePage;
        this.imageName = imageName;
    }

    public String displayName() {
        return displayName;
    }
    public String urlName() {
        return displayName.toLowerCase().replaceAll("\\s+", "-");
    }
    public int imagePage() {
        return imagePage;
    }
    public String imageName() {
        return imageName;
    }
}
