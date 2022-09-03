package name.ipsi.project.fwbp.books.werewolf;

import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import name.ipsi.project.fwbp.books.*;
import name.ipsi.project.fwbp.books.werewolf.locations.*;
import name.ipsi.project.fwbp.foundry.DamageTypes;
import name.ipsi.project.fwbp.foundry.WeaponConcealment;
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

    private static final Book BOOK_DETAILS;

    static {
        BOOK_DETAILS = new Book(
                Arrays.asList(new BreedLocation(
                                new Records.NameLocation(new Paragraph(74, makeRect(321, 678, 48, 12))),
                                new Records.DescriptionLocation(
                                        new Paragraph(74, makeRect(321, 697, 245, 35)),
                                        new Paragraph(75, makeRect(43, 255, 246, 94)),
                                        new Paragraph(75, makeRect(43, 353, 246, 82)),
                                        new Paragraph(75, makeRect(43, 438, 246, 95))
                                ),
                                new Records.NicknamesLocation(new Paragraph(75, makeRect(119, 537, 116, 10))),
                                new InitialGnosisLocation(new Paragraph(75, makeRect(129, 549, 5, 10))),
                                new BeginningGiftsLocation(
                                        new Paragraph(
                                                new TextArea(75, makeRect(137, 564, 153, 10)),
                                                new TextArea(75, makeRect(42, 574, 178, 10))
                                        )
                                ),
                                null,
                                null
                        ),
                        new BreedLocation(
                                new Records.NameLocation(new Paragraph(75, makeRect(41, 590, 42, 15))),
                                new Records.DescriptionLocation(
                                        new Paragraph(
                                                new TextArea(75, makeRect(43, 612, 245, 126)),
                                                new TextArea(75, makeRect(304, 44, 245, 91))
                                        ),
                                        new Paragraph(75, makeRect(303, 137, 245, 89)),
                                        new Paragraph(75, makeRect(303, 227, 245, 105))
                                ),
                                new Records.NicknamesLocation(new Paragraph(75, makeRect(380, 335, 125, 10))),
                                new InitialGnosisLocation(new Paragraph(75, makeRect(391, 349, 5, 10))),
                                new BeginningGiftsLocation(
                                        new Paragraph(
                                                new TextArea(75, makeRect(398, 364, 152, 10)),
                                                new TextArea(75, makeRect(305, 376, 113, 10))
                                        )
                                ),
                                new DeformityLocations(
                                        new Records.DescriptionLocation(new Paragraph(
                                                new TextArea(75, makeRect(383, 391, 166, 10)),
                                                new TextArea(75, makeRect(304, 405, 246, 63))
                                        )),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(75, makeRect(339, 471, 210, 10))),
                                                new Records.DescriptionLocation(new Paragraph(75, makeRect(304, 485, 246, 77)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(75, makeRect(339, 565, 210, 10))),
                                                new Records.DescriptionLocation(new Paragraph(75, makeRect(304, 579, 246, 65)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(75, makeRect(339, 646, 210, 10))),
                                                new Records.DescriptionLocation(new Paragraph(75, makeRect(304, 661, 246, 77)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(76, makeRect(89, 439, 215, 10))),
                                                new Records.DescriptionLocation(new Paragraph(76, makeRect(61, 451, 245, 80)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(76, makeRect(89, 534, 215, 10))),
                                                new Records.DescriptionLocation(new Paragraph(76, makeRect(61, 547, 245, 143)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(76, makeRect(89, 693, 215, 10))),
                                                new Records.DescriptionLocation(new Paragraph(
                                                        new TextArea(76, makeRect(61, 707, 245, 27)),
                                                        new TextArea(76, makeRect(322, 439, 245, 39))
                                                ))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(76, makeRect(349, 480, 215, 10))),
                                                new Records.DescriptionLocation(new Paragraph(76, makeRect(322, 494, 245, 51)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(76, makeRect(349, 548, 215, 10))),
                                                new Records.DescriptionLocation(new Paragraph(76, makeRect(322, 563, 245, 76)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(76, makeRect(349, 642, 215, 10))),
                                                new Records.DescriptionLocation(new Paragraph(76, makeRect(322, 657, 245, 76)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(77, makeRect(70, 45, 215, 10))),
                                                new Records.DescriptionLocation(new Paragraph(77, makeRect(43, 59, 245, 103)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(77, makeRect(70, 164, 215, 10))),
                                                new Records.DescriptionLocation(new Paragraph(77, makeRect(43, 179, 245, 63)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(77, makeRect(70, 245, 215, 10))),
                                                new Records.DescriptionLocation(new Paragraph(77, makeRect(43, 259, 245, 77)))
                                        ),
                                        new DeformityLocation(
                                                new Records.NameLocation(new Paragraph(77, makeRect(70, 339, 215, 10))),
                                                new Records.DescriptionLocation(new Paragraph(77, makeRect(43, 353, 245, 65)))
                                        )
                                ),
                                null
                        ),
                        new BreedLocation(
                                new Records.NameLocation(new Paragraph(77, makeRect(44, 422, 48, 16))),
                                new Records.DescriptionLocation(
                                        new Paragraph(77, makeRect(43, 443, 246, 77)),
                                        new Paragraph(77, makeRect(43, 522, 246, 129)),
                                        new Paragraph(
                                                new TextArea(77, makeRect(43, 654, 246, 78)),
                                                new TextArea(77, makeRect(303, 44, 246, 78))
                                        )
                                ),
                                new Records.NicknamesLocation(new Paragraph(77, makeRect(378, 124, 176, 10))),
                                new InitialGnosisLocation(new Paragraph(77, makeRect(390, 138, 5, 10))),
                                new BeginningGiftsLocation(
                                        new Paragraph(
                                                new TextArea(77, makeRect(402, 153, 153, 10)),
                                                new TextArea(77, makeRect(303, 167, 188, 10))
                                        )
                                ),
                                null,
                                new RestrictedAbilitiesLocations(
                                        new Records.DescriptionLocation(new Paragraph(
                                                new TextArea(77, makeRect(423, 180, 140, 10)),
                                                new TextArea(77, makeRect(304, 195, 245, 76))
                                        )),
                                        new RestrictedSkillsLocation(
                                                new Paragraph(77, makeRect(354, 273, 187, 10))
                                        ),
                                        new RestrictedKnowledgesLocation(new Paragraph(
                                                new TextArea(77, makeRect(385, 288, 164, 10)),
                                                new TextArea(77, makeRect(304, 301, 163, 10))
                                        ))
                                )
                        )),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Arrays.asList(
                        new GiftLocations(new BreedGifts(Breeds.HOMID), Arrays.asList(
                                new Paragraph(153, makeRect(304, 363, 247, 370)),
                                new Paragraph(154, makeRect(61, 404, 247, 329)),
                                new Paragraph(154, makeRect(322, 404, 247, 329)),
                                new Paragraph(155, makeRect(42, 45, 247, 695)),
                                new Paragraph(155, makeRect(304, 45, 247, 695)),
                                new Paragraph(156, makeRect(61, 45, 247, 695)),
                                new Paragraph(156, makeRect(322, 45, 247, 512))
                        )),
                        new GiftLocations(new BreedGifts(Breeds.METIS), Arrays.asList(
                                new Paragraph(156, makeRect(322, 663, 247, 77)),
                                new Paragraph(157, makeRect(42, 45, 247, 695)),
                                new Paragraph(157, makeRect(304, 45, 247, 695)),
                                new Paragraph(158, makeRect(61, 45, 247, 695)),
                                new Paragraph(158, makeRect(322, 45, 247, 695)),
                                new Paragraph(159, makeRect(42, 45, 247, 263))
                        )),
                        new GiftLocations(new BreedGifts(Breeds.LUPUS), Arrays.asList(
                                new Paragraph(159, makeRect(304, 59, 247, 315)),
                                new Paragraph(160, makeRect(61, 45, 247, 695)),
                                new Paragraph(160, makeRect(322, 45, 247, 695)),
                                new Paragraph(161, makeRect(42, 45, 247, 695)),
                                new Paragraph(161, makeRect(304, 45, 247, 695)),
                                new Paragraph(162, makeRect(61, 45, 247, 50))
                        )),
                        new GiftLocations(new AuspiceGifts(Auspices.RAGABASH), Arrays.asList(
                                new Paragraph(162, makeRect(61, 253, 247, 474)),
                                new Paragraph(162, makeRect(322, 45, 247, 695)),
                                new Paragraph(163, makeRect(42, 45, 247, 695)),
                                new Paragraph(163, makeRect(304, 45, 247, 695)),
                                new Paragraph(164, makeRect(61, 45, 247, 327)),
                                new Paragraph(164, makeRect(322, 45, 247, 327)),
                                new Paragraph(165, makeRect(42, 45, 247, 155))
                        )),
                        new GiftLocations(new AuspiceGifts(Auspices.THEURGE), Arrays.asList(
                                new Paragraph(165, makeRect(42, 265, 247, 467)),
                                new Paragraph(165, makeRect(304, 45, 247, 695)),
                                new Paragraph(166, makeRect(61, 45, 247, 695)),
                                new Paragraph(166, makeRect(322, 45, 247, 695)),
                                new Paragraph(167, makeRect(42, 45, 247, 580))
                        )),
                        new GiftLocations(new AuspiceGifts(Auspices.PHILADOX), Arrays.asList(
                                new Paragraph(167, makeRect(42, 714, 247, 26)),
                                new Paragraph(167, makeRect(304, 45, 247, 695)),
                                new Paragraph(168, makeRect(61, 45, 247, 695)),
                                new Paragraph(168, makeRect(322, 45, 247, 695)),
                                new Paragraph(169, makeRect(42, 45, 247, 695)),
                                new Paragraph(170, makeRect(61, 45, 247, 77))
                        )),
                        new GiftLocations(new AuspiceGifts(Auspices.GALLIARD), Arrays.asList(
                                new Paragraph(170, makeRect(61, 200, 247, 540)),
                                new Paragraph(170, makeRect(322, 45, 247, 695)),
                                new Paragraph(171, makeRect(42, 45, 247, 695)),
                                new Paragraph(171, makeRect(304, 45, 247, 570))
                        )),
                        new GiftLocations(new AuspiceGifts(Auspices.AHROUN), Arrays.asList(
                                new Paragraph(171, makeRect(304, 691, 247, 37)),
                                new Paragraph(172, makeRect(61, 45, 247, 695)),
                                new Paragraph(172, makeRect(322, 45, 247, 695)),
                                new Paragraph(173, makeRect(42, 45, 247, 695)),
                                new Paragraph(173, makeRect(304, 45, 247, 475))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.BLACK_FURIES), Arrays.asList(
                                new Paragraph(174, makeRect(61, 119, 247, 609)),
                                new Paragraph(174, makeRect(322, 45, 247, 695)),
                                new Paragraph(175, makeRect(42, 45, 247, 695)),
                                new Paragraph(175, makeRect(304, 45, 247, 198))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.BONE_GNAWERS), Arrays.asList(
                                new Paragraph(175, makeRect(304, 295, 247, 427)),
                                new Paragraph(176, makeRect(61, 45, 247, 695)),
                                new Paragraph(176, makeRect(322, 45, 247, 695)),
                                new Paragraph(177, makeRect(42, 404, 247, 331)),
                                new Paragraph(177, makeRect(304, 404, 247, 331)),
                                new Paragraph(178, makeRect(61, 45, 247, 90))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.CHILDREN_OF_GAIA), Arrays.asList(
                                new Paragraph(178, makeRect(61, 253, 247, 485)),
                                new Paragraph(178, makeRect(322, 45, 247, 695)),
                                new Paragraph(179, makeRect(42, 45, 247, 695)),
                                new Paragraph(179, makeRect(304, 45, 247, 596))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.FIANNA), Arrays.asList(
                                new Paragraph(179, makeRect(304, 705, 247, 25)),
                                new Paragraph(180, makeRect(61, 45, 247, 695)),
                                new Paragraph(180, makeRect(322, 45, 247, 695)),
                                new Paragraph(181, makeRect(42, 45, 247, 622))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.GET_OF_FENRIS), Arrays.asList(
                                new Paragraph(181, makeRect(304, 45, 247, 695)),
                                new Paragraph(182, makeRect(61, 45, 247, 348)),
                                new Paragraph(182, makeRect(322, 45, 247, 348)),
                                new Paragraph(183, makeRect(42, 45, 247, 695)),
                                new Paragraph(183, makeRect(304, 392, 247, 66))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.GLASS_WALKERS), Arrays.asList(
                                new Paragraph(183, makeRect(304, 547, 247, 188)),
                                new Paragraph(184, makeRect(61, 45, 247, 268)),
                                new Paragraph(184, makeRect(322, 45, 247, 268)),
                                new Paragraph(185, makeRect(42, 45, 247, 695)),
                                new Paragraph(185, makeRect(304, 45, 247, 695)),
                                new Paragraph(186, makeRect(61, 45, 247, 695))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.RED_TALONS), Arrays.asList(
                                new Paragraph(186, makeRect(322, 120, 247, 610)),
                                new Paragraph(187, makeRect(42, 45, 247, 239)),
                                new Paragraph(187, makeRect(304, 45, 247, 239)),
                                new Paragraph(188, makeRect(61, 45, 247, 695)),
                                new Paragraph(188, makeRect(322, 45, 247, 695)),
                                new Paragraph(189, makeRect(322, 45, 247, 264))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.SHADOW_LORDS), Arrays.asList(
                                new Paragraph(189, makeRect(322, 385, 247, 352)),
                                new Paragraph(190, makeRect(61, 45, 247, 211)),
                                new Paragraph(190, makeRect(322, 45, 247, 211)),
                                new Paragraph(191, makeRect(42, 45, 247, 695)),
                                new Paragraph(191, makeRect(304, 45, 247, 695)),
                                new Paragraph(192, makeRect(61, 45, 247, 278)),
                                new Paragraph(192, makeRect(322, 45, 247, 224))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.SILENT_STRIDERS), Arrays.asList(
                                new Paragraph(192, makeRect(322, 319, 247, 415)),
                                new Paragraph(193, makeRect(42, 45, 247, 695)),
                                new Paragraph(193, makeRect(304, 45, 247, 695)),
                                new Paragraph(194, makeRect(61, 167, 247, 695)),
                                new Paragraph(194, makeRect(322, 167, 247, 118))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.SILVER_FANGS), Arrays.asList(
                                new Paragraph(194, makeRect(322, 227, 247, 509)),
                                new Paragraph(195, makeRect(42, 45, 247, 481)),
                                new Paragraph(195, makeRect(304, 45, 247, 695)),
                                new Paragraph(196, makeRect(61, 45, 247, 423))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.STARGAZERS), Arrays.asList(
                                new Paragraph(196, makeRect(61, 557, 247, 171)),
                                new Paragraph(196, makeRect(322, 45, 247, 695)),
                                new Paragraph(197, makeRect(42, 45, 247, 695)),
                                new Paragraph(197, makeRect(304, 45, 247, 571))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.UKTENA), Arrays.asList(
                                new Paragraph(197, makeRect(304, 691, 247, 40)),
                                new Paragraph(198, makeRect(61, 45, 247, 695)),
                                new Paragraph(198, makeRect(322, 45, 247, 695)),
                                new Paragraph(199, makeRect(42, 45, 247, 695)),
                                new Paragraph(199, makeRect(304, 45, 247, 695)),
                                new Paragraph(200, makeRect(61, 45, 247, 424))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.WENDIGO), Arrays.asList(
                                new Paragraph(200, makeRect(61, 533, 247, 201)),
                                new Paragraph(200, makeRect(322, 45, 247, 146)),
                                new Paragraph(201, makeRect(42, 45, 247, 695)),
                                new Paragraph(201, makeRect(304, 45, 247, 695)),
                                new Paragraph(202, makeRect(61, 45, 247, 695)),
                                new Paragraph(202, makeRect(322, 45, 247, 533))
                        ))
                ),
                Collections.emptyList(),
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
            List<Background> backgrounds,
            List<GiftLocations> gifts,
            List<RiteLocations> rites,
            List<FetishLocations> fetishes,
            List<TalenLocations> talens,
            WeaponLocations weapons,
            List<Spirit> spirits,
            List<Merit> merits,
            List<Flaw> flaws
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
        private final Map<GiftLevel, List<Gift>> giftsByGroup = new HashMap<>();

        private final PdfDocumentContentParser parser;

        private GiftProcessor(PdfDocumentContentParser parser) {
            this.parser = parser;
        }

        public void process(GiftGroup group, List<Paragraph> text) {
            var definition = Pattern.compile("^• (.*) \\(Level (.*)\\) ?— ?(.*)");
            var speedOfThoughtDefinition = Pattern.compile("^• Speed of Thought \\(Level\\s*");
            var harmoniousDefinition = Pattern.compile("^• Harmonious Unity of the Emerald Mother \\(Level\\s*");
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
                    var altSystemMatcher = altSystem.matcher(line);

                    // The only two gift with the definition split across two lines :-/
                    if (speedOfThoughtDefinition.matcher(line).matches() || harmoniousDefinition.matcher(line).matches()) {
                        log.trace("Gift has definition split across two lines - found first");
                        appendNext = line;
                        continue;
                    }

                    if (definitionMatcher.matches()) {
                        log.trace("Found gift definition, finalising previous gift");
                        if (description != null) {
                            finaliseGift(group, name, level, description, sys);
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
            finaliseGift(group, name, level, description, sys);
        }

        private void finaliseGift(GiftGroup group, String name, String level, StringBuilder description, StringBuilder sys) {
            var fixedName = fixText(name);
            var fixedDesc = fixText(description.toString());
            var fixedSys = sys == null ? null : fixText(sys.toString());
            var l = levelToInt(level);
            var giftLevel = new GiftLevel(group, l);
            GiftRoll giftRoll = null;
            if (fixedSys != null) {
                var matcher = Pattern.compile(".*?(([\\w-]+) \\+)? ([\\w-]+|Animal Ken)( roll)? \\([Dd]ifficulty ([^)]+)\\).*").matcher(fixedSys);
                if (matcher.matches()) {
                    // Poor templating means we pick up "bite attack roll" which we can't use and need to exclude
                    if (!matcher.group(3).equals("attack")) {
                        giftRoll = new GiftRoll(
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
                            new ArrayList<>(Collections.singletonList(giftLevel))
                    ));
            if (optionalGift.isEmpty()) {
                gifts.add(gift);
            }
            giftsByGroup.putIfAbsent(giftLevel, new ArrayList<>());
            giftsByGroup.get(giftLevel).add(gift);
        }
    }

    public List<BookEntry> process() {
        //new Paragraph(even, makeRect(61, 45, 247, 695)),
        //new Paragraph(even, makeRect(322, 45, 247, 695)),
        //new Paragraph(odd, makeRect(42, 45, 247, 695)),
        //new Paragraph(odd, makeRect(304, 45, 247, 695))

        log.debug("Extracting Gifts");
        var giftProcessor = new GiftProcessor(parser);

        for (var textLocations : BOOK_DETAILS.gifts()) {
            log.trace("Extracting gift from {}", textLocations);
            giftProcessor.process(textLocations.group(), textLocations.locations());
            log.trace("Extracted {} gifts", giftProcessor.gifts.size());
        }

        var entries = new ArrayList<BookEntry>(giftProcessor.gifts);

        log.trace("Processing breeds");
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
            var beginningGiftsList = Arrays.asList(beginningGifts.split("\\s*,\\s*"));
            entries.add(new Breed(
                    name,
                    description,
                    nicknames,
                    Integer.parseInt(initialGnosis),
                    giftProcessor.gifts.stream().filter(g -> beginningGiftsList.stream().anyMatch(s -> s.equals(g.name()))).collect(Collectors.toList()),
                    deformities,
                    restrictedAbilities,
                    giftProcessor.gifts.stream()
                            .filter(g -> g.availableFor(name))
                            .sorted(Comparator.comparing(Gift::name))
                            .collect(Collectors.groupingBy(g -> g.availableTo().stream().filter(gl -> gl.group().name().equals(name)).map(gl -> Rank.from(gl.level())).findFirst().orElseThrow()))
            ));
        }

        log.trace("Processing Auspices");
        for (var a : Auspices.values()) {
            log.trace("Processing {}", a);
            entries.add(new Auspice(
                    a,
                    Collections.emptyList(),
                    "",
                    -1,
                    "",
                    "",
                    giftProcessor.gifts.stream()
                            .filter(g -> g.availableFor(a.displayName()))
                            .sorted(Comparator.comparing(Gift::name))
                            .collect(Collectors.groupingBy(g -> g.availableTo().stream().filter(gl -> gl.group().name().equals(a.displayName())).map(gl -> Rank.from(gl.level())).findFirst().orElseThrow()))
            ));
        }

        log.trace("Processing Tribes");
        for (var t : Tribes.values()) {
            log.trace("Processing {}", t);
            entries.add(new Tribe(
                    t,
                    "",
                    "",
                    "",
                    "",
                    "",
                    -1,
                    Collections.emptyList(),
                    giftProcessor.gifts.stream()
                            .filter(g -> g.availableFor(t.displayName()))
                            .sorted(Comparator.comparing(Gift::name))
                            .collect(Collectors.groupingBy(g -> g.availableTo().stream().filter(gl -> gl.group().name().equals(t.displayName())).map(gl -> Rank.from(gl.level())).findFirst().orElseThrow()))
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

        log.trace("Processing Melee weapons");
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

        return entries;
    }
}
