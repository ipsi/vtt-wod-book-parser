package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PageTextFormat {
    HTML(1.0),
    MARKDOWN(2.0);

    @JsonValue
    private final double jsonValue;

    PageTextFormat(double jsonValue) {
        this.jsonValue = jsonValue;
    }
}
