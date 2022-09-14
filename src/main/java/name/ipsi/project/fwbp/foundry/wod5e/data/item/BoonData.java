package name.ipsi.project.fwbp.foundry.wod5e.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record BoonData(
    @JsonProperty("description") String description,
    @JsonProperty("boontype") String boontype,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class BoonDataBuilder {
        private String description = "";
        private String boontype = "Trivial";
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public BoonDataBuilder() {
            // Do nothing
        }
        
        public BoonDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public BoonDataBuilder withBoontype(String boontype){
            this.boontype = boontype;
            return this;
        }
        
        public BoonDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public BoonData build() {
            return new BoonData(
                description,
                boontype,
                additionalProperties
            );
        }
    }
}
