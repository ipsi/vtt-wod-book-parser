package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageText implements PageContent {

    private final PageTextFormat format;
    @JsonProperty("content")
    private final String html;
    private final String markdown;

    public static PageText createHtmlText(String content) {
        return new PageText(PageTextFormat.HTML, content, null);
    }

    public static PageText createMarkdownText(String content) {
        return new PageText(PageTextFormat.MARKDOWN, null, content);
    }

    private PageText(PageTextFormat format, String html, String markdown) {
        this.format = format;
        this.html = html;
        this.markdown = markdown;
    }
}
