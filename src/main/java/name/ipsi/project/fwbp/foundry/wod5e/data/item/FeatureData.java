package name.ipsi.project.fwbp.foundry.wod5e.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record FeatureData(
    @JsonProperty("description") String description,
    @JsonProperty("featuretype") String featuretype,
    @JsonProperty("points") Integer points,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class FeatureDataBuilder {
        private String description = "";
        private String featuretype = "merit";
        private Integer points = 0;
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public FeatureDataBuilder() {
            // Do nothing
        }
        
        public FeatureDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public FeatureDataBuilder withFeaturetype(String featuretype){
            this.featuretype = featuretype;
            return this;
        }
        
        public FeatureDataBuilder withPoints(Integer points){
            this.points = points;
            return this;
        }
        
        public FeatureDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public FeatureData build() {
            return new FeatureData(
                description,
                featuretype,
                points,
                additionalProperties
            );
        }
    }
}
