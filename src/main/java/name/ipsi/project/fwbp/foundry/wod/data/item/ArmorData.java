package name.ipsi.project.fwbp.foundry.wod.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record ArmorData(
    @JsonProperty("iscreated") boolean created,
    @JsonProperty("version") String version,
    @JsonProperty("parentid") String parentid,
    @JsonProperty("worldanvil") String worldanvil,
    @JsonProperty("description") String description,
    @JsonProperty("isequipped") boolean equipped,
    @JsonProperty("ismagical") boolean magical,
    @JsonProperty("soak") Soak soak,
    @JsonProperty("dexpenalty") Integer dexpenalty,
    @JsonProperty("forms") Forms forms,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class ArmorDataBuilder {
        private boolean created = true;
        private String version = "2.0.3";
        private String parentid = "";
        private String worldanvil = "";
        private String description = "";
        private boolean equipped = false;
        private boolean magical = false;
        private Soak soak = new Soak(0,0,0);
        private Integer dexpenalty = 0;
        private Forms forms = new Forms(true,false,false,false,false);
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public ArmorDataBuilder() {
            // Do nothing
        }
        
        public ArmorDataBuilder withCreated(boolean created){
            this.created = created;
            return this;
        }
        
        public ArmorDataBuilder withVersion(String version){
            this.version = version;
            return this;
        }
        
        public ArmorDataBuilder withParentid(String parentid){
            this.parentid = parentid;
            return this;
        }
        
        public ArmorDataBuilder withWorldanvil(String worldanvil){
            this.worldanvil = worldanvil;
            return this;
        }

        public ArmorDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public ArmorDataBuilder withEquipped(boolean equipped){
            this.equipped = equipped;
            return this;
        }
        
        public ArmorDataBuilder withMagical(boolean magical){
            this.magical = magical;
            return this;
        }
        
        public ArmorDataBuilder withSoak(Soak soak){
            this.soak = soak;
            return this;
        }
        
        public ArmorDataBuilder withDexpenalty(Integer dexpenalty){
            this.dexpenalty = dexpenalty;
            return this;
        }
        
        public ArmorDataBuilder withForms(Forms forms){
            this.forms = forms;
            return this;
        }
        
        public ArmorDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public ArmorData build() {
            return new ArmorData(
                created,
                version,
                parentid,
                worldanvil,
                description,
                equipped,
                magical,
                soak,
                dexpenalty,
                forms,
                additionalProperties
            );
        }
    }
    public record Soak(
        Integer bashing,
        Integer lethal,
        Integer aggravated
    ) {}
    
    public record Forms(
        boolean hashomid,
        boolean hasglabro,
        boolean hascrinos,
        boolean hashispo,
        boolean haslupus
    ) {}
    
}
