package name.ipsi.project.fwbp.foundry.wod.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.books.shared.MeleeWeapon;
import name.ipsi.project.fwbp.books.werewolf.*;
import name.ipsi.project.fwbp.foundry.core.*;
import name.ipsi.project.fwbp.foundry.templating.Templater;
import name.ipsi.project.fwbp.foundry.wod.ItemTypes;
import name.ipsi.project.fwbp.foundry.wod.MeleeWeaponData;
import name.ipsi.project.fwbp.foundry.wod.PowerTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Werewolf20FoundryConverter {
    public static final Logger log = LoggerFactory.getLogger(Werewolf20FoundryConverter.class);

    private Map<String, String> folderIdsByName;
    private double breedSort;
    private double auspiceSort;
    private double tribeSort;

    public static final String MODULE_NAME = "wod-werewolf-20-core";

    public List<FoundryDocument> process(List<BookEntry> entries) {
        log.trace("Converting book entries");
        var documents = new ArrayList<FoundryDocument>();
        for (var entry : entries) {
            log.trace("Converting book entry {}", entry.getClass().getSimpleName());
            if (entry instanceof Breed b) {
                documents.add(processBreed(b));
            } else if (entry instanceof Auspice a) {
                documents.add(processAuspice(a));
            } else if (entry instanceof Tribe t) {
                documents.add(processTribe(t));
            } else if (entry instanceof Gift g) {
                documents.add(processGift(g, null));
            } else if (entry instanceof MeleeWeapon mw) {
                documents.add(processMeleeWeapon(mw, null));
            }
        }

        return documents;
    }

    public Adventure processAsAdventure(List<BookEntry> entries) {
        log.trace("Converting book entries tp adventure");

        folderIdsByName = new HashMap<>();
        folderIdsByName.put("Garou", FoundryUtils.generateId("folder", "garou"));
        folderIdsByName.put("Gifts - Garou", FoundryUtils.generateId("folder", "gifts-garou"));
        folderIdsByName.put("Weapons - Melee", FoundryUtils.generateId("folder", "weapons-melee"));

        var items = new ArrayList<Item>();
        var journals = new ArrayList<Journal>();
        var folders = new ArrayList<Folder>();
        List<Page> breedJournalPages = new ArrayList<>();
        var breedJournal = new Journal(FoundryUtils.generateId("journal", "breeds"), "Breeds", folderIdsByName.get("Garou"), 1.0, Collections.emptyMap(), breedJournalPages, DocumentOwnershipLevel.defaultObserver());
        journals.add(breedJournal);
        List<Page> auspiceJournalPages = new ArrayList<>();
        var auspiceJournal = new Journal(FoundryUtils.generateId("journal", "auspices"), "Auspices", folderIdsByName.get("Garou"), 2.0, Collections.emptyMap(), auspiceJournalPages, DocumentOwnershipLevel.defaultObserver());
        journals.add(auspiceJournal);
        List<Page> tribeJournalPages = new ArrayList<>();
        var tribeJournal = new Journal(FoundryUtils.generateId("journal", "tribes"), "Tribes", folderIdsByName.get("Garou"), 3.0, Collections.emptyMap(), tribeJournalPages, DocumentOwnershipLevel.defaultObserver());
        journals.add(tribeJournal);

        folders.add(new Folder(folderIdsByName.get("Garou"), "Garou", DocumentTypes.JOURNAL_ENTRY, FolderSortingModes.MANUAL, 1.0, null, Collections.emptyMap(), null, null));
        folders.add(new Folder(folderIdsByName.get("Gifts - Garou"), "Gifts - Garou", DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, 1.0, null, Collections.emptyMap(), null, null));
        folders.add(new Folder(folderIdsByName.get("Weapons - Melee"), "Weapons - Melee", DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, 2.0, null, Collections.emptyMap(), null, null));

        for (var entry : entries) {
            log.trace("Converting book entry {}", entry.getClass().getSimpleName());
            if (entry instanceof Breed b) {
                var j = processBreed(b);
                breedJournalPages.add(Page.createTextPage(j.getId(), j.getName(), j.getContent()));
            } else if (entry instanceof Auspice a) {
                var j = processAuspice(a);
                auspiceJournalPages.add(Page.createTextPage(j.getId(), j.getName(), j.getContent()));
            } else if (entry instanceof Tribe t) {
                var j = processTribe(t);
                tribeJournalPages.add(Page.createTextPage(
                        j.getId(),
                        j.getName(),
                        j.getContent(),
                        // BSDs are technically ST-only so hide from players
                        t.name() == Tribes.BLACK_SPIRAL_DANCERS ? DocumentOwnershipLevel.defaultNone() : DocumentOwnershipLevel.defaultInherit()
                ));
            } else if (entry instanceof Gift g) {
                items.add(processGift(g, folderIdsByName.get("Gifts - Garou")));
            } else if (entry instanceof MeleeWeapon mw) {
                items.add(processMeleeWeapon(mw, folderIdsByName.get("Weapons - Melee")));
            }
        }

        return new Adventure(
                FoundryUtils.generateId("adventure", "werewolf-20"),
                "Werewolf: the Apocalypse 20th Anniversary Edition",
                "modules/" + MODULE_NAME + "/images/adventure-cover.jpeg",
                "<p>An automated conversion of the W:tA 20th Anniversary PDF into a Foundry Module</p>",
                Collections.emptyList(),
                Collections.emptyList(),
                items,
                Collections.emptyList(),
                journals,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                folders,
                1.0,
                Collections.emptyMap(),
                "Werewolf: the Apocalypse 20th Anniversary Edition",
                null
        );
    }

    private Item processMeleeWeapon(MeleeWeapon meleeWeapon, String folder) {
        log.trace("Converting melee weapon");
        return new Item(
                meleeWeapon.id(),
                meleeWeapon.name(),
                ItemTypes.MELEE_WEAPON,
                meleeWeapon.natural() ? "systems/worldofdarkness/assets/img/items/naturalweapons.svg"
                        : "systems/worldofdarkness/assets/img/items/meleeweapons.svg",
                new MeleeWeaponData(
                        false,
                        "",
                        "",
                        meleeWeapon.description(),
                        false,
                        meleeWeapon.silver(),
                        new MeleeWeaponData.Attack(
                                "dexterity",
                                "brawl",
                                true
                        ),
                        new MeleeWeaponData.Damage(
                                "strength",
                                meleeWeapon.damageBonus(),
                                meleeWeapon.damageType(),
                                true
                        ),
                        String.valueOf(meleeWeapon.difficulty()),
                        meleeWeapon.difficulty(),
                        meleeWeapon.concealment(),
                        meleeWeapon.twoHanded(),
                        meleeWeapon.natural(),
                        "wod.types.meleeweapon"
                ),
                "",
                folder,
                0,
                Collections.singletonMap("default", 2),
                Collections.emptyMap(),
                Packs.Weapons
        );
    }

    private Journal processBreed(Breed b) {
        log.trace("Converting Breed {}", b.name());
        String html;
        try {
            log.debug("Running template for breed {}", b.name());
            html = Templater.instance().compile("breed").apply(b);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Journal(
                b.id(),
                b.name(),
                html,
                null,
                breedSort++,
                Packs.Breeds
        );
    }

    private Journal processAuspice(Auspice a) {
        log.trace("Converting Auspice {}", a.name());
        var text = new StringBuilder();
        log.trace("Adding titles");
        text.append("<p><em>").append(String.join(", ", a.altNames())).append("</em></p>\n");
        log.trace("Adding description");
        text.append("<p>").append(String.join("</p><p>", a.description().split("\n"))).append("</p>\n");
        log.trace("Adding initial rage");
        text.append("<p><strong>Initial Rage:</strong> ").append(a.initialRage()).append("</p>\n");
        log.trace("Adding stereotype");
        text.append("<p><strong>Stereotype:</strong> <em>").append(a.stereotype()).append("</em></p>\n");
        log.trace("Adding quote");
        text.append("<p><strong>Quote:</strong> <em>").append(a.quote()).append("</em></p>\n");

//        appendGifts(text, a.gifts());

        return new Journal(
                a.id(),
                a.name(),
                text.toString(),
                null,
                auspiceSort++,
                Packs.Auspices
        );
    }

    private Journal processTribe(Tribe t) {
        log.trace("Converting Tribe {}", t.name());
        String html;
        try {
            log.debug("Running template for tribe {}", t.name());
            html = Templater.instance().compile("tribe").apply(t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Journal(
                t.id(),
                t.name().displayName(),
                html,
                null,
                tribeSort++,
                Packs.Tribes
        );
    }

    private void appendGifts(StringBuilder text, Map<Rank, List<Gift>> gifts) {
        appendBeginningGifts(text, gifts.get(Rank.ONE));

        appendAllGifts(text, gifts);
    }

    private void appendBeginningGifts(StringBuilder text, List<Gift> gifts) {
        log.trace("Adding beginning gifts");
        text.append("<p><strong>Beginning Gifts:</strong> ").append(gifts.stream()
                .map(g -> String.format("@Item[%s]{%s}", g.id(), g.name())).collect(Collectors.joining(", "))).append("</p>");
    }

    private void appendAllGifts(StringBuilder text, Map<Rank, List<Gift>> gifts) {
        log.trace("Adding gifts");
        text.append("\n<h2>Gifts</h2>");
        for (var f : gifts.entrySet().stream().sorted(Comparator.comparingInt(o -> o.getKey().sortKey())).toList()) {
            text.append("\n<h4>").append(f.getKey().loreName()).append("</h4>\n")
                    .append(f.getValue().stream()
                            .sorted(Comparator.comparing(Gift::name))
                            .map(g -> String.format("@Item[%s]{%s}", g.id(), g.name()))
                            .collect(Collectors.joining(" ")));
        }
    }

    private Item processGift(Gift g, String folder) {
        log.trace("Converting Gift {}", g.name());
        log.trace("Adding description");
        var description = new StringBuilder(g.description());
        log.trace("Adding available to");
        description.append("\n<h3>Available To</h3>");
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
                        false,
                        "",
                        g.system()
                ),
                "",
                folder,
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
