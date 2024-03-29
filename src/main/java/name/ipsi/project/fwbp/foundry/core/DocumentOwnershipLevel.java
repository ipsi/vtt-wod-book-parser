package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum DocumentOwnershipLevel {
    INHERIT(-1),
    NONE(0),
    LIMITED(1),
    OBSERVER(2),
    OWNER(3);

    public static Map<String, DocumentOwnershipLevel> defaultInherit() {
        var value = new HashMap<String, DocumentOwnershipLevel>();
        value.put("default", DocumentOwnershipLevel.INHERIT);
        return value;
    }

    public static Map<String, DocumentOwnershipLevel> defaultNone() {
        var value = new HashMap<String, DocumentOwnershipLevel>();
        value.put("default", DocumentOwnershipLevel.NONE);
        return value;
    }

    public static Map<String, DocumentOwnershipLevel> defaultLimited() {
        var value = new HashMap<String, DocumentOwnershipLevel>();
        value.put("default", DocumentOwnershipLevel.LIMITED);
        return value;
    }

    public static Map<String, DocumentOwnershipLevel> defaultObserver() {
        var value = new HashMap<String, DocumentOwnershipLevel>();
        value.put("default", DocumentOwnershipLevel.OBSERVER);
        return value;
    }

    @JsonValue
    private final int jsonValue;

    DocumentOwnershipLevel(int jsonValue) {
        this.jsonValue = jsonValue;
    }
}
