package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public record PageImage() implements PageContent {
}
