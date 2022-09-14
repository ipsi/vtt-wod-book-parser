package name.ipsi.project.fwbp.foundry.wod.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record ExperienceData(
    @JsonProperty("iscreated") boolean created,
    @JsonProperty("version") String version,
    @JsonProperty("parentid") String parentid,
    @JsonProperty("worldanvil") String worldanvil,
    @JsonProperty("description") String description,
    @JsonProperty("isspent") boolean spent,
    @JsonProperty("amount") Integer amount,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class ExperienceDataBuilder {
        private boolean created = true;
        private String version = "2.0.3";
        private String parentid = "";
        private String worldanvil = "";
        private String description = "";
        private boolean spent = true;
        private Integer amount = 0;
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public ExperienceDataBuilder() {
            // Do nothing
        }
        
        public ExperienceDataBuilder withCreated(boolean created){
            this.created = created;
            return this;
        }
        
        public ExperienceDataBuilder withVersion(String version){
            this.version = version;
            return this;
        }
        
        public ExperienceDataBuilder withParentid(String parentid){
            this.parentid = parentid;
            return this;
        }
        
        public ExperienceDataBuilder withWorldanvil(String worldanvil){
            this.worldanvil = worldanvil;
            return this;
        }
        
        public ExperienceDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public ExperienceDataBuilder withSpent(boolean spent){
            this.spent = spent;
            return this;
        }
        
        public ExperienceDataBuilder withAmount(Integer amount){
            this.amount = amount;
            return this;
        }
        
        public ExperienceDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public ExperienceData build() {
            return new ExperienceData(
                created,
                version,
                parentid,
                worldanvil,
                description,
                spent,
                amount,
                additionalProperties
            );
        }
    }
}
