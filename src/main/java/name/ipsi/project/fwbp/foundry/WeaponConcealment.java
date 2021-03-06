package name.ipsi.project.fwbp.foundry;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WeaponConcealment {
    @JsonProperty("P")
    POCKET,
    @JsonProperty("J")
    JACKET,
    @JsonProperty("T")
    TRENCHCOAT,
    @JsonProperty("NA")
    NONE;

    public static WeaponConcealment parse(String type) {
        return switch (type) {
            case "P" -> POCKET;
            case "J" -> JACKET;
            case "T" -> TRENCHCOAT;
            case "N" -> NONE;
            default -> throw new RuntimeException("Unknown weapon concealment " + type);
        };
    }
}
