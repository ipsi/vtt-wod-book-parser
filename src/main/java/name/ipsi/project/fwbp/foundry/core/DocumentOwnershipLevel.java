package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum DocumentOwnershipLevel {
    INHERIT(-1.0),
    NONE(0.0),
    LIMITED(1.0),
    OBSERVER(2.0),
    OWNER(3.0);

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
    private final double jsonValue;

    DocumentOwnershipLevel(double jsonValue) {
        this.jsonValue = jsonValue;
    }
}
