package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public record PageText(
        PageTextFormat format,
        @JsonProperty("content")
        String html,
        String markdown
) implements PageContent {

    public static PageText createHtmlText(String content) {
        return new PageText(PageTextFormat.HTML, content, null);
    }

    public static PageText createMarkdownText(String content) {
        return new PageText(PageTextFormat.MARKDOWN, null, content);
    }
}
