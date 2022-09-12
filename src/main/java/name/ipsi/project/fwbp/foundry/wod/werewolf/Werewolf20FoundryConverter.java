package name.ipsi.project.fwbp.foundry.wod.werewolf;

import name.ipsi.project.fwbp.books.shared.*;
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
    private Map<String, String> folderIdsById;
    private double breedSort;
    private double auspiceSort;
    private double tribeSort;

    public static final String MODULE_NAME = "wod-werewolf-20-core";
    public static final String CORE_FILE_PREFIX = "w20";

    public Adventure processAsAdventure(List<BookEntry> entries) throws IOException {
        log.trace("Converting book entries tp adventure");
        folderIdsByName = new HashMap<>();
        folderIdsById = new HashMap<>();

        var items = new ArrayList<Item>();
        var journals = new ArrayList<Journal>();
        var folders = new ArrayList<Folder>();

        folders.add(createFolder("garou", "Garou", DocumentTypes.JOURNAL_ENTRY, FolderSortingModes.MANUAL, 1.0, null));

        var itemFolderSort = 0;
        folders.add(createFolder("gifts-garou", "Gifts - Garou", DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, itemFolderSort++, null));
        folders.add(createFolder("backgrounds-garou", "Backgrounds - Garou", DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, itemFolderSort++, null));
        folders.add(createFolder("rites-garou", "Rites - Garou", DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, itemFolderSort++, null));
        folders.add(createFolder("fetishes-garou", "Fetishes - Garou", DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, itemFolderSort++, null));
        folders.add(createFolder("talens-garou", "Talens - Garou", DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, itemFolderSort++, null));
        folders.add(createFolder("merits-garou", "Merits - Garou", DocumentTypes.ITEM, FolderSortingModes.MANUAL, itemFolderSort++, null));
        folders.add(createFolder("flaws-garou", "Flaws - Garou", DocumentTypes.ITEM, FolderSortingModes.MANUAL, itemFolderSort++, null));

        folders.add(createFolder("weapons-melee", "Weapons - Melee", DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, itemFolderSort++, null));
        double sortIdx = 0;
        for(var type : RiteType.values()) {
            folders.add(createFolder("garou-rites-" + type.displayName(), type.displayName(), DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, sortIdx++, folderIdsByName.get("Rites - Garou")));
        }

        sortIdx = 0;
        for (var section : Arrays.asList("Physical", "Mental", "Social", "Supernatural")) {
            folders.add(createFolder("garou-merits-" + section, section, DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, sortIdx, folderIdsByName.get("Merits - Garou")));
            folders.add(createFolder("garou-flaws-" + section, section, DocumentTypes.ITEM, FolderSortingModes.ALPHABETICAL, sortIdx, folderIdsByName.get("Flaws - Garou")));
            sortIdx++;
        }

        List<Page> breedJournalPages = new ArrayList<>();
        var breedJournal = new Journal(FoundryUtils.generateId("journal", "breeds"), "Breeds", folderIdsByName.get("Garou"), 1.0, Collections.emptyMap(), breedJournalPages, DocumentOwnershipLevel.defaultObserver());
        journals.add(breedJournal);
        List<Page> auspiceJournalPages = new ArrayList<>();
        var auspiceJournal = new Journal(FoundryUtils.generateId("journal", "auspices"), "Auspices", folderIdsByName.get("Garou"), 2.0, Collections.emptyMap(), auspiceJournalPages, DocumentOwnershipLevel.defaultObserver());
        journals.add(auspiceJournal);
        List<Page> tribeJournalPages = new ArrayList<>();
        var tribeJournal = new Journal(FoundryUtils.generateId("journal", "tribes"), "Tribes", folderIdsByName.get("Garou"), 3.0, Collections.emptyMap(), tribeJournalPages, DocumentOwnershipLevel.defaultObserver());
        journals.add(tribeJournal);

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
            } else if (entry instanceof Rite r) {
                items.add(processRite(r));
            } else if (entry instanceof Fetish f) {
                items.add(processFetish(f));
            } else if (entry instanceof Talen t) {
                items.add(processTalen(t));
            } else if (entry instanceof Merit m) {
                items.add(processMerit(m));
            } else if (entry instanceof Flaw f) {
                items.add(processFlaw(f));
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

    private Folder createFolder(String idGroup, String name, DocumentTypes documentType, FolderSortingModes sortingMode, double sortOrder, String parentFolderId) {
        String id = FoundryUtils.generateId("folder", idGroup);
        folderIdsByName.put(name, id);
        folderIdsById.put(idGroup, id);
        return new Folder(id, name, documentType, sortingMode, sortOrder, null, Collections.emptyMap(), parentFolderId, null);
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
                        new MeleeWeaponData.Attack(
                                "dexterity",
                                0,
                                "brawl",
                                true
                        ),
                        meleeWeapon.concealment(),
                        new MeleeWeaponData.Damage(
                                "strength",
                                meleeWeapon.damageBonus(),
                                meleeWeapon.damageType(),
                                true
                        ),
                        meleeWeapon.description(),
                        String.valueOf(meleeWeapon.difficulty()),
                        meleeWeapon.natural(),
                        meleeWeapon.twoHanded()
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
                new PowerData(
                        description,
                        gr != null ? convertCharacteristic(gr.characteristicOne()) : "",
                        gr != null && gr.characteristicTwo() != null ? convertCharacteristic(gr.characteristicTwo()) : "",
                        gr != null ? gr.difficulty() : "",
                        gr != null,
                        String.valueOf(g.availableTo().get(0).level()),
                        system,
                        PowerTypes.GIFT
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
                        "",
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

    private Item processRite(Rite r) throws IOException {
        log.debug("Running templates for rite {}", r.name());
        var description = Templater.instance().compile("rite-description").apply(r);
        var system = Templater.instance().compile("rite-system").apply(r);
        return new Item(
                r.id(),
                r.name(),
                ItemTypes.POWER,
                "systems/worldofdarkness/assets/img/items/ritual_werewolf.svg",
                new PowerData(
                        description,
                        r.rollData() != null ? convertCharacteristic(r.rollData().characteristicOne()) : null,
                        r.rollData() != null && r.rollData().characteristicTwo() != null ? convertCharacteristic(r.rollData().characteristicTwo()) : null,
                        r.rollData() != null ? r.rollData().difficulty() : null,
                        r.rollData() != null,
                        String.valueOf(r.level().foundryLevel()),
                        system,
                        PowerTypes.RITE
                ),
                null,
                folderIdsByName.get(r.type().displayName()),
                0,
                DocumentOwnershipLevel.defaultObserver(),
                Collections.emptyMap(),
                null
        );
    }

    private Item processFetish(Fetish f) throws IOException {
        log.debug("Running templates for fetish {}", f.name());
        var description = Templater.instance().compile("fetish").apply(f);
        return new Item(
                f.id(),
                f.name(),
                ItemTypes.FETISH,
                "systems/worldofdarkness/assets/img/items/fetish.svg",
                new FetishData(
                        description,
                        f.gnosis(),
                        f.level().foundryLevel(),
                        FetishType.FETISH
                ),
                null,
                folderIdsByName.get("Fetishes - Garou"),
                0,
                DocumentOwnershipLevel.defaultObserver(),
                Collections.emptyMap(),
                null
        );
    }

    private Item processTalen(Talen t) throws IOException {
        log.debug("Running templates for talen {}", t.name());
        var description = Templater.instance().compile("talen").apply(t);
        return new Item(
                t.id(),
                t.name(),
                ItemTypes.FETISH,
                "systems/worldofdarkness/assets/img/items/fetish.svg",
                new FetishData(
                        description,
                        t.gnosis(),
                        FetishType.TALEN
                ),
                null,
                folderIdsByName.get("Talens - Garou"),
                0,
                DocumentOwnershipLevel.defaultObserver(),
                Collections.emptyMap(),
                null
        );
    }

    private Item processMerit(Merit m) throws IOException {
        log.debug("Running templates for merit {}", m.name());
        var description = Templater.instance().compile("merit").apply(m);
        return new Item(
                m.id(),
                m.name(),
                ItemTypes.FEATURE,
                "systems/worldofdarkness/assets/img/items/feature.svg",
                new FeatureData(
                        description,
                        "",
                        String.valueOf(m.cost()),
                        m.max(),
                        FeatureTypes.MERIT
                ),
                null,
                folderIdsById.get("garou-merits-" + m.section()),
                0,
                DocumentOwnershipLevel.defaultObserver(),
                Collections.emptyMap(),
                null
        );
    }

    private Item processFlaw(Flaw f) throws IOException {
        log.debug("Running templates for flaw {}", f.name());
        var description = Templater.instance().compile("flaw").apply(f);
        return new Item(
                f.id(),
                f.name(),
                ItemTypes.FEATURE,
                "systems/worldofdarkness/assets/img/items/feature.svg",
                new FeatureData(
                        description,
                        "",
                        String.valueOf(f.cost()),
                        f.max(),
                        FeatureTypes.FLAW
                ),
                null,
                folderIdsById.get("garou-flaws-" + f.section()),
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
