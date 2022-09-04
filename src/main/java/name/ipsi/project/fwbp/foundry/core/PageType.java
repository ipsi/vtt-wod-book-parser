package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PageType {
    @JsonProperty("image")
    IMAGE,
    @JsonProperty("pdf")
    PDF,
    @JsonProperty("text")
    TEXT,
    @JsonProperty("video")
    VIDEO
}
