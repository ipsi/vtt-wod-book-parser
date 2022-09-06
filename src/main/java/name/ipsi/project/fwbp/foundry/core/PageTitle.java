package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public class PageTitle {
    private final boolean show;
    private final PageTitleLevel level;

    public PageTitle() {
        this.show = true;
        this.level = PageTitleLevel.ONE;
    }

    public PageTitle(PageTitleLevel level) {
        this.show = true;
        this.level = level;
    }

    public PageTitle(boolean show, PageTitleLevel level) {
        this.show = show;
        this.level = level;
    }

    public boolean isShow() {
        return show;
    }

    public PageTitleLevel getLevel() {
        return level;
    }
}
