package name.ipsi.project.fwbp.foundry.wod.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;
import name.ipsi.project.fwbp.foundry.wod.PowerTypes;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record PowerData(
    @JsonProperty("iscreated") boolean created,
    @JsonProperty("version") String version,
    @JsonProperty("parentid") String parentid,
    @JsonProperty("worldanvil") String worldanvil,
    @JsonProperty("description") String description,
    @JsonProperty("type") PowerTypes type,
    @JsonProperty("level") String level,
    @JsonProperty("value") Integer value,
    @JsonProperty("max") Integer max,
    @JsonProperty("dice1") String dice1,
    @JsonProperty("dice2") String dice2,
    @JsonProperty("bonus") Integer bonus,
    @JsonProperty("difficulty") Integer difficulty,
    @JsonProperty("isrollable") boolean rollable,
    @JsonProperty("isactive") boolean active,
    @JsonProperty("spendwillpower") boolean spendWillpower,
    @JsonProperty("spendrage") boolean spendRage,
    @JsonProperty("spendgnosis") boolean spendGnosis,
    @JsonProperty("spendblood") boolean spendBlood,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class PowerDataBuilder {
        private boolean created = true;
        private String version = "2.0.3";
        private String parentid = "";
        private String worldanvil = "";
        private String description = "";
        private PowerTypes type = null;
        private String level = "0";
        private Integer value = 0;
        private Integer max = 5;
        private String dice1 = "";
        private String dice2 = "";
        private Integer bonus = 0;
        private Integer difficulty = 0;
        private boolean rollable = true;
        private boolean active = false;
        private boolean spendWillpower = false;
        private boolean spendRage = false;
        private boolean spendGnosis = false;
        private boolean spendBlood = false;
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public PowerDataBuilder() {
            // Do nothing
        }
        
        public PowerDataBuilder withCreated(boolean created){
            this.created = created;
            return this;
        }
        
        public PowerDataBuilder withVersion(String version){
            this.version = version;
            return this;
        }
        
        public PowerDataBuilder withParentid(String parentid){
            this.parentid = parentid;
            return this;
        }
        
        public PowerDataBuilder withWorldanvil(String worldanvil){
            this.worldanvil = worldanvil;
            return this;
        }
        
        public PowerDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public PowerDataBuilder withType(PowerTypes type){
            this.type = type;
            return this;
        }
        
        public PowerDataBuilder withLevel(String level){
            this.level = level;
            return this;
        }
        
        public PowerDataBuilder withValue(Integer value){
            this.value = value;
            return this;
        }
        
        public PowerDataBuilder withMax(Integer max){
            this.max = max;
            return this;
        }
        
        public PowerDataBuilder withDice1(String dice1){
            this.dice1 = dice1;
            return this;
        }
        
        public PowerDataBuilder withDice2(String dice2){
            this.dice2 = dice2;
            return this;
        }
        
        public PowerDataBuilder withBonus(Integer bonus){
            this.bonus = bonus;
            return this;
        }
        
        public PowerDataBuilder withDifficulty(Integer difficulty){
            this.difficulty = difficulty;
            return this;
        }
        
        public PowerDataBuilder withRollable(boolean rollable){
            this.rollable = rollable;
            return this;
        }
        
        public PowerDataBuilder withActive(boolean active){
            this.active = active;
            return this;
        }
        
        public PowerDataBuilder withSpendWillpower(boolean spendWillpower){
            this.spendWillpower = spendWillpower;
            return this;
        }
        
        public PowerDataBuilder withSpendRage(boolean spendRage){
            this.spendRage = spendRage;
            return this;
        }
        
        public PowerDataBuilder withSpendGnosis(boolean spendGnosis){
            this.spendGnosis = spendGnosis;
            return this;
        }
        
        public PowerDataBuilder withSpendBlood(boolean spendBlood){
            this.spendBlood = spendBlood;
            return this;
        }
        
        public PowerDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public PowerData build() {
            return new PowerData(
                created,
                version,
                parentid,
                worldanvil,
                description,
                type,
                level,
                value,
                max,
                dice1,
                dice2,
                bonus,
                difficulty,
                rollable,
                active,
                spendWillpower,
                spendRage,
                spendGnosis,
                spendBlood,
                additionalProperties
            );
        }
    }
}
