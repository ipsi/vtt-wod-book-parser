package name.ipsi.project.fwbp.books.werewolf;

import java.util.Arrays;
import java.util.Optional;

public enum Tribes {
    BLACK_FURIES("Black Furies", 81, "Im0"),
    BONE_GNAWERS("Bone Gnawers", 83, "Im1"),
    CHILDREN_OF_GAIA("Children of Gaia", 85, "Im0"),
    FIANNA("Fianna", 87, "Im0"),
    GET_OF_FENRIS("Get of Fenris", 89, "Im0"),
    GLASS_WALKERS("Glass Walkers", 91, "Im0"),
    RED_TALONS("Red Talons", 93, "Im0"),
    SHADOW_LORDS("Shadow Lords", 95, "Im0"),
    SILENT_STRIDERS("Silent Striders", 97, "Im0"),
    SILVER_FANGS("Silver Fangs", 99, "Im0"),
    STARGAZERS("Stargazers", 101, "Im0"),
    UKTENA("Uktena", 103, "Im0"),
    WENDIGO("Wendigo", 105, "Im0"),
    BUNYIP("Bunyip", 389, "Im0"),
    CROATAN("Croatan", 391, "Im0"),
    WHITE_HOWLERS("White Howlers", 393, "Im0"),
    BLACK_SPIRAL_DANCERS("Black Spiral Dancers", 425, "Im1");

    private final String displayName;
    private final int imagePage;
    private final String imageName;

    public static Optional<Tribes> findByDisplayName(String name) {
        return Arrays.stream(Tribes.values()).filter(t -> t.displayName().equals(name)).findFirst();
    }

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
