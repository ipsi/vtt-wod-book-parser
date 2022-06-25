package name.ipsi.project.fwbp.foundry;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DocumentTypes {
    @JsonProperty("Item")
    ITEM,
    @JsonProperty("JournalEntry")
    JOURNAL_ENTRY
}
