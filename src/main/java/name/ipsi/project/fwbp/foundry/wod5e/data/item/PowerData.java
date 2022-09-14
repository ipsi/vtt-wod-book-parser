package name.ipsi.project.fwbp.foundry.wod5e.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record PowerData(
    @JsonProperty("description") String description,
    @JsonProperty("discipline") String discipline,
    @JsonProperty("duration") String duration,
    @JsonProperty("level") Integer level,
    @JsonProperty("dice1") String dice1,
    @JsonProperty("dice2") String dice2,
    @JsonProperty("cost") String cost,
    @JsonProperty("rollable") boolean rollable,
    @JsonProperty("skill") boolean skill,
    @JsonProperty("amalgam") boolean amalgam,
    @JsonProperty("rouse") boolean rouse,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class PowerDataBuilder {
        private String description = "";
        private String discipline = "rituals";
        private String duration = "";
        private Integer level = 1;
        private String dice1 = "discipline";
        private String dice2 = "discipline";
        private String cost = "";
        private boolean rollable = false;
        private boolean skill = false;
        private boolean amalgam = false;
        private boolean rouse = false;
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public PowerDataBuilder() {
            // Do nothing
        }
        
        public PowerDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public PowerDataBuilder withDiscipline(String discipline){
            this.discipline = discipline;
            return this;
        }
        
        public PowerDataBuilder withDuration(String duration){
            this.duration = duration;
            return this;
        }
        
        public PowerDataBuilder withLevel(Integer level){
            this.level = level;
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
        
        public PowerDataBuilder withCost(String cost){
            this.cost = cost;
            return this;
        }
        
        public PowerDataBuilder withRollable(boolean rollable){
            this.rollable = rollable;
            return this;
        }
        
        public PowerDataBuilder withSkill(boolean skill){
            this.skill = skill;
            return this;
        }
        
        public PowerDataBuilder withAmalgam(boolean amalgam){
            this.amalgam = amalgam;
            return this;
        }
        
        public PowerDataBuilder withRouse(boolean rouse){
            this.rouse = rouse;
            return this;
        }
        
        public PowerDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public PowerData build() {
            return new PowerData(
                description,
                discipline,
                duration,
                level,
                dice1,
                dice2,
                cost,
                rollable,
                skill,
                amalgam,
                rouse,
                additionalProperties
            );
        }
    }
}
