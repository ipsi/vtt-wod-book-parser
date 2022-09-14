package name.ipsi.project.fwbp.foundry.wod.data.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import name.ipsi.project.fwbp.foundry.core.ItemData;
import name.ipsi.project.fwbp.foundry.wod.DamageTypes;
import name.ipsi.project.fwbp.foundry.wod.WeaponConcealment;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
public record RangedWeaponData(
    @JsonProperty("iscreated") boolean created,
    @JsonProperty("version") String version,
    @JsonProperty("parentid") String parentid,
    @JsonProperty("worldanvil") String worldanvil,
    @JsonProperty("description") String description,
    @JsonProperty("isequipped") boolean equipped,
    @JsonProperty("ismagical") boolean magical,
    @JsonProperty("attack") Attack attack,
    @JsonProperty("damage") Damage damage,
    @JsonProperty("difficulty") Integer difficulty,
    @JsonProperty("conceal") WeaponConcealment conceal,
    @JsonProperty("istwohanded") boolean twohanded,
    @JsonProperty("range") Integer range,
    @JsonProperty("rate") Integer rate,
    @JsonProperty("mode") Mode mode,
    @JsonProperty("clip") Clip clip,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class RangedWeaponDataBuilder {
        private boolean created = true;
        private String version = "2.0.3";
        private String parentid = "";
        private String worldanvil = "";
        private String description = "";
        private boolean equipped = false;
        private boolean magical = false;
        private Attack attack = new Attack("","",0,true);
        private Damage damage = new Damage("",0,null,true);
        private Integer difficulty = 6;
        private WeaponConcealment conceal = null;
        private boolean twohanded = false;
        private Integer range = 0;
        private Integer rate = 0;
        private Mode mode = new Mode(false,false,false,false);
        private Clip clip = new Clip(0,0);
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public RangedWeaponDataBuilder() {
            // Do nothing
        }
        
        public RangedWeaponDataBuilder withCreated(boolean created){
            this.created = created;
            return this;
        }
        
        public RangedWeaponDataBuilder withVersion(String version){
            this.version = version;
            return this;
        }
        
        public RangedWeaponDataBuilder withParentid(String parentid){
            this.parentid = parentid;
            return this;
        }
        
        public RangedWeaponDataBuilder withWorldanvil(String worldanvil){
            this.worldanvil = worldanvil;
            return this;
        }
        
        public RangedWeaponDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public RangedWeaponDataBuilder withEquipped(boolean equipped){
            this.equipped = equipped;
            return this;
        }
        
        public RangedWeaponDataBuilder withMagical(boolean magical){
            this.magical = magical;
            return this;
        }
        
        public RangedWeaponDataBuilder withAttack(Attack attack){
            this.attack = attack;
            return this;
        }
        
        public RangedWeaponDataBuilder withDamage(Damage damage){
            this.damage = damage;
            return this;
        }
        
        public RangedWeaponDataBuilder withDifficulty(Integer difficulty){
            this.difficulty = difficulty;
            return this;
        }
        
        public RangedWeaponDataBuilder withConceal(WeaponConcealment conceal){
            this.conceal = conceal;
            return this;
        }
        
        public RangedWeaponDataBuilder withTwohanded(boolean twohanded){
            this.twohanded = twohanded;
            return this;
        }
        
        public RangedWeaponDataBuilder withRange(Integer range){
            this.range = range;
            return this;
        }
        
        public RangedWeaponDataBuilder withRate(Integer rate){
            this.rate = rate;
            return this;
        }
        
        public RangedWeaponDataBuilder withMode(Mode mode){
            this.mode = mode;
            return this;
        }
        
        public RangedWeaponDataBuilder withClip(Clip clip){
            this.clip = clip;
            return this;
        }
        
        public RangedWeaponDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public RangedWeaponData build() {
            return new RangedWeaponData(
                created,
                version,
                parentid,
                worldanvil,
                description,
                equipped,
                magical,
                attack,
                damage,
                difficulty,
                conceal,
                twohanded,
                range,
                rate,
                mode,
                clip,
                additionalProperties
            );
        }
    }
    public record Mode(
        boolean hasreload,
        boolean hasburst,
        boolean hasfullauto,
        boolean hasspray
    ) {}
    
    public record Damage(
        String attribute,
        Integer bonus,
        DamageTypes type,
        boolean rollable
    ) {}
    
    public record Attack(
        String attribute,
        String ability,
        Integer accuracy,
        boolean rollable
    ) {}
    
    public record Clip(
        Integer value,
        Integer max
    ) {}
    
}
