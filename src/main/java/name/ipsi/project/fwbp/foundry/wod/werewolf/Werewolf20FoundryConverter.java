package name.ipsi.project.fwbp.foundry.wod.werewolf;

import name.ipsi.project.fwbp.books.shared.Background;
import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.books.shared.MeleeWeapon;
import name.ipsi.project.fwbp.books.werewolf.*;
import name.ipsi.project.fwbp.foundry.core.*;
import name.ipsi.project.fwbp.foundry.templating.Templater;
import name.ipsi.project.fwbp.foundry.wod.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class Werewolf20FoundryConverter {
    public static final Logger log = LoggerFactory.getLogger(Werewolf20FoundryConverter.class);

    private Map<String, String> folderIdsByName;
    private double breedSort;
    private double auspiceSort;
    private double tribeSort;

    public static final String MODULE_NAME = "wod-werewolf-20-core";
    public static final String CORE_FILE_PREFIX = "w20";

    public Adventure processAsAdventure(List<BookEntry> entries) throws IOException {
        log.trace("Converting book entries tp adventure");

        folderIdsByName = new HashMap<>();
        folderIdsByName.put("Garou", FoundryUtils.generateId("folder", "garou"));
        folderIdsByName.put("Gifts - Garou", FoundryUtils.generateId("folder", "gifts-garou"));
        folderIdsByName.put("Backgrounds - Garou", FoundryUtils.generateId("folder", "backgrounds-garou"));
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
        folders.add(new Folder(folderIdsByName.get("Backgrounds - Garou"), "Backgrounds - Garou", DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, 1.0, null, Collections.emptyMap(), null, null));
        folders.add(new Folder(folderIdsByName.get("Weapons - Melee"), "Weapons - Melee", DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, 2.0, null, Collections.emptyMap(), null, null));

        for (var entry : entries) {
            log.trace("Converting book entry {}", entry.getClass().getSimpleName());
            if (entry instanceof Breed b) {
                var j = processBreed(b);
                breedJournalPages.add(Page.createTextPage(j.id(), j.name(), j.content()));
            } else if (entry instanceof Auspice a) {
                var j = processAuspice(a);
                auspiceJournalPages.add(Page.createTextPage(j.id(), j.name(), j.content()));
            } else if (entry instanceof Tribe t) {
                var j = processTribe(t);
                tribeJournalPages.add(Page.createTextPage(
                        j.id(),
                        j.name(),
                        j.content(),
                        // BSDs are technically ST-only so hide from players
                        t.name() == Tribes.BLACK_SPIRAL_DANCERS ? DocumentOwnershipLevel.defaultNone() : DocumentOwnershipLevel.defaultInherit()
                ));
            } else if (entry instanceof Gift g) {
                items.add(processGift(g, folderIdsByName.get("Gifts - Garou")));
            } else if (entry instanceof MeleeWeapon mw) {
                items.add(processMeleeWeapon(mw, folderIdsByName.get("Weapons - Melee")));
            } else if (entry instanceof Background b) {
                items.add(processBackground(b));
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
                DocumentOwnershipLevel.defaultObserver(),
                Collections.emptyMap(),
                Packs.Weapons
        );
    }

    private Journal processBreed(Breed b) throws IOException {
        log.debug("Running template for breed {}", b.name());
        var html = Templater.instance().compile("breed").apply(b);

        return new Journal(
                b.id(),
                b.name(),
                html,
                null,
                breedSort++,
                Packs.Breeds
        );
    }

    private Journal processAuspice(Auspice a) throws IOException {
        log.debug("Running template for Auspice {}", a.name());
        var html = Templater.instance().compile("auspice").apply(a);

        return new Journal(
                a.id(),
                a.name(),
                html,
                null,
                auspiceSort++,
                Packs.Auspices
        );
    }

    private Journal processTribe(Tribe t) throws IOException {
        log.debug("Running template for tribe {}", t.name());
        var html = Templater.instance().compile("tribe").apply(t);

        return new Journal(
                t.id(),
                t.name().displayName(),
                html,
                null,
                tribeSort++,
                Packs.Tribes
        );
    }

    private Item processGift(Gift g, String folder) throws IOException {
        log.debug("Running templates for gift {}", g.name());
        var description = Templater.instance().compile("gift-description").apply(g);
        var system = Templater.instance().compile("gift-system").apply(g);
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
                        description,
                        PowerTypes.GIFT,
                        g.availableTo().get(0).level(),
                        gr != null ? convertCharacteristic(gr.characteristicOne()) : "",
                        gr != null && gr.characteristicTwo() != null ? convertCharacteristic(gr.characteristicTwo()) : "",
                        0,
                        gr != null ? gr.difficulty() : "",
                        gr != null,
                        false,
                        "",
                        system
                ),
                "",
                folder,
                0,
                DocumentOwnershipLevel.defaultObserver(),
                Collections.emptyMap(),
                Packs.Gifts
        );
    }

    private Item processBackground(Background b) throws IOException {
        log.debug("Running templates for background {}", b.name());
        var description = Templater.instance().compile("background").apply(b);
        return new Item(
                b.id(),
                b.name(),
                ItemTypes.FEATURE,
                "systems/worldofdarkness/assets/img/items/feature.svg",
                new FeatureData(
                        description,
                        null,
                        FeatureTypes.BACKGROUND
                ),
                null,
                folderIdsByName.get("Backgrounds - Garou"),
                0,
                DocumentOwnershipLevel.defaultObserver(),
                Collections.emptyMap(),
                null
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
