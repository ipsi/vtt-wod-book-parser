package name.ipsi.project.fwbp.foundry.wod.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record RoteData(
    @JsonProperty("iscreated") boolean created,
    @JsonProperty("version") String version,
    @JsonProperty("parentid") String parentid,
    @JsonProperty("worldanvil") String worldanvil,
    @JsonProperty("description") String description,
    @JsonProperty("correspondence") Integer correspondence,
    @JsonProperty("entropy") Integer entropy,
    @JsonProperty("forces") Integer forces,
    @JsonProperty("life") Integer life,
    @JsonProperty("matter") Integer matter,
    @JsonProperty("mind") Integer mind,
    @JsonProperty("prime") Integer prime,
    @JsonProperty("spirit") Integer spirit,
    @JsonProperty("time") Integer time,
    @JsonProperty("instrument") Instrument instrument,
    @JsonProperty("spendingtime") Integer spendingTime,
    @JsonProperty("spelltype") String spelltype,
    @JsonProperty("isextended") boolean extended,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class RoteDataBuilder {
        private boolean created = true;
        private String version = "2.0.3";
        private String parentid = "";
        private String worldanvil = "";
        private String description = "";
        private Integer correspondence = 0;
        private Integer entropy = 0;
        private Integer forces = 0;
        private Integer life = 0;
        private Integer matter = 0;
        private Integer mind = 0;
        private Integer prime = 0;
        private Integer spirit = 0;
        private Integer time = 0;
        private Instrument instrument = new Instrument(false,false);
        private Integer spendingTime = 0;
        private String spelltype = "";
        private boolean extended = false;
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public RoteDataBuilder() {
            // Do nothing
        }
        
        public RoteDataBuilder withCreated(boolean created){
            this.created = created;
            return this;
        }
        
        public RoteDataBuilder withVersion(String version){
            this.version = version;
            return this;
        }
        
        public RoteDataBuilder withParentid(String parentid){
            this.parentid = parentid;
            return this;
        }
        
        public RoteDataBuilder withWorldanvil(String worldanvil){
            this.worldanvil = worldanvil;
            return this;
        }
        
        public RoteDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public RoteDataBuilder withCorrespondence(Integer correspondence){
            this.correspondence = correspondence;
            return this;
        }
        
        public RoteDataBuilder withEntropy(Integer entropy){
            this.entropy = entropy;
            return this;
        }
        
        public RoteDataBuilder withForces(Integer forces){
            this.forces = forces;
            return this;
        }
        
        public RoteDataBuilder withLife(Integer life){
            this.life = life;
            return this;
        }
        
        public RoteDataBuilder withMatter(Integer matter){
            this.matter = matter;
            return this;
        }
        
        public RoteDataBuilder withMind(Integer mind){
            this.mind = mind;
            return this;
        }
        
        public RoteDataBuilder withPrime(Integer prime){
            this.prime = prime;
            return this;
        }
        
        public RoteDataBuilder withSpirit(Integer spirit){
            this.spirit = spirit;
            return this;
        }
        
        public RoteDataBuilder withTime(Integer time){
            this.time = time;
            return this;
        }
        
        public RoteDataBuilder withInstrument(Instrument instrument){
            this.instrument = instrument;
            return this;
        }
        
        public RoteDataBuilder withSpendingTime(Integer spendingTime){
            this.spendingTime = spendingTime;
            return this;
        }
        
        public RoteDataBuilder withSpelltype(String spelltype){
            this.spelltype = spelltype;
            return this;
        }
        
        public RoteDataBuilder withExtended(boolean extended){
            this.extended = extended;
            return this;
        }
        
        public RoteDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public RoteData build() {
            return new RoteData(
                created,
                version,
                parentid,
                worldanvil,
                description,
                correspondence,
                entropy,
                forces,
                life,
                matter,
                mind,
                prime,
                spirit,
                time,
                instrument,
                spendingTime,
                spelltype,
                extended,
                additionalProperties
            );
        }
    }
    public record Instrument(
        boolean personalized,
        boolean unique
    ) {}
    
}
