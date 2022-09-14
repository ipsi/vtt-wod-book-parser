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
public record MeleeWeaponData(
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
    @JsonProperty("isnatural") boolean natural,
    @JsonAnyGetter
    @JsonAnySetter
    Map<String, Object> additionalData
) implements ItemData {
    public static class MeleeWeaponDataBuilder {
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
        private boolean natural = false;
        
        private final Map<String, Object> additionalProperties = new HashMap<>();
        
        public MeleeWeaponDataBuilder() {
            // Do nothing
        }
        
        public MeleeWeaponDataBuilder withCreated(boolean created){
            this.created = created;
            return this;
        }
        
        public MeleeWeaponDataBuilder withVersion(String version){
            this.version = version;
            return this;
        }
        
        public MeleeWeaponDataBuilder withParentid(String parentid){
            this.parentid = parentid;
            return this;
        }
        
        public MeleeWeaponDataBuilder withWorldanvil(String worldanvil){
            this.worldanvil = worldanvil;
            return this;
        }
        
        public MeleeWeaponDataBuilder withDescription(String description){
            this.description = description;
            return this;
        }
        
        public MeleeWeaponDataBuilder withEquipped(boolean equipped){
            this.equipped = equipped;
            return this;
        }
        
        public MeleeWeaponDataBuilder withMagical(boolean magical){
            this.magical = magical;
            return this;
        }
        
        public MeleeWeaponDataBuilder withAttack(Attack attack){
            this.attack = attack;
            return this;
        }
        
        public MeleeWeaponDataBuilder withDamage(Damage damage){
            this.damage = damage;
            return this;
        }
        
        public MeleeWeaponDataBuilder withDifficulty(Integer difficulty){
            this.difficulty = difficulty;
            return this;
        }
        
        public MeleeWeaponDataBuilder withConceal(WeaponConcealment conceal){
            this.conceal = conceal;
            return this;
        }
        
        public MeleeWeaponDataBuilder withTwohanded(boolean twohanded){
            this.twohanded = twohanded;
            return this;
        }
        
        public MeleeWeaponDataBuilder withNatural(boolean natural){
            this.natural = natural;
            return this;
        }
        
        public MeleeWeaponDataBuilder withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }
        
        public MeleeWeaponData build() {
            return new MeleeWeaponData(
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
                natural,
                additionalProperties
            );
        }
    }
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
    
}
