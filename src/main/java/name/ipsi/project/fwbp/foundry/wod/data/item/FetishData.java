package name.ipsi.project.fwbp.foundry.wod.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;
import name.ipsi.project.fwbp.foundry.wod.werewolf.FetishType;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record FetishData(
    @JsonProperty("iscreated") boolean created,
    @JsonProperty("version") String version,
    @JsonProperty("parentid") String parentid,
    @JsonProperty("worldanvil") String worldanvil,
    @JsonProperty("description") String description,
    @JsonProperty("type") FetishType type,
    @JsonProperty("level") String level,
    @JsonProperty("value") Integer value,
    @JsonProperty("max") Integer max,
    @JsonProperty("isequipped") boolean equipped,
    @JsonProperty("ismagical") boolean magical,
    @JsonProperty("gnosis") Integer gnosis,
    @JsonProperty("difficulty") Integer difficulty,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class FetishDataBuilder {
        private boolean created = true;
        private String version = "2.0.3";
        private String parentid = "";
        private String worldanvil = "";
        private String description = "";
        private FetishType type = null;
        private String level = "0";
        private Integer value = 0;
        private Integer max = 5;
        private boolean equipped = false;
        private boolean magical = false;
        private Integer gnosis = 0;
        private Integer difficulty = 6;
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public FetishDataBuilder() {
            // Do nothing
        }
        
        public FetishDataBuilder withCreated(boolean created){
            this.created = created;
            return this;
        }
        
        public FetishDataBuilder withVersion(String version){
            this.version = version;
            return this;
        }
        
        public FetishDataBuilder withParentid(String parentid){
            this.parentid = parentid;
            return this;
        }
        
        public FetishDataBuilder withWorldanvil(String worldanvil){
            this.worldanvil = worldanvil;
            return this;
        }
        
        public FetishDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public FetishDataBuilder withType(FetishType type){
            this.type = type;
            return this;
        }
        
        public FetishDataBuilder withLevel(String level){
            this.level = level;
            return this;
        }
        
        public FetishDataBuilder withValue(Integer value){
            this.value = value;
            return this;
        }
        
        public FetishDataBuilder withMax(Integer max){
            this.max = max;
            return this;
        }
        
        public FetishDataBuilder withEquipped(boolean equipped){
            this.equipped = equipped;
            return this;
        }
        
        public FetishDataBuilder withMagical(boolean magical){
            this.magical = magical;
            return this;
        }
        
        public FetishDataBuilder withGnosis(Integer gnosis){
            this.gnosis = gnosis;
            return this;
        }
        
        public FetishDataBuilder withDifficulty(Integer difficulty){
            this.difficulty = difficulty;
            return this;
        }
        
        public FetishDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public FetishData build() {
            return new FetishData(
                created,
                version,
                parentid,
                worldanvil,
                description,
                type,
                level,
                value,
                max,
                equipped,
                magical,
                gnosis,
                difficulty,
                additionalProperties
            );
        }
    }
}
