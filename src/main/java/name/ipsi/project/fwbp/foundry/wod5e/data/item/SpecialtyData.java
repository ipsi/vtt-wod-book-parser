package name.ipsi.project.fwbp.foundry.wod5e.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record SpecialtyData(
    @JsonProperty("description") String description,
    @JsonProperty("skill") String skill,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class SpecialtyDataBuilder {
        private String description = "";
        private String skill = "athletics";
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public SpecialtyDataBuilder() {
            // Do nothing
        }
        
        public SpecialtyDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public SpecialtyDataBuilder withSkill(String skill){
            this.skill = skill;
            return this;
        }
        
        public SpecialtyDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public SpecialtyData build() {
            return new SpecialtyData(
                description,
                skill,
                additionalProperties
            );
        }
    }
}
