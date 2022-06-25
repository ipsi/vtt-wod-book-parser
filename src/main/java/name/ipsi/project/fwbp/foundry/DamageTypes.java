package name.ipsi.project.fwbp.foundry;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DamageTypes {
    @JsonProperty("bashing")
    BASHING,
    @JsonProperty("lethal")
    LETHAL,
    @JsonProperty("aggravated")
    AGGRAVATED;

    public static DamageTypes parse(String type) {
        return switch (type) {
            case "B" -> BASHING;
            case "L" -> LETHAL;
            case "A" -> AGGRAVATED;
            default -> throw new RuntimeException("Unknown damage type " + type);
        };
    }
}
