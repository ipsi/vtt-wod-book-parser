package name.ipsi.project.fwbp.foundry;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FolderSortingModes {
    @JsonProperty("m")
    MANUAL,
    @JsonProperty("a")
    ALPHABETICAL
}
