package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DocumentTypes {
    @JsonProperty("Adventure")
    ADVENTURE,
    @JsonProperty("Item")
    ITEM,
    @JsonProperty("JournalEntry")
    JOURNAL_ENTRY
}
