package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PageTitleLevel {
    ONE(1.0),
    TWO(2.0),
    THREE(3.0);

    @JsonValue
    private final double level;

    PageTitleLevel(double level) {
        this.level = level;
    }
}
