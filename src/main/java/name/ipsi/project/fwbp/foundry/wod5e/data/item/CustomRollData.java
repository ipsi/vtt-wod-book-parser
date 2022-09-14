package name.ipsi.project.fwbp.foundry.wod5e.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record CustomRollData(
    @JsonProperty("description") String description,
    @JsonProperty("dice1") String dice1,
    @JsonProperty("dice2") String dice2,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class CustomRollDataBuilder {
        private String description = "";
        private String dice1 = "";
        private String dice2 = "";
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public CustomRollDataBuilder() {
            // Do nothing
        }
        
        public CustomRollDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public CustomRollDataBuilder withDice1(String dice1){
            this.dice1 = dice1;
            return this;
        }
        
        public CustomRollDataBuilder withDice2(String dice2){
            this.dice2 = dice2;
            return this;
        }
        
        public CustomRollDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public CustomRollData build() {
            return new CustomRollData(
                description,
                dice1,
                dice2,
                additionalProperties
            );
        }
    }
}
