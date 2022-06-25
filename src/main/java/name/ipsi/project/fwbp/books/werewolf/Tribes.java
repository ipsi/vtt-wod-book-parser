package name.ipsi.project.fwbp.books.werewolf;

public enum Tribes {
    BLACK_FURIES("Black Furies"),
    BONE_GNAWERS("Bone Gnawers"),
    CHILDREN_OF_GAIA("Children of Gaia"),
    FIANNA("Fianna"),
    GET_OF_FENRIS("Get of Fenris"),
    GLASS_WALKERS("Glass Walkers"),
    RED_TALONS("Red Talons"),
    SHADOW_LORDS("Shadow Lords"),
    SILENT_STRIDERS("Silent Sriders"),
    SILVER_FANGS("Silver Fangs"),
    STARGAZERS("Stargazers"),
    UKTENA("Uktena"),
    WENDIGO("Wendigo");

    private final String displayName;

    Tribes(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
