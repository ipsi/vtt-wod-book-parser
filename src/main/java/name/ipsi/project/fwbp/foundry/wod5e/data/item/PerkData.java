package name.ipsi.project.fwbp.foundry.wod5e.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record PerkData(
    @JsonProperty("description") String description,
    @JsonProperty("edge") String edge,
    @JsonProperty("duration") String duration,
    @JsonProperty("dice1") String dice1,
    @JsonProperty("dice2") String dice2,
    @JsonProperty("cost") String cost,
    @JsonProperty("rollable") boolean rollable,
    @JsonProperty("skill") boolean skill,
    @JsonProperty("amalgam") boolean amalgam,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class PerkDataBuilder {
        private String description = "";
        private String edge = "arsenal";
        private String duration = "";
        private String dice1 = "edge";
        private String dice2 = "edge";
        private String cost = "";
        private boolean rollable = false;
        private boolean skill = false;
        private boolean amalgam = false;
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public PerkDataBuilder() {
            // Do nothing
        }
        
        public PerkDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public PerkDataBuilder withEdge(String edge){
            this.edge = edge;
            return this;
        }
        
        public PerkDataBuilder withDuration(String duration){
            this.duration = duration;
            return this;
        }
        
        public PerkDataBuilder withDice1(String dice1){
            this.dice1 = dice1;
            return this;
        }
        
        public PerkDataBuilder withDice2(String dice2){
            this.dice2 = dice2;
            return this;
        }
        
        public PerkDataBuilder withCost(String cost){
            this.cost = cost;
            return this;
        }
        
        public PerkDataBuilder withRollable(boolean rollable){
            this.rollable = rollable;
            return this;
        }
        
        public PerkDataBuilder withSkill(boolean skill){
            this.skill = skill;
            return this;
        }
        
        public PerkDataBuilder withAmalgam(boolean amalgam){
            this.amalgam = amalgam;
            return this;
        }
        
        public PerkDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public PerkData build() {
            return new PerkData(
                description,
                edge,
                duration,
                dice1,
                dice2,
                cost,
                rollable,
                skill,
                amalgam,
                additionalProperties
            );
        }
    }
}
