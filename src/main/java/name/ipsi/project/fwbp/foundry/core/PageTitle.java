package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public record PageTitle(boolean show, PageTitleLevel level) {
    public PageTitle() {
        this(true, PageTitleLevel.ONE);
    }

    public PageTitle(PageTitleLevel level) {
        this(true, level);
    }
}
