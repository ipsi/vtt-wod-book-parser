package name.ipsi.project.fwbp.foundry.wod.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;
import name.ipsi.project.fwbp.foundry.wod.FeatureTypes;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record FeatureData(
    @JsonProperty("iscreated") boolean created,
    @JsonProperty("version") String version,
    @JsonProperty("parentid") String parentid,
    @JsonProperty("worldanvil") String worldanvil,
    @JsonProperty("description") String description,
    @JsonProperty("type") FeatureTypes type,
    @JsonProperty("level") String level,
    @JsonProperty("value") Integer value,
    @JsonProperty("max") Integer max,
    @JsonProperty("details") String details,
    @JsonProperty("isrollable") boolean rollable,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class FeatureDataBuilder {
        private boolean created = true;
        private String version = "2.0.3";
        private String parentid = "";
        private String worldanvil = "";
        private String description = "";
        private FeatureTypes type = null;
        private String level = "0";
        private Integer value = 0;
        private Integer max = 5;
        private String details = "";
        private boolean rollable = false;
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public FeatureDataBuilder() {
            // Do nothing
        }
        
        public FeatureDataBuilder withCreated(boolean created){
            this.created = created;
            return this;
        }
        
        public FeatureDataBuilder withVersion(String version){
            this.version = version;
            return this;
        }
        
        public FeatureDataBuilder withParentid(String parentid){
            this.parentid = parentid;
            return this;
        }
        
        public FeatureDataBuilder withWorldanvil(String worldanvil){
            this.worldanvil = worldanvil;
            return this;
        }
        
        public FeatureDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public FeatureDataBuilder withType(FeatureTypes type){
            this.type = type;
            return this;
        }
        
        public FeatureDataBuilder withLevel(String level){
            this.level = level;
            return this;
        }
        
        public FeatureDataBuilder withValue(Integer value){
            this.value = value;
            return this;
        }
        
        public FeatureDataBuilder withMax(Integer max){
            this.max = max;
            return this;
        }
        
        public FeatureDataBuilder withDetails(String details){
            this.details = details;
            return this;
        }
        
        public FeatureDataBuilder withRollable(boolean rollable){
            this.rollable = rollable;
            return this;
        }
        
        public FeatureDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public FeatureData build() {
            return new FeatureData(
                created,
                version,
                parentid,
                worldanvil,
                description,
                type,
                level,
                value,
                max,
                details,
                rollable,
                additionalProperties
            );
        }
    }
}
