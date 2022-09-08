package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collections;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public final record Author(
        String name,
        String url,
        String discord,
        Map<String, Object> flags
) {
    public Author(String name, String url, String discord) {
        this(name, url, discord, Collections.emptyMap());
    }
}
