package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.*;
import name.ipsi.project.fwbp.foundry.*;
import name.ipsi.project.fwbp.foundry.Packs;

import java.util.*;
import java.util.stream.Collectors;

public class Werewolf20FoundryConverter {

    public static final String MODULE_NAME = "wod-werewolf-20-core";

    public List<FoundryDocument> process(List<BookEntry> entries) {
        var documents = new ArrayList<FoundryDocument>();
        for (var entry : entries) {
            if (entry instanceof Breed b) {
                documents.add(processBreed(b));
            } else if (entry instanceof Auspice a) {
                documents.add(processAuspice(a));
            } else if (entry instanceof Tribe t) {
                documents.add(processTribe(t));
            } else if (entry instanceof Gift g) {
                documents.add(processGift(g));
            } else if (entry instanceof MeleeWeapon mw) {
                documents.add(processMeleeWeapon(mw));
            }
        }

        return documents;
    }

    private FoundryDocument processMeleeWeapon(MeleeWeapon meleeWeapon) {
        return new Item(
                meleeWeapon.id(),
                meleeWeapon.name(),
                ItemTypes.MELEE_WEAPON,
                meleeWeapon.natural() ? "systems/worldofdarkness/assets/img/items/naturalweapons.svg"
                        : "systems/worldofdarkness/assets/img/items/meleeweapons.svg",
                new MeleeWeaponData(
                        false,
                        false,
                        "",
                        "",
                        meleeWeapon.description(),
                        false,
                        meleeWeapon.silver(),
                        new MeleeWeaponData.Attack(
                                "dexterity",
                                "brawl",
                                true,
                                true
                        ),
                        new MeleeWeaponData.Damage(
                                "strength",
                                meleeWeapon.damageBonus(),
                                meleeWeapon.damageType(),
                                true,
                                true
                        ),
                        String.valueOf(meleeWeapon.difficulty()),
                        meleeWeapon.difficulty(),
                        meleeWeapon.concealment(),
                        meleeWeapon.twoHanded(),
                        meleeWeapon.twoHanded(),
                        meleeWeapon.natural(),
                        "wod.types.meleeweapon"
                ),
                "",
                "",
                0,
                Collections.singletonMap("default", 2),
                Collections.emptyMap(),
                Packs.Weapons
        );
    }

    private FoundryDocument processBreed(Breed b) {
        var text = new StringBuilder();
        text.append("<h1>").append(b.name()).append("</h1>\n");
        text.append("<p>").append(String.join("</p><p>", b.description().split("\n"))).append("</p>\n");
        text.append("<p><strong>Nicknames:</strong> ").append(b.nicknames()).append("</p>\n");
        text.append("<p><strong>Initial Gnosis:</strong> ").append(b.initialGnosis()).append("</p>\n");
        text.append("<p><strong>Beginning Gifts:</strong> ").append(b.beginningGifts().stream()
                .map(g -> String.format("@Item[%s]{%s}", g.id(), g.name())).collect(Collectors.joining(", "))).append("</p>");
        if (b.deformities() != null) {
            text.append("\n<h2>Deformities</h2>");
            text.append("\n<p>").append(b.deformities().description()).append("</p>");
            for (var d : b.deformities().deformities()) {
                text.append("\n<p><strong>").append(d.name()).append(":</strong> ").append(d.description());
            }
        }

        if (b.restrictedAbilities() != null) {
            text.append("\n<h2>Restricted Abilities</h2>");
            text.append("\n<p>").append(b.restrictedAbilities().description()).append("</p>");
            text.append("\n<p><strong>Skills:</strong> ").append(b.restrictedAbilities().skills()).append("</p>");
            text.append("\n<p><strong>Knowledges:</strong> ").append(b.restrictedAbilities().knowledges()).append("</p>");
        }

        text.append("\n<h2>Gifts</h2>");
        for (var f : b.gifts().entrySet().stream().sorted(Comparator.comparingInt(o -> o.getKey().sortKey())).toList()) {
            text.append("\n<h3>Rank ").append(f.getKey().displayName()).append(" Gifts</h3>");
            for (var g : f.getValue().stream().sorted(Comparator.comparing(Gift::name)).toList()) {
                text.append("\n<p>").append(String.format("@Item[%s]{%s}", g.id(), g.name())).append("</p>");
            }
        }

        return new Journal(
                b.id(),
                b.name(),
                text.toString(),
                null,
                Packs.Breeds
        );
    }

    private FoundryDocument processAuspice(Auspice a) {
        var text = new StringBuilder();
        text.append("<h1>").append(a.name().displayName()).append("</h1>\n");
        text.append("<p>").append(String.join("</p><p>", a.description().split("\n"))).append("</p>\n");
        text.append("<p><strong>Nicknames:</strong> ").append(a.altNames()).append("</p>\n");
        text.append("<p><strong>Initial Rage:</strong> ").append(a.initialRage()).append("</p>\n");
        text.append("<p><strong>Beginning Gifts:</strong> ").append(a.gifts().get(Rank.ONE).stream()
                .map(g -> String.format("@Item[%s]{%s}", g.id(), g.name())).collect(Collectors.joining(", "))).append("</p>");

        text.append("\n<h2>Gifts</h2>");
        for (var f : a.gifts().entrySet().stream().sorted(Comparator.comparingInt(o -> o.getKey().sortKey())).toList()) {
            text.append("\n<h3>Rank ").append(f.getKey().displayName()).append(" Gifts</h3>");
            for (var g : f.getValue().stream().sorted(Comparator.comparing(Gift::name)).toList()) {
                text.append("\n<p>").append(String.format("@Item[%s]{%s}", g.id(), g.name())).append("</p>");
            }
        }

        return new Journal(
                a.id(),
                a.name().displayName(),
                text.toString(),
                null,
                Packs.Auspices
        );
    }

    private FoundryDocument processTribe(Tribe t) {
        var text = new StringBuilder();
        text.append("<h1>").append(t.name().displayName()).append("</h1>\n");
        text.append("<p>").append(String.join("</p><p>", t.description().split("\n"))).append("</p>\n");
        text.append("<p><strong>Initial Willpower:</strong> ").append(t.initialWillpower()).append("</p>\n");
        text.append("<p><strong>Beginning Gifts:</strong> ").append(t.gifts().get(Rank.ONE).stream()
                .map(g -> String.format("@Item[%s]{%s}", g.id(), g.name())).collect(Collectors.joining(", "))).append("</p>");

        text.append("\n<h2>Gifts</h2>");
        for (var f : t.gifts().entrySet().stream().sorted(Comparator.comparingInt(o -> o.getKey().sortKey())).toList()) {
            text.append("\n<h3>Rank ").append(f.getKey().displayName()).append(" Gifts</h3>");
            for (var g : f.getValue().stream().sorted(Comparator.comparing(Gift::name)).toList()) {
                text.append("\n<p>").append(String.format("@Item[%s]{%s}", g.id(), g.name())).append("</p>");
            }
        }

        return new Journal(
                t.id(),
                t.name().displayName(),
                text.toString(),
                null,
                Packs.Tribes
        );
    }

    private FoundryDocument processGift(Gift g) {
        var description = new StringBuilder(g.description());
        description.append("\n<h2>Available To</h2>");
        for (var at : g.availableTo()) {
            description.append("\n<p>").append(at.group().name()).append(" @ Rank ").append(at.level()).append("</p>");
        }
        var gr = g.giftRoll();
        return new Item(
                g.id(),
                g.name(),
                ItemTypes.POWER,
                "systems/worldofdarkness/assets/img/items/power.svg",
                new GiftData(
                        false,
                        false,
                        "",
                        "",
                        description.toString(),
                        PowerTypes.GIFT,
                        g.availableTo().get(0).level(),
                        gr != null ? convertCharacteristic(gr.characteristicOne()) : "",
                        gr != null && gr.characteristicTwo() != null ? convertCharacteristic(gr.characteristicTwo()) : "",
                        0,
                        gr != null ? gr.difficulty() : "",
                        gr != null,
                        gr != null,
                        false,
                        false,
                        "",
                        g.system()
                ),
                "",
                null,
                0,
                Collections.singletonMap("default", 2),
                Collections.emptyMap(),
                Packs.Gifts
        );
    }

    private String convertCharacteristic(String c) {
        if (c.equals("Crafts")) {
            c = "Craft";
        }
        c = c.replaceAll("[ \\-]+", "");
        return c.toLowerCase();
    }
}
