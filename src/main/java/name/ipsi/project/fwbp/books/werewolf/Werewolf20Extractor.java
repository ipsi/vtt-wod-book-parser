package name.ipsi.project.fwbp.books.werewolf;

import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import name.ipsi.project.fwbp.books.Utils;
import name.ipsi.project.fwbp.books.shared.*;
import name.ipsi.project.fwbp.books.shared.locations.*;
import name.ipsi.project.fwbp.books.werewolf.locations.*;
import name.ipsi.project.fwbp.foundry.wod.DamageTypes;
import name.ipsi.project.fwbp.foundry.wod.WeaponConcealment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static name.ipsi.project.fwbp.books.Utils.*;

public class Werewolf20Extractor {

    public static final Logger log = LoggerFactory.getLogger(Werewolf20Extractor.class);

    public static final String BOOK_NAME = "Werewolf: The Apocalypse 20th Anniversary Edition";
    public static final String BOOK_ID = "112023";
    public static final String COVER_IMAGE_ID = "26:Im1";

    private static final Book BOOK_DETAILS;

    static {
        BOOK_DETAILS = new Book(
                BreedLocations.DATA,
                AuspiceLocations.DATA,
                TribeLocations.DATA,
                BackgroundLocations.DATA,
                GiftLocations.DATA,
                RiteLocations.RITES,
                Collections.emptyList(),
                Collections.emptyList(),
                new WeaponLocations(
                        new Paragraph(303, makeRect(121, 129, 263, 280)),
                        new Paragraph(303, makeRect(0, 0, 0, 0)),
                        new Paragraph(304, makeRect(0, 0, 0, 0))
                ),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    private record Book(
            List<BreedLocation> breeds,
            List<AuspiceLocation> auspices,
            List<TribeLocation> tribes,
            List<BackgroundLocation> backgrounds,
            List<GiftLocations> gifts,
            List<RiteLocation> rites,
            List<FetishLocations> fetishes,
            List<TalenLocations> talens,
            WeaponLocations weapons,
            List<SpiritLocation> spirits,
            List<MeritLocation> merits,
            List<FlawLocation> flaws
    ) {}

    private final PdfDocumentContentParser parser;

    public Werewolf20Extractor(PdfDocumentContentParser parser) {
        this.parser = parser;
    }

    private static int levelToInt(String level) {
        return switch (level) {
            case "One" -> 1;
            case "Two", "T wo" -> 2;
            case "Three" -> 3;
            case "Four" -> 4;
            case "Five" -> 5;
            case "Six" -> 6;
            default -> throw new RuntimeException("Unknown level " + level);
        };
    }

    private static class GiftProcessor {
        private final List<Gift> gifts = new ArrayList<>();
        private final Map<GiftAvailability, List<Gift>> giftsByGroup = new HashMap<>();

        private final PdfDocumentContentParser parser;

        private GiftProcessor(PdfDocumentContentParser parser) {
            this.parser = parser;
        }

        public void process(GiftGroup group, List<Paragraph> text, GiftChart[] giftCharts) {
            var definition = Pattern.compile("^• (.*) \\(Level (.*)\\):? ?—? ?(.*)");
            var speedOfThoughtDefinition = Pattern.compile("^• Speed of Thought \\(Level\\s*");
            var harmoniousDefinition = Pattern.compile("^• Harmonious Unity of the Emerald Mother \\(Level\\s*");
            var bsdDefinitionOneLine = Pattern.compile("^• (.*) \\(Wyrm (.*) Gift, Level (.*)\\):\\s*(.*)");
            var bsdDefinitionTwoLines = Pattern.compile("^• (.*) \\(Wyrm (.*) Gift, Level\\s*");
            var system = Pattern.compile("^System: .*");
            var altSystem = Pattern.compile("(.*) System: (.*)");
            String name = "";
            String level = "";
            StringBuilder description = null;
            StringBuilder sys = null;
            log.trace("Starting to process text for gift");
            for (var p : text) {
                log.trace("Processing partial text fragment for gift");
                String appendNext = null;
                for (var line : getTextAsLines(parser, p)) {
                    line = line.trim();
                    if (appendNext != null) {
                        line = appendNext + " " + line;
                        appendNext = null;
                    }

                    var definitionMatcher = definition.matcher(line);
                    var bsdDefinitionMatcher = bsdDefinitionOneLine.matcher(line);
                    var altSystemMatcher = altSystem.matcher(line);

                    // The only two gift with the definition split across two lines :-/
                    // Plus BSD gifts, which are *special*
                    if (speedOfThoughtDefinition.matcher(line).matches()
                            || harmoniousDefinition.matcher(line).matches()
                            || bsdDefinitionTwoLines.matcher(line).matches()) {
                        log.trace("Gift has definition split across two lines - found first");
                        appendNext = line;
                        continue;
                    }

                    // Bloody, bloody stupid BSDs having a completely separate definition format!
                    if (bsdDefinitionMatcher.matches()) {
                        log.trace("Found BSD gift definition, finalising previous gift");
                        if (description != null) {
                            finaliseGift(group, name, level, description, sys, giftCharts);
                            description = null;
                            sys = null;
                        }

                        name = bsdDefinitionMatcher.group(1);
                        String wyrmAuspice = bsdDefinitionMatcher.group(2);
                        level = bsdDefinitionMatcher.group(3);
                        description = new StringBuilder("Wyrm Auspice: ").append(wyrmAuspice).append("\n\n").append(bsdDefinitionMatcher.group(4));
                        log.trace("Starting gift {}, {}, {}", name, level, description);
                    }
                    else if (definitionMatcher.matches()) {
                        log.trace("Found gift definition, finalising previous gift");
                        if (description != null) {
                            finaliseGift(group, name, level, description, sys, giftCharts);
                            description = null;
                            sys = null;
                        }

                        name = definitionMatcher.group(1);
                        level = definitionMatcher.group(2);
                        description = new StringBuilder(definitionMatcher.group(3));
                        log.trace("Starting gift {}, {}, {}", name, level, description);
                    } else if (system.matcher(line).matches()) {
                        log.trace("Found system definition line");
                        sys = new StringBuilder(line.replaceAll("^System: ", ""));
                    } else if (altSystemMatcher.matches()) {
                        log.trace("Found alternative system defintion line");
                        var d = altSystemMatcher.group(1);
                        var s = altSystemMatcher.group(2);
                        if (description == null) {
                            throw new RuntimeException(String.format(
                                    "Found combined description + system line but not ready for description - text line is [%s]; Base64: [%s]",
                                    line,
                                    Base64.getEncoder().encodeToString(line.getBytes(Charset.defaultCharset()))
                            ));
                        }
                        description.append(" ").append(d);
                        sys = new StringBuilder(s);
                    }
                    else {
                        if (sys != null) {
                            log.trace("Found system line");
                            sys.append(" ").append(line);
                        } else {
                            log.trace("Found description line");
                            if (description == null) {
                                throw new RuntimeException(String.format(
                                        "Found description line but not ready for description - text line is [%s]; Base64: [%s]",
                                        line,
                                        Base64.getEncoder().encodeToString(line.getBytes(Charset.defaultCharset()))
                                ));
                            }
                            description.append(" ").append(line);
                        }
                    }
                }
            }

            log.trace("Finalising final gift in section");
            finaliseGift(group, name, level, description, sys, giftCharts);
        }

        private void finaliseGift(GiftGroup group, String name, String level, StringBuilder description, StringBuilder sys, GiftChart[] giftCharts) {
            var fixedName = fixText(name);
            var fixedDesc = fixText(description.toString());
            var fixedSys = sys == null ? null : fixText(sys.toString());
            var l = levelToInt(level);
            var giftLevel = new GiftAvailability(group, l);
            RollData giftRoll = null;
            if (fixedSys != null) {
                var matcher = Pattern.compile(".*?(([\\w-]+) \\+)? ([\\w-]+|Animal Ken)( roll)? \\([Dd]ifficulty ([^)]+)\\).*").matcher(fixedSys);
                if (matcher.matches()) {
                    // Poor templating means we pick up "bite attack rollable" which we can't use and need to exclude
                    if (!matcher.group(3).equals("attack")) {
                        giftRoll = new RollData(
                                matcher.group(2) == null ? matcher.group(3) : matcher.group(2),
                                matcher.group(2) == null ? null : matcher.group(3),
                                matcher.group(5).matches("\\d+") ? matcher.group(5) : "-1"
                        );
                    }
                }
            }
            var optionalGift = gifts.stream()
                    .filter(g -> g.name().equals(fixedName))
                    .findFirst()
                    .map(g -> {
                        g.availableTo().add(giftLevel);
                        return g;
                    });
            var gr = giftRoll;
            var gift = optionalGift
                    .orElseGet(() -> new Gift(
                            fixedName,
                            fixedDesc,
                            // Absurd, but the only Gift without a "System:" label which is *not* an "As the..." Gift.
                            fixedName.equals("Grandmother's Touch") ? "" : fixedSys,
                            gr,
                            new ArrayList<>(Collections.singletonList(giftLevel)),
                            Arrays.stream(giftCharts)
                                    .filter(gc -> gc.name().equals(name))
                                    .findFirst()
                                    .map(this::processGiftChart)
                                    .orElse(null)
                    ));
            if (optionalGift.isEmpty()) {
                gifts.add(gift);
            }
            giftsByGroup.putIfAbsent(giftLevel, new ArrayList<>());
            giftsByGroup.get(giftLevel).add(gift);
        }

        private Table processGiftChart(GiftChart gc) {
            int rowCount = 0;
            Table.HeaderRow header = null;
            List<Table.Row> rows = new ArrayList<>();
            for (var line : getTextAsLines(parser, gc.tableParagraph().paragraphLocation())) {
                var colA = fixText(line.split(" ")[0]);
                var colB = fixText(line.substring(line.indexOf(" ") + 1));
                if (rowCount == 0) {
                    header = new Table.HeaderRow(new Table.Column(colA), new Table.Column(colB));
                } else {
                    rows.add(new Table.Row(new Table.Column(colA), new Table.Column(colB)));
                }
                rowCount++;
            }
            return new Table(header, null, rows.toArray(rows.toArray(new Table.Row[0])));
        }
    }

    public List<BookEntry> process() {
        log.info("Extracting Gifts");
        var giftProcessor = new GiftProcessor(parser);

        for (var textLocations : BOOK_DETAILS.gifts()) {
            log.trace("Extracting gift from {}", textLocations);
            giftProcessor.process(textLocations.group(), textLocations.locations(), textLocations.giftCharts());
            log.trace("Extracted {} gifts", giftProcessor.gifts.size());
        }

        var entries = new ArrayList<BookEntry>(giftProcessor.gifts);

        log.info("Extracting breeds");
        for (var breed : BOOK_DETAILS.breeds()) {
            log.trace("Processing {}", breed);
            log.trace("Getting name");
            var name = getText(parser, breed.nameLocation()).get(0);
            log.trace("Getting description");
            var description = String.join("\n", getText(parser, breed.descriptionLocation()));
            log.trace("Getting nicknames");
            var nicknames = getText(parser, breed.nicknamesLocation()).get(0);
            log.trace("Getting initialGnosis");
            var initialGnosis = getText(parser, breed.initialGnosisLocation()).get(0);
            log.trace("Getting beginningGifts");
            var beginningGifts = getText(parser, breed.beginningGiftsLocation()).get(0);
            log.trace("Getting deformities");
            Deformities deformities = null;
            if (breed.deformityLocations() != null) {
                String deformitiesDesc = getText(parser, breed.deformityLocations().descriptionLocation()).get(0);
                var deformitiesList = new ArrayList<Deformity>();
                for (DeformityLocation deformityLocation : breed.deformityLocations().deformities()) {
                    deformitiesList.add(new Deformity(getText(parser, deformityLocation.nameLocation()).get(0), String.join("\n", getText(parser, deformityLocation.descriptionLocation()))));
                }
                deformities = new Deformities(deformitiesDesc, deformitiesList);
            }

            log.trace("Getting restrictedAbilities");
            RestrictedAbilities restrictedAbilities = null;
            if (breed.restrictedAbilitiesLocations() != null) {
                var desc = getText(parser, breed.restrictedAbilitiesLocations().descriptionLocation()).get(0);
                var skills = getText(parser, breed.restrictedAbilitiesLocations().restrictedSkillsLocation()).get(0);
                var knowledges = getText(parser, breed.restrictedAbilitiesLocations().restrictedKnowledgesLocation()).get(0);
                restrictedAbilities = new RestrictedAbilities(desc, skills, knowledges);
            }

            log.trace("Processing beginning gifts");
            entries.add(new Breed(
                    name,
                    description,
                    nicknames,
                    Integer.parseInt(initialGnosis),
                    deformities,
                    restrictedAbilities,
                    collectGifts(name, giftProcessor.gifts)
            ));
        }

        log.info("Extracting Auspices");
        for (var a : BOOK_DETAILS.auspices()) {
            log.trace("Processing {}", a);
            log.trace("Getting name");
            var name = getText(parser, a.nameLocation()).get(0).replaceAll("\\s*:\\s*$", "");
            log.trace("Getting description");
            var description = String.join("\n", getText(parser, a.descriptionLocation()));
            log.trace("Getting titles");
            var titles = getText(parser, a.titleLocation()).get(0).replaceAll("^(" + name + ")?(\\s*:\\s*)?", "");
            log.trace("Getting stereotype");
            var stereotype = getText(parser, a.stereotypeLocation()).get(0).replaceAll("^\\s*Stereotype:\\s*", "");
            log.trace("Getting quote");
            var quote = getText(parser, a.quoteLocation()).get(0).replaceAll("^\\s*Quote:\\s*", "");
            log.trace("Getting initialRageLocation");
            var initialRage = getText(parser, a.initialRageLocation()).get(0).replaceAll("^\\s*Initial Rage:\\s*", "");
            log.trace("Processing {}", a);
            entries.add(new Auspice(
                    name,
                    Arrays.asList(titles.split("\\s*,\\s*")),
                    description,
                    Integer.parseInt(initialRage),
                    stereotype,
                    quote,
                    collectGifts(name, giftProcessor.gifts)
            ));
        }

        log.info("Extracting Tribes");
        for (var t : BOOK_DETAILS.tribes()) {
            log.trace("Processing {}", t);
            log.trace("Getting description");
            var description = String.join("\n", getText(parser, t.description()));
            log.trace("Getting appearance");
            var appearance = getText(parser, t.appearance()).get(0).replaceAll("^\\s*Appearance:\\s*", "");
            log.trace("Getting kinfolkTerritory");

            // BSDs are weird - the only clan with two paragraphs here
            var kinfolkTerritory = String.join("</p><p>", getText(parser, t.kinfolkTerritory())).replaceAll("^\\s*Kinfolk & Territory:\\s*", "");
            log.trace("Getting tribalTotem");
            var tribalTotem = getText(parser, t.tribalTotem()).get(0).replaceAll("^\\s*Tribal Totem:\\s*", "");

            // BSDs aren't officially playable characters
            var characterCreationLocation = t.characterCreation();
            String characterCreation = null;
            if (characterCreationLocation != null) {
                log.trace("Getting characterCreation");
                characterCreation = getText(parser, characterCreationLocation).get(0).replaceAll("^\\s*Character Creation:\\s*", "");
            }

            var backgroundRestrictionsLocation = t.backgroundRestrictions();
            String backgroundRestrictions = null;
            if (backgroundRestrictionsLocation != null) {
                log.trace("Getting backgroundRestrictions");
                backgroundRestrictions = getText(parser, backgroundRestrictionsLocation).get(0).replaceAll("^\\s*Background Restrictions:\\s*", "");
            }

            var derangementLocation = t.derangementLocation();
            String derangement = null;
            if (derangementLocation != null) {
                log.trace("Getting derangementLocation");
                derangement = getText(parser, derangementLocation).get(0).replaceAll("^\\s*Derangement:\\s*", "");
            }

            var stereotypesLocation = t.stereotypes();
            List<String> stereotype = null;
            if (stereotypesLocation != null) {
                log.trace("Getting stereotype");
                stereotype = getTextAsLines(parser, stereotypesLocation);
            }
            log.trace("Getting quote");
            var quote = getTextAsLines(parser, t.quote());
            log.trace("Getting initialWillpower");
            var initialWillpower = getText(parser, t.initialWillpower()).get(0).replaceAll("^\\s*Initial Willpower:\\s*", "");
            entries.add(new Tribe(
                    t.tribe(),
                    description,
                    appearance,
                    kinfolkTerritory,
                    tribalTotem,
                    characterCreation,
                    Integer.parseInt(initialWillpower),
                    backgroundRestrictions,
                    derangement,
                    stereotype == null ? null : parseTribalStereotypes(stereotype),
                    fixText(String.join("\n", quote)),
                    collectGifts(t.tribe().displayName(), giftProcessor.gifts)
            ));
        }

        entries.add(new MeleeWeapon(
                "Bite - Crinos, Hispo, Lupus",
                getText(parser, new Paragraph(297, makeRect(304, 213, 245, 77))).get(0).replaceAll("^\\s*•?\\s*Bite:\\s*", ""),
                5,
                1,
                DamageTypes.AGGRAVATED,
                WeaponConcealment.NONE,
                false,
                false,
                false,
                false,
                false,
                true
        ));

        entries.add(new MeleeWeapon(
                "Claw - Crinos, Hispo",
                getText(parser, new Paragraph(297, makeRect(304, 335, 246, 52))).get(0).replaceAll("^\\s*•?\\s*Claw:\\s*", ""),
                6,
                2,
                DamageTypes.AGGRAVATED,
                WeaponConcealment.NONE,
                false,
                false,
                false,
                false,
                false,
                true
        ));

        entries.add(new MeleeWeapon(
                "Claw - Glabro, Lupus",
                getText(parser, new Paragraph(297, makeRect(304, 335, 246, 52))).get(0).replaceAll("^\\s*•?\\s*Claw:\\s*", ""),
                6,
                2,
                DamageTypes.BASHING,
                WeaponConcealment.NONE,
                false,
                false,
                false,
                false,
                false,
                true
        ));

        log.info("Extracting Melee weapons");
        var meleeWeaponPattern = Pattern.compile("([\\w\\s]+)(\\**)?\\s*(\\d+)(\\**)?\\s*Strength(\\s*\\+\\s*(\\d+))?/([BLA])(\\**)?\\s*([PJTN])\\s*");
        for (var line : getTextAsLines(parser, BOOK_DETAILS.weapons().meleeWeapons())) {
            log.trace("Processing weapon line");
            var matcher = meleeWeaponPattern.matcher(line);
            if (matcher.matches()) {
                log.trace("Weapon matches");
                entries.add(new MeleeWeapon(
                        matcher.group(1).trim(),
                        "",
                        Integer.parseInt(matcher.group(3).trim()),
                        matcher.group(6) == null ? 0 : Integer.parseInt(matcher.group(6).trim()),
                        DamageTypes.parse(matcher.group(7).trim()),
                        WeaponConcealment.parse(matcher.group(9).trim()),
                        matcher.group(4) != null && matcher.group(4).trim().equals("*"),
                        matcher.group(8) != null && matcher.group(8).trim().equals("**"),
                        matcher.group(2) != null && matcher.group(2).trim().equals("***"),
                        matcher.group(2) != null && matcher.group(2).trim().equals("****"),
                        matcher.group(8) != null && matcher.group(8).trim().equals("*****"),
                        false
                ));
                log.trace("Weapon processed");
            } else {
                log.warn("Line {} doesn't match against weapon regex", line);
            }
        }

        log.info("Extracting backgrounds");
        var dotPattern = Pattern.compile("(•+)\\s*(.*)");
        var totemHeaderPattern = Pattern.compile("(Cost)\\s*(Power)");
        var totemPattern = Pattern.compile("(\\d+)\\s*(.*)");
        for (var background : BOOK_DETAILS.backgrounds()) {
            var name = background.name();
            var description = String.join("\n", getText(parser, background.descriptionLocation()));
            if(!name.equals("Totem")) {
                List<Table.Row> rows = new ArrayList<>();
                var rating = new StringBuilder();
                var ratingDescription = new ArrayList<String>();
                for (String line : getTextAsLines(parser, background.tableParagraph().paragraphLocation())) {
                    var matcher = dotPattern.matcher(line);
                    if (matcher.matches()) {
                        if (rating.length() > 0) {
                            rows.add(new Table.Row(new Table.Column(fixText(rating.toString())), new Table.Column(fixText(ratingDescription.stream().collect(CONTENT_AWARE_JOINER)))));
                            rating = new StringBuilder();
                            ratingDescription = new ArrayList<>();
                        }

                        rating.append(matcher.group(1));
                        ratingDescription.add(matcher.group(2));
                    } else {
                        ratingDescription.add(line);
                    }
                }
                rows.add(new Table.Row(new Table.Column(fixText(rating.toString())), new Table.Column(fixText(ratingDescription.stream().collect(CONTENT_AWARE_JOINER)))));
                entries.add(new Background(name, description, new Table(null, null, rows.toArray(new Table.Row[0])), "werewolf"));
            } else {
                var trailer = String.join("\n", getText(parser, background.tableParagraph().trailer()));
                Table.HeaderRow headerRow = new Table.HeaderRow(new Table.Column("Cost"), new Table.Column("Power"));
                List<Table.Row> rows = new ArrayList<>();
                var cost = new StringBuilder();
                var power = new ArrayList<String>();
                for (String line : getTextAsLines(parser, background.tableParagraph().paragraphLocation())) {
                    var headerMatcher = totemHeaderPattern.matcher(line);
                    var matcher = totemPattern.matcher(line);
                    if (headerMatcher.matches()) {
                        continue;
                    } else if (matcher.matches()) {
                        if (cost.length() > 0) {
                            rows.add(new Table.Row(new Table.Column(fixText(cost.toString())), new Table.Column(fixText(power.stream().collect(CONTENT_AWARE_JOINER).replaceAll("Cost Power {2}", "")))));
                            cost = new StringBuilder();
                            power = new ArrayList<>();
                        }

                        cost.append(matcher.group(1));
                        power.add(matcher.group(2));
                    } else {
                        power.add(line);
                    }
                }
                rows.add(new Table.Row(new Table.Column(fixText(cost.toString())), new Table.Column(fixText(power.stream().collect(CONTENT_AWARE_JOINER)))));
                entries.add(new Background(name, description, new Table(headerRow, trailer, rows.toArray(new Table.Row[0])), "werewolf"));
            }
        }

        log.info("Extracting Rites");
        var headerPattern = Pattern.compile("(.*) (Difficulty|Effect|Level)");
        var tableBodyPattern1 = Pattern.compile("^([0-9+–-]+) (.*)$");
        var tableBodyPattern2 = Pattern.compile("^(.*) ([0-9+–-]+)$");
        for(var rite : BOOK_DETAILS.rites()) {
            List<TextEntry> description = new ArrayList<>();
            List<TextEntry> system = new ArrayList<>();
            boolean foundSystem = false;
            for (var textLocation : rite.textLocations()) {
                if (textLocation.type() == TextLocationType.TEXT) {
                    var text = Utils.getText(parser, textLocation.locations());
                    if (text.startsWith("System:")) {
                        foundSystem = true;
                        system.add(new StringEntry(text.replaceAll("^System:\\s+", "")));
                    } else if (foundSystem) {
                        system.add(new StringEntry(text));
                    } else {
                        description.add(new StringEntry(text));
                    }
                } else if (textLocation.type() == TextLocationType.TABLE) {
                    var text = Utils.getTextAsLines(parser, textLocation);

                    Table.HeaderRow header = null;
                    List<Table.Row> rows = new ArrayList<>();
                    for (int i = 0; i < text.size(); i++) {
                        if (i == 0) {
                            var matcher = headerPattern.matcher(text.get(i));
                            if (matcher.matches()) {
                                header = new Table.HeaderRow(new Table.Column(matcher.group(1)), new Table.Column(matcher.group(2)));
                            } else {
                                log.error("No match found for header row [{}]", text.get(i));
                            }
                        } else {
                            var matcher1 = tableBodyPattern1.matcher(text.get(i));
                            var matcher2 = tableBodyPattern2.matcher(text.get(i));

                            if (matcher1.matches()) {
                                rows.add(new Table.Row(new Table.Column(matcher1.group(1)), new Table.Column(matcher1.group(2))));
                            } else if (matcher2.matches()) {
                                rows.add(new Table.Row(new Table.Column(matcher2.group(1)), new Table.Column(matcher2.group(2))));
                            } else {
                                log.trace("Line [{}] not matched by regex - assuming part of previous line, second column", text.get(i));
                                var prev = rows.remove(rows.size() - 1);
                                rows.add(new Table.Row(prev.columns()[0], new Table.Column(prev.columns()[1].data() + " " + text.get(i))));
                            }
                        }
                    }

                    if (foundSystem) {
                        system.add(new TableEntry(new Table(header, null, rows.toArray(new Table.Row[0]))));
                    } else {
                        description.add(new TableEntry(new Table(header, null, rows.toArray(new Table.Row[0]))));
                    }
                }
            }

            entries.add(new Rite(rite.name(), rite.type(), rite.level(), rite.type().rollData(), description, system));
        }

        return entries;
    }

    private static List<PowerCollection<Rank, Gift>> collectGifts(String name, List<Gift> gifts) {
        return gifts.stream()
                .filter(g -> g.availableFor(name))
                .collect(Collectors.groupingBy(g -> g.availableTo().stream().filter(gl -> gl.group().name().equals(name)).map(gl -> Rank.from(gl.level())).findFirst().orElseThrow()))
                .entrySet().stream().map(e -> new PowerCollection<>(e.getKey(), e.getValue()))
                .sorted(Comparator.comparing(PowerCollection::group))
                .toList();
    }

    private List<Stereotype<Tribes>> parseTribalStereotypes(List<String> stereotypes) {
        var rv = new ArrayList<Stereotype<Tribes>>();
        Tribes currentTribe = null;
        StringBuilder sb = new StringBuilder();
        var stereotypePattern = Pattern.compile("^\\s*(.*?)\\s*:\\s*(.*)");
        for(String line : stereotypes) {
            var stereotypeMatcher = stereotypePattern.matcher(line);
            if (stereotypeMatcher.matches()) {
                var tribeName = fixText(stereotypeMatcher.group(1));
                var restOfLine = stereotypeMatcher.group(2);

                Optional<Tribes> tribesOptional = Tribes.findByDisplayName(tribeName);
                if (tribesOptional.isEmpty()) {
                    log.debug("Unable to find Tribe [{}] - assuming bad regex match and adding to paragraph", tribeName);
                    sb.append(" ").append(line);
                } else {
                    if (currentTribe != null) {
                        rv.add(new Stereotype<>(currentTribe, fixText(sb.toString())));
                    }
                    currentTribe = tribesOptional.get();
                    sb = new StringBuilder(restOfLine);
                }

            } else {
                sb.append(" ").append(line);
            }
        }

        rv.add(new Stereotype<>(currentTribe, fixText(sb.toString())));

        return rv;
    }
}
