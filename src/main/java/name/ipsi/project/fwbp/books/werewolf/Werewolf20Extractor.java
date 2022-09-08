package name.ipsi.project.fwbp.books.werewolf;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
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

    private static final Book BOOK_DETAILS;

    static {
        BOOK_DETAILS = new Book(
                Arrays.asList(new BreedLocation(
                                new NameLocation(new Paragraph(74, makeRect(321, 678, 48, 12))),
                                new DescriptionLocation(
                                        new Paragraph(74, makeRect(321, 697, 245, 35)),
                                        new Paragraph(75, makeRect(43, 255, 246, 94)),
                                        new Paragraph(75, makeRect(43, 353, 246, 82)),
                                        new Paragraph(75, makeRect(43, 438, 246, 95))
                                ),
                                new NicknamesLocation(new Paragraph(75, makeRect(119, 537, 116, 10))),
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
                                new NameLocation(new Paragraph(75, makeRect(41, 590, 42, 15))),
                                new DescriptionLocation(
                                        new Paragraph(
                                                new TextArea(75, makeRect(43, 612, 245, 126)),
                                                new TextArea(75, makeRect(304, 44, 245, 91))
                                        ),
                                        new Paragraph(75, makeRect(303, 137, 245, 89)),
                                        new Paragraph(75, makeRect(303, 227, 245, 105))
                                ),
                                new NicknamesLocation(new Paragraph(75, makeRect(380, 335, 125, 10))),
                                new InitialGnosisLocation(new Paragraph(75, makeRect(391, 349, 5, 10))),
                                new BeginningGiftsLocation(
                                        new Paragraph(
                                                new TextArea(75, makeRect(398, 364, 152, 10)),
                                                new TextArea(75, makeRect(305, 376, 113, 10))
                                        )
                                ),
                                new DeformityLocations(
                                        new DescriptionLocation(new Paragraph(
                                                new TextArea(75, makeRect(383, 391, 166, 10)),
                                                new TextArea(75, makeRect(304, 405, 246, 63))
                                        )),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(75, makeRect(339, 471, 210, 10))),
                                                new DescriptionLocation(new Paragraph(75, makeRect(304, 485, 246, 77)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(75, makeRect(339, 565, 210, 10))),
                                                new DescriptionLocation(new Paragraph(75, makeRect(304, 579, 246, 65)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(75, makeRect(339, 646, 210, 10))),
                                                new DescriptionLocation(new Paragraph(75, makeRect(304, 661, 246, 77)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(76, makeRect(89, 439, 215, 10))),
                                                new DescriptionLocation(new Paragraph(76, makeRect(61, 451, 245, 80)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(76, makeRect(89, 534, 215, 10))),
                                                new DescriptionLocation(new Paragraph(76, makeRect(61, 547, 245, 143)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(76, makeRect(89, 693, 215, 10))),
                                                new DescriptionLocation(new Paragraph(
                                                        new TextArea(76, makeRect(61, 707, 245, 27)),
                                                        new TextArea(76, makeRect(322, 439, 245, 39))
                                                ))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(76, makeRect(349, 480, 215, 10))),
                                                new DescriptionLocation(new Paragraph(76, makeRect(322, 494, 245, 51)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(76, makeRect(349, 548, 215, 10))),
                                                new DescriptionLocation(new Paragraph(76, makeRect(322, 563, 245, 76)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(76, makeRect(349, 642, 215, 10))),
                                                new DescriptionLocation(new Paragraph(76, makeRect(322, 657, 245, 76)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(77, makeRect(70, 45, 215, 10))),
                                                new DescriptionLocation(new Paragraph(77, makeRect(43, 59, 245, 103)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(77, makeRect(70, 164, 215, 10))),
                                                new DescriptionLocation(new Paragraph(77, makeRect(43, 179, 245, 63)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(77, makeRect(70, 245, 215, 10))),
                                                new DescriptionLocation(new Paragraph(77, makeRect(43, 259, 245, 77)))
                                        ),
                                        new DeformityLocation(
                                                new NameLocation(new Paragraph(77, makeRect(70, 339, 215, 10))),
                                                new DescriptionLocation(new Paragraph(77, makeRect(43, 353, 245, 65)))
                                        )
                                ),
                                null
                        ),
                        new BreedLocation(
                                new NameLocation(new Paragraph(77, makeRect(44, 422, 48, 16))),
                                new DescriptionLocation(
                                        new Paragraph(77, makeRect(43, 443, 246, 77)),
                                        new Paragraph(77, makeRect(43, 522, 246, 129)),
                                        new Paragraph(
                                                new TextArea(77, makeRect(43, 654, 246, 78)),
                                                new TextArea(77, makeRect(303, 44, 246, 78))
                                        )
                                ),
                                new NicknamesLocation(new Paragraph(77, makeRect(378, 124, 176, 10))),
                                new InitialGnosisLocation(new Paragraph(77, makeRect(390, 138, 5, 10))),
                                new BeginningGiftsLocation(
                                        new Paragraph(
                                                new TextArea(77, makeRect(402, 153, 153, 10)),
                                                new TextArea(77, makeRect(303, 167, 188, 10))
                                        )
                                ),
                                null,
                                new RestrictedAbilitiesLocations(
                                        new DescriptionLocation(new Paragraph(
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
                        )
                ),
                Arrays.asList(
                        new AuspiceLocation(
                                new NameLocation(new Paragraph(78, makeRect(60, 180, 68, 15))),
                                new TitleLocation(new Paragraph(78, makeRect(61, 193, 205, 15))),
                                new DescriptionLocation(
                                        new Paragraph(78, makeRect(61, 213, 245, 128)),
                                        new Paragraph(78, makeRect(61, 344, 245, 128))
                                ),
                                new InitialRage(new Paragraph(78, makeRect(78, 476, 67, 11))),
                                new BeginningGiftsLocation(new Paragraph(78, makeRect(60, 489, 246, 27))),
                                new StereotypeLocation(new Paragraph(78, makeRect(60, 518, 246, 155))),
                                new QuoteLocation(new Paragraph(
                                        new TextArea(78, makeRect(60, 672, 247, 54)),
                                        new TextArea(78, makeRect(320, 44, 247, 39))
                                ))
                        ),
                        new AuspiceLocation(
                                new NameLocation(new Paragraph(78, makeRect(324, 86, 58, 18))),
                                new TitleLocation(new Paragraph(78, makeRect(323, 100, 197, 18))),
                                new DescriptionLocation(
                                        new Paragraph(78, makeRect(320, 121, 250, 77)),
                                        new Paragraph(78, makeRect(320, 200, 250, 118))
                                ),
                                new InitialRage(new Paragraph(78, makeRect(340, 318, 69, 14))),
                                new BeginningGiftsLocation(new Paragraph(78, makeRect(322, 333, 246, 26))),
                                new StereotypeLocation(new Paragraph(78, makeRect(320, 361, 247, 143))),
                                new QuoteLocation(new Paragraph(
                                        new TextArea(78, makeRect(320, 504, 246, 41))
                                ))
                        ),
                        new AuspiceLocation(
                                new NameLocation(new Paragraph(78, makeRect(322, 548, 61, 16))),
                                new TitleLocation(new Paragraph(78, makeRect(323, 563, 205, 18))),
                                new DescriptionLocation(
                                        new Paragraph(78, makeRect(321, 581, 248, 106)),
                                        new Paragraph(
                                                new TextArea(78, makeRect(323, 687, 245, 41)),
                                                new TextArea(79, makeRect(43, 45, 246, 39))
                                        )
                                ),
                                new InitialRage(new Paragraph(79, makeRect(61, 85, 65, 13))),
                                new BeginningGiftsLocation(new Paragraph(79, makeRect(41, 99, 247, 28))),
                                new StereotypeLocation(new Paragraph(79, makeRect(41, 126, 246, 157))),
                                new QuoteLocation(new Paragraph(79, makeRectOddLeft(284, 91)))
                        ),
                        new AuspiceLocation(
                                new NameLocation(new Paragraph(79, makeRect(305, 44, 55, 18))),
                                new TitleLocation(new Paragraph(79, makeRect(306, 60, 243, 17))),
                                new DescriptionLocation(
                                        new Paragraph(79, makeRect(304, 79, 246, 79)),
                                        new Paragraph(79, makeRect(304, 159, 246, 131))
                                ),
                                new InitialRage(new Paragraph(79, makeRect(304, 291, 246, 12))),
                                new BeginningGiftsLocation(new Paragraph(79, makeRect(304, 305, 246, 26))),
                                new StereotypeLocation(new Paragraph(
                                        new TextArea(79, makeRect(304, 333, 246, 39)),
                                        new TextArea(80, makeRect(61, 44, 246, 118))
                                )),
                                new QuoteLocation(new Paragraph(
                                        new TextArea(80, makeRect(61, 163, 246, 77))
                                ))
                        ),
                        new AuspiceLocation(
                                new NameLocation(new Paragraph(80, makeRect(61, 244, 246, 16))),
                                new TitleLocation(new Paragraph(80, makeRect(61, 260, 246, 16))),
                                new DescriptionLocation(
                                        new Paragraph(80, makeRect(61, 279, 246, 104)),
                                        new Paragraph(
                                                new TextArea(80, makeRect(61, 384, 246, 39)),
                                                new TextArea(80, makeRect(322, 45, 246, 115))
                                        )
                                ),
                                new InitialRage(new Paragraph(80, makeRect(322, 163, 246, 13))),
                                new BeginningGiftsLocation(new Paragraph(80, makeRect(322, 177, 246, 27))),
                                new StereotypeLocation(new Paragraph(80, makeRect(322, 204, 246, 156))),
                                new QuoteLocation(new Paragraph(80, makeRect(322, 362, 246, 52)))
                        )
                ),
                Arrays.asList(
                        new TribeLocation(
                                Tribes.BLACK_FURIES,
                                new DescriptionLocation(
                                        new Paragraph(81, makeRectOddLeft(151, 73)),
                                        new Paragraph(81, makeRectOddLeft(224, 119)),
                                        new Paragraph(
                                                new TextArea(81, makeRectOddLeft(346, 96)),
                                                new TextArea(81, makeRectOddRight(151, 96))
                                        ),
                                        new Paragraph(81, makeRectOddRight(248, 107)),
                                        new Paragraph(81, makeRectOddRight(358, 168)),
                                        new Paragraph(81, makeRectOddRight(527, 180)),
                                        new Paragraph(
                                                new TextArea(81, makeRectOddRight(710, 24)),
                                                new TextArea(82, makeRectEvenLeft(45, 263))
                                        )
                                ),
                                new AppearanceLocation(new Paragraph(82, makeRectEvenLeft(310, 46))),
                                new KinfolkTerritoryLocation(new Paragraph(82, makeRectEvenLeft(359, 120))),
                                new TribalTotemLocation(new Paragraph(82, makeRectEvenLeft(481, 71))),
                                new CharacterCreationLocation(new Paragraph(82, makeRectEvenLeft(554, 48))),
                                new InitialWillpower(new Paragraph(82, makeRectEvenRight(565, 13))),
                                new BeginningGiftsLocation(new Paragraph(82, makeRectEvenRight(579, 23))),
                                new QuoteLocation(new Paragraph(82, makeRect(177, 633, 274, 85))),
                                new StereotypesLocation(new Paragraph(82, makeRect(339, 90, 213, 438)))
                        ),
                        new TribeLocation(
                                Tribes.BONE_GNAWERS,
                                new DescriptionLocation(
                                        new Paragraph(83, makeRectOddLeft(151, 110)),
                                        new Paragraph(83, makeRectOddLeft(262, 108)),
                                        new Paragraph(83, makeRectOddLeft(372, 215)),
                                        new Paragraph(83, makeRectOddRight(151, 122)),
                                        new Paragraph(83, makeRectOddRight(275, 121)),
                                        new Paragraph(83, makeRectOddRight(396, 158)),
                                        new Paragraph(84, makeRectEvenLeft(45, 145))
                                ),
                                new AppearanceLocation(new Paragraph(84, makeRectEvenLeft(190, 84))),
                                new KinfolkTerritoryLocation(new Paragraph(84, makeRectEvenLeft(275, 156))),
                                new TribalTotemLocation(new Paragraph(84, makeRectEvenLeft(432, 49))),
                                new CharacterCreationLocation(new Paragraph(84, makeRectEvenLeft(483, 59))),
                                new InitialWillpower(new Paragraph(84, makeRectEvenLeft(543, 12))),
                                new BeginningGiftsLocation(new Paragraph(84, makeRectEvenLeft(556, 25))),
                                new QuoteLocation(new Paragraph(84, makeRect(81, 627, 468, 72))),
                                new StereotypesLocation(new Paragraph(84, makeRect(339, 90, 213, 477)))
                        ),
                        new TribeLocation(
                                Tribes.CHILDREN_OF_GAIA,
                                new DescriptionLocation(
                                        new Paragraph(85, makeRectOddLeft(151, 122)),
                                        new Paragraph(85, makeRectOddLeft(274, 84)),
                                        new Paragraph(85, makeRectOddLeft(359, 83)),
                                        new Paragraph(
                                                new TextArea(85, makeRectOddLeft(444, 25)),
                                                new TextArea(85, makeRectOddRight(151, 74))
                                        ),
                                        new Paragraph(85, makeRectOddRight(225, 61)),
                                        new Paragraph(85, makeRectOddRight(288, 119)),
                                        new Paragraph(86, makeRectEvenLeft(42, 133)),
                                        new Paragraph(86, makeRectEvenLeft(177, 36))
                                ),
                                new AppearanceLocation(new Paragraph(86, makeRectEvenLeft(216, 46))),
                                new KinfolkTerritoryLocation(new Paragraph(86, makeRectEvenLeft(264, 96))),
                                new TribalTotemLocation(new Paragraph(86, makeRectEvenLeft(361, 62))),
                                new CharacterCreationLocation(new Paragraph(86, makeRectEvenLeft(423, 60))),
                                new InitialWillpower(new Paragraph(86, makeRectEvenLeft(485, 11))),
                                new BeginningGiftsLocation(new Paragraph(86, makeRectEvenLeft(498, 24))),
                                new QuoteLocation(new Paragraph(86, makeRect(182, 601, 258, 78))),
                                new StereotypesLocation(new Paragraph(86, makeRect(339, 90, 213, 440)))
                        ),
                        new TribeLocation(
                                Tribes.FIANNA,
                                new DescriptionLocation(
                                        new Paragraph(
                                                new TextArea(87, makeRectOddLeft(151, 158)),
                                                new TextArea(87, makeRectOddRight(151, 38))
                                        ),
                                        new Paragraph(87, makeRectOddRight(189, 145)),
                                        new Paragraph(87, makeRectOddRight(335, 216)),
                                        new Paragraph(87, makeRectOddRight(552, 180)),
                                        new Paragraph(88, makeRectEvenLeft(45, 96)),
                                        new Paragraph(88, makeRectEvenLeft(142, 108))
                                ),
                                new AppearanceLocation(new Paragraph(88, makeRectEvenLeft(252, 47))),
                                new KinfolkTerritoryLocation(new Paragraph(88, makeRectEvenLeft(300, 133))),
                                new TribalTotemLocation(new Paragraph(88, makeRectEvenLeft(434, 47))),
                                new CharacterCreationLocation(new Paragraph(88, makeRectEvenLeft(484, 36))),
                                new InitialWillpower(new Paragraph(88, makeRectEvenLeft(521, 13))),
                                new BackgroundRestrictionsLocation(new Paragraph(88, makeRectEvenLeft(534, 12))),
                                new BeginningGiftsLocation(new Paragraph(88, makeRectEvenLeft(547, 25))),
                                new QuoteLocation(new Paragraph(88, makeRect(121, 619, 386, 61))),
                                new StereotypesLocation(new Paragraph(88, makeRect(339, 90, 213, 440)))
                        ),
                        new TribeLocation(
                                Tribes.GET_OF_FENRIS,
                                new DescriptionLocation(
                                        new Paragraph(89, makeRectOddLeft(151, 86)),
                                        new Paragraph(89, makeRectOddLeft(237, 83)),
                                        new Paragraph(89, makeRectOddLeft(323, 156)),
                                        new Paragraph(89, makeRectOddLeft(480, 121)),
                                        new Paragraph(
                                                new TextArea(89, makeRectOddLeft(601, 134)),
                                                new TextArea(89, makeRectOddRight(151, 61))
                                        ),
                                        new Paragraph(
                                                new TextArea(89, makeRectOddRight(213, 37)),
                                                new TextArea(90, makeRectEvenLeft(45, 119))
                                        ),
                                        new Paragraph(90, makeRectEvenLeft(165, 108))
                                ),
                                new AppearanceLocation(new Paragraph(90, makeRectEvenLeft(275, 60))),
                                new KinfolkTerritoryLocation(new Paragraph(90, makeRectEvenLeft(336, 107))),
                                new TribalTotemLocation(new Paragraph(90, makeRectEvenLeft(445, 49))),
                                new CharacterCreationLocation(new Paragraph(90, makeRectEvenLeft(495, 37))),
                                new InitialWillpower(new Paragraph(90, makeRectEvenLeft(533, 12))),
                                new BeginningGiftsLocation(new Paragraph(90, makeRectEvenLeft(546, 24))),
                                new QuoteLocation(new Paragraph(90, makeRect(177, 320, 272, 63))),
                                new StereotypesLocation(new Paragraph(90, makeRect(339, 90, 213, 463)))
                        ),
                        new TribeLocation(
                                Tribes.GLASS_WALKERS,
                                new DescriptionLocation(
                                        new Paragraph(91, makeRectOddLeft(151, 134)),
                                        new Paragraph(91, makeRectOddLeft(285, 251)),
                                        new Paragraph(
                                                new TextArea(91, makeRectOddLeft(538, 86)),
                                                new TextArea(91, makeRectOddRight(151, 98))
                                        ),
                                        new Paragraph(91, makeRectOddRight(249, 373)),
                                        new Paragraph(92, makeRectEvenLeft(45, 131))
                                ),
                                new AppearanceLocation(new Paragraph(92, makeRectEvenLeft(178, 60))),
                                new KinfolkTerritoryLocation(new Paragraph(92, makeRectEvenLeft(239, 84))),
                                new TribalTotemLocation(new Paragraph(92, makeRectEvenLeft(324, 61))),
                                new CharacterCreationLocation(new Paragraph(92, makeRectEvenLeft(386, 85))),
                                new InitialWillpower(new Paragraph(92, makeRectEvenLeft(471, 13))),
                                new BeginningGiftsLocation(new Paragraph(92, makeRectEvenLeft(484, 26))),
                                new QuoteLocation(new Paragraph(92, makeRect(133, 608, 362, 64))),
                                new StereotypesLocation(new Paragraph(92, makeRect(339, 90, 213, 440)))
                        ),
                        new TribeLocation(
                                Tribes.RED_TALONS,
                                new DescriptionLocation(
                                        new Paragraph(93, makeRectOddLeft(151, 134)),
                                        new Paragraph(
                                                new TextArea(93, makeRectOddLeft(285, 109)),
                                                new TextArea(93, makeRectOddRight(151, 98))
                                        ),
                                        new Paragraph(93, makeRectOddRight(249, 145)),
                                        new Paragraph(
                                                new TextArea(93, makeRectOddRight(395, 84)),
                                                new TextArea(94, makeRectEvenLeft(207, 109))
                                        ),
                                        new Paragraph(94, makeRectEvenLeft(316, 108))
                                ),
                                new AppearanceLocation(new Paragraph(94, makeRectEvenLeft(425, 96))),
                                new KinfolkTerritoryLocation(new Paragraph(94, makeRectEvenLeft(521, 86))),
                                new TribalTotemLocation(new Paragraph(94, makeRectEvenLeft(608, 81))),
                                new CharacterCreationLocation(new Paragraph(94, makeRectEvenRight(558, 72))),
                                new InitialWillpower(new Paragraph(94, makeRectEvenRight(632, 13))),
                                new BeginningGiftsLocation(new Paragraph(94, makeRectEvenRight(645, 25))),
                                new QuoteLocation(new Paragraph(94, makeRect(179, 687, 266, 37))),
                                new StereotypesLocation(new Paragraph(94, makeRect(339, 90, 213, 425)))
                        ),
                        new TribeLocation(
                                Tribes.SHADOW_LORDS,
                                new DescriptionLocation(
                                        new Paragraph(95, makeRectOddLeft(151, 147)),
                                        new Paragraph(95, makeRect(42, 297, 280, 122)),
                                        new Paragraph(95, makeRect(42, 418, 280, 97)),
                                        new Paragraph(95, makeRectOddLeft(516, 218)),
                                        new Paragraph(96, makeRectEvenLeft(45, 146)),
                                        new Paragraph(96, makeRectEvenLeft(190, 108))
                                ),
                                new AppearanceLocation(new Paragraph(96, makeRectEvenLeft(299, 48))),
                                new KinfolkTerritoryLocation(new Paragraph(96, makeRectEvenLeft(348, 85))),
                                new TribalTotemLocation(new Paragraph(96, makeRectEvenLeft(433, 73))),
                                new CharacterCreationLocation(new Paragraph(96, makeRectEvenLeft(507, 72))),
                                new InitialWillpower(new Paragraph(96, makeRectEvenLeft(580, 13))),
                                new BeginningGiftsLocation(new Paragraph(96, makeRectEvenLeft(594, 25))),
                                new QuoteLocation(new Paragraph(96, makeRect(90, 653, 448, 64))),
                                new StereotypesLocation(new Paragraph(96, makeRect(339, 90, 213, 475)))
                        ),
                        new TribeLocation(
                                Tribes.SILENT_STRIDERS,
                                new DescriptionLocation(
                                        new Paragraph(97, makeRect(172, 151, 379, 90)),
                                        new Paragraph(97, makeRect(319, 236, 232, 217)),
                                        new Paragraph(
                                                new TextArea(97, makeRectOddRight(455, 182)),
                                                new TextArea(98, makeRectEvenLeft(45, 71))
                                        ),
                                        new Paragraph(98, makeRectEvenLeft(118, 96)),
                                        new Paragraph(98, makeRectEvenLeft(214, 217)),
                                        new Paragraph(98, makeRectEvenLeft(432, 97))
                                ),
                                new AppearanceLocation(new Paragraph(98, makeRectEvenLeft(530, 73))),
                                new KinfolkTerritoryLocation(new Paragraph(98, makeRectEvenLeft(603, 37))),
                                new TribalTotemLocation(new Paragraph(98, makeRectEvenRight(492, 48))),
                                new CharacterCreationLocation(new Paragraph(98, makeRectEvenRight(541, 49))),
                                new InitialWillpower(new Paragraph(98, makeRectEvenRight(591, 13))),
                                new BeginningGiftsLocation(new Paragraph(98, makeRectEvenRight(606, 24))),
                                new QuoteLocation(new Paragraph(98, makeRect(168, 673, 294, 37))),
                                new StereotypesLocation(new Paragraph(98, makeRect(339, 90, 213, 356)))
                        ),
                        new TribeLocation(
                                Tribes.SILVER_FANGS,
                                new DescriptionLocation(
                                        new Paragraph(99, makeRectOddRight(151, 134)),
                                        new Paragraph(99, makeRectOddRight(284, 122)),
                                        new Paragraph(99, makeRectOddRight(407, 144)),
                                        new Paragraph(
                                                new TextArea(99, makeRectOddRight(553, 178)),
                                                new TextArea(100, makeRectEvenLeft(45, 72))
                                        ),
                                        new Paragraph(100, makeRectEvenLeft(118, 107)),
                                        new Paragraph(100, makeRectEvenLeft(226, 73)),
                                        new Paragraph(100, makeRectEvenLeft(299, 85))
                                ),
                                new AppearanceLocation(new Paragraph(100, makeRectEvenLeft(386, 60))),
                                new KinfolkTerritoryLocation(new Paragraph(100, makeRectEvenLeft(446, 110))),
                                new TribalTotemLocation(new Paragraph(100, makeRectEvenLeft(557, 37))),
                                new CharacterCreationLocation(new Paragraph(
                                        new TextArea(100, makeRectEvenLeft(595, 35)),
                                        new TextArea(100, makeRectEvenRight(557, 36))
                                )),
                                new InitialWillpower(new Paragraph(100, makeRectEvenRight(594, 12))),
                                new BeginningGiftsLocation(new Paragraph(100, makeRectEvenRight(607, 24))),
                                new QuoteLocation(new Paragraph(100, makeRect(154, 665, 324, 32))),
                                new StereotypesLocation(new Paragraph(100, makeRect(339, 90, 213, 416)))
                        ),
                        new TribeLocation(
                                Tribes.STARGAZERS,
                                new DescriptionLocation(
                                        new Paragraph(101, makeRectOddLeft(151, 85)),
                                        new Paragraph(101, makeRectOddLeft(237, 84)),
                                        new Paragraph(
                                                new TextArea(101, makeRectOddLeft(322, 169)),
                                                new TextArea(101, makeRectOddLeft(152, 24))

                                        ),
                                        new Paragraph(101, makeRectOddRight(176, 133)),
                                        new Paragraph(
                                                new TextArea(101, makeRectOddRight(309, 86)),
                                                new TextArea(102, makeRectEvenLeft(45, 48))
                                        ),
                                        new Paragraph(102, makeRectEvenLeft(94, 72)),
                                        new Paragraph(102, makeRectEvenLeft(165, 100)),
                                        new Paragraph(102, makeRectEvenLeft(264, 37))
                                ),
                                new AppearanceLocation(new Paragraph(102, makeRectEvenLeft(303, 47))),
                                new KinfolkTerritoryLocation(new Paragraph(102, makeRectEvenLeft(352, 83))),
                                new TribalTotemLocation(new Paragraph(102, makeRectEvenLeft(437, 73))),
                                new CharacterCreationLocation(new Paragraph(102, makeRectEvenLeft(510, 60))),
                                new InitialWillpower(new Paragraph(102, makeRectEvenLeft(571, 12))),
                                new BeginningGiftsLocation(new Paragraph(102, makeRectEvenLeft(585, 24))),
                                new QuoteLocation(new Paragraph(102, makeRect(67, 648, 492, 39))),
                                new StereotypesLocation(new Paragraph(102, makeRect(339, 90, 213, 431)))
                        ),
                        new TribeLocation(
                                Tribes.UKTENA,
                                new DescriptionLocation(
                                        new Paragraph(103, makeRectOddLeft(151, 85)),
                                        new Paragraph(103, makeRectOddLeft(238, 95)),
                                        new Paragraph(103, makeRectOddLeft(334, 157)),
                                        new Paragraph(103, makeRectOddLeft(492, 119)),
                                        new Paragraph(103, makeRectOddLeft(614, 120)),
                                        new Paragraph(
                                                new TextArea(103, makeRectOddRight(151, 111)),
                                                new TextArea(104, makeRectEvenLeft(45, 48))
                                        ),
                                        new Paragraph(104, makeRectEvenLeft(94, 132))
                                ),
                                new AppearanceLocation(new Paragraph(104, makeRectEvenLeft(227, 60))),
                                new KinfolkTerritoryLocation(new Paragraph(104, makeRectEvenLeft(288, 84))),
                                new TribalTotemLocation(new Paragraph(104, makeRectEvenLeft(374, 71))),
                                new CharacterCreationLocation(new Paragraph(104, makeRectEvenLeft(447, 59))),
                                new InitialWillpower(new Paragraph(104, makeRectEvenLeft(509, 12))),
                                new BeginningGiftsLocation(new Paragraph(104, makeRectEvenLeft(522, 25))),
                                new QuoteLocation(new Paragraph(104, makeRect(101, 618, 430, 50))),
                                new StereotypesLocation(new Paragraph(104, makeRect(339, 90, 213, 440)))
                        ),
                        new TribeLocation(
                                Tribes.WENDIGO,
                                new DescriptionLocation(
                                        new Paragraph(105, makeRectOddLeft(151, 86)),
                                        new Paragraph(105, makeRectOddLeft(238, 131)),
                                        new Paragraph(105, makeRectOddLeft(371, 84)),
                                        new Paragraph(105, makeRectOddLeft(456, 71)),
                                        new Paragraph(105, makeRectOddLeft(531, 180)),
                                        new Paragraph(
                                                new TextArea(105, makeRectOddLeft(712, 23)),
                                                new TextArea(105, makeRectOddRight(151, 74)),
                                                new TextArea(106, makeRectEvenLeft(45, 35))
                                        ),
                                        new Paragraph(106, makeRectEvenLeft(83, 155))
                                ),
                                new AppearanceLocation(new Paragraph(106, makeRectEvenLeft(241, 59))),
                                new KinfolkTerritoryLocation(new Paragraph(106, makeRectEvenLeft(300, 109))),
                                new TribalTotemLocation(new Paragraph(106, makeRectEvenLeft(411, 48))),
                                new CharacterCreationLocation(new Paragraph(106, makeRectEvenLeft(460, 59))),
                                new InitialWillpower(new Paragraph(106, makeRectEvenLeft(521, 12))),
                                new BeginningGiftsLocation(new Paragraph(106, makeRectEvenLeft(535, 25))),
                                new QuoteLocation(new Paragraph(106, makeRect(154, 607, 321, 52))),
                                new StereotypesLocation(new Paragraph(106, makeRect(339, 90, 213, 381)))
                        ),
                        new TribeLocation(
                                Tribes.BUNYIP,
                                new DescriptionLocation(
                                        new Paragraph(389, makeRectOddLeft(151, 118)),
                                        new Paragraph(389, makeRectOddLeft(271, 142)),
                                        new Paragraph(389, makeRectOddLeft(416, 102)),
                                        new Paragraph(389, makeRectOddLeft(521, 116)),
                                        new Paragraph(
                                                new TextArea(389, makeRectOddLeft(639, 81)),
                                                new TextArea(389, makeRectOddRight(151, 53))
                                        ),
                                        new Paragraph(
                                                new TextArea(389, makeRectOddRight(206, 259)),
                                                new TextArea(390, makeRectEvenLeft(227, 76))
                                        ),
                                        new Paragraph(390, makeRectEvenLeft(306, 103))
                                ),
                                new AppearanceLocation(new Paragraph(
                                        new TextArea(390, makeRectEvenLeft(412, 25)),
                                        new TextArea(390, makeRectEvenRight(45, 76))
                                )),
                                new KinfolkTerritoryLocation(new Paragraph(390, makeRectEvenRight(123, 103))),
                                new TribalTotemLocation(new Paragraph(390, makeRectEvenRight(229, 92))),
                                new CharacterCreationLocation(new Paragraph(390, makeRectEvenRight(322, 51))),
                                new InitialWillpower(new Paragraph(390, makeRectEvenRight(375, 12))),
                                new BackgroundRestrictionsLocation(new Paragraph(390, makeRectEvenRight(390, 12))),
                                new BeginningGiftsLocation(new Paragraph(390, makeRectEvenRight(405, 25))),
                                new QuoteLocation(new Paragraph(390, makeRect(141, 565, 346, 51)))
                        ),
                        new TribeLocation(
                                Tribes.CROATAN,
                                new DescriptionLocation(
                                        new Paragraph(391, makeRectOddLeft(151, 274)),
                                        new Paragraph(
                                                new TextArea(391, makeRectOddLeft(426, 182)),
                                                new TextArea(391, makeRectOddLeft(151, 42))
                                        ),
                                        new Paragraph(391, makeRectOddRight(191, 235)),
                                        new Paragraph(
                                                new TextArea(391, makeRectOddRight(430, 194)),
                                                new TextArea(392, makeRectEvenLeft(45, 26))
                                        ),
                                        new Paragraph(392, makeRectEvenLeft(72, 154)),
                                        new Paragraph(392, makeRectEvenLeft(229, 117))
                                ),
                                new AppearanceLocation(new Paragraph(392, makeRectEvenLeft(348, 78))),
                                new KinfolkTerritoryLocation(new Paragraph(392, makeRectEvenRight(45, 142))),
                                new TribalTotemLocation(new Paragraph(392, makeRectEvenRight(189, 91))),
                                new CharacterCreationLocation(new Paragraph(392, makeRectEvenRight(281, 63))),
                                new InitialWillpower(new Paragraph(392, makeRectEvenRight(348, 12))),
                                new BackgroundRestrictionsLocation(new Paragraph(392, makeRectEvenRight(362, 12))),
                                new BeginningGiftsLocation(new Paragraph(392, makeRectEvenRight(377, 25))),
                                new QuoteLocation(new Paragraph(392, makeRect(113, 562, 397, 76)))
                        ),
                        new TribeLocation(
                                Tribes.WHITE_HOWLERS,
                                new DescriptionLocation(
                                        new Paragraph(393, makeRect(229, 214, 321, 91)),
                                        new Paragraph(393, makeRectOddRight(307, 181)),
                                        new Paragraph(
                                                new TextArea(393, makeRectOddRight(491, 234)),
                                                new TextArea(394, makeRectEvenLeft(45, 26))
                                        ),
                                        new Paragraph(394, makeRectEvenLeft(72, 156)),
                                        new Paragraph(394, makeRectEvenLeft(229, 142)),
                                        new Paragraph(394, makeRectEvenLeft(373, 131))
                                ),
                                new AppearanceLocation(new Paragraph(394, makeRectEvenRight(45, 91))),
                                new KinfolkTerritoryLocation(new Paragraph(394, makeRectEvenRight(137, 154))),
                                new TribalTotemLocation(new Paragraph(394, makeRectEvenRight(294, 90))),
                                new CharacterCreationLocation(new Paragraph(394, makeRectEvenRight(386, 64))),
                                new InitialWillpower(new Paragraph(394, makeRectEvenRight(454, 12))),
                                new BackgroundRestrictionsLocation(new Paragraph(394, makeRectEvenRight(468, 12))),
                                new BeginningGiftsLocation(new Paragraph(394, makeRectEvenRight(482, 27))),
                                new QuoteLocation(new Paragraph(394, makeRect(125, 573, 381, 48)))
                        ),
                        new TribeLocation(
                                Tribes.BLACK_SPIRAL_DANCERS,
                                new DescriptionLocation(
                                        new Paragraph(425, makeRectOddLeft(214, 228)),
                                        new Paragraph(425, makeRectOddLeft(445, 239)),
                                        new Paragraph(
                                                new TextArea(425, makeRectOddLeft(686, 48)),
                                                new TextArea(425, makeRectOddRight(214, 120))
                                        ),
                                        new Paragraph(
                                                new TextArea(425, makeRectOddRight(336, 47)),
                                                new TextArea(426, makeRectEvenLeft(45, 84))
                                        ),
                                        new Paragraph(426, makeRectEvenLeft(130, 143)),
                                        new Paragraph(426, makeRectEvenLeft(275, 132)),
                                        new Paragraph(426, makeRectEvenLeft(409, 107))
                                ),
                                new AppearanceLocation(new Paragraph(
                                        new TextArea(426, makeRectEvenLeft(519, 59)),
                                        new TextArea(426, makeRectEvenRight(45, 107))
                                )),
                                new KinfolkTerritoryLocation(
                                        new Paragraph(426, makeRectEvenRight(154, 179)),
                                        new Paragraph(426, makeRectEvenRight(335, 48))
                                ),
                                new TribalTotemLocation(new Paragraph(426, makeRectEvenRight(385, 48))),
                                new InitialWillpower(new Paragraph(426, makeRectEvenRight(434, 12))),
                                new BackgroundRestrictionsLocation(new Paragraph(426, makeRectEvenRight(448, 59))),
                                new DerangementLocation(new Paragraph(426, makeRectEvenRight(509, 47))),
                                new BeginningGiftsLocation(new Paragraph(426, makeRectEvenRight(558, 24))),
                                new QuoteLocation(new Paragraph(426, makeRect(98, 600, 435, 122)))
                        )
                ),
                Arrays.asList(
                        new BackgroundLocation(
                                "Allies",
                                new DescriptionLocation(
                                        new Paragraph(
                                                new TextArea(136, makeRectEvenRight(704, 26)),
                                                new TextArea(137, makeRectOddLeft(45, 169))
                                        ),
                                        new Paragraph(137, makeRectOddLeft(215, 103)),
                                        new Paragraph(137, makeRectOddLeft(320, 65)),
                                        new Paragraph(137, makeRectOddLeft(388, 11))
                                ),
                                new TableParagraph(new Paragraph(137, makeRectOddLeft(402, 136)))
                        ),
                        new BackgroundLocation(
                                "Ancestors",
                                new DescriptionLocation(
                                        new Paragraph(137, makeRectOddLeft(566, 63)),
                                        new Paragraph(
                                                new TextArea(137, makeRectOddLeft(632, 103)),
                                                new TextArea(137, makeRectOddRight(45, 116))
                                        ),
                                        new Paragraph(137, makeRectOddRight(163, 38)),
                                        new Paragraph(137, makeRectOddRight(204, 77))
                                ),
                                new TableParagraph(new Paragraph(137, makeRectOddRight(285, 94)))
                        ),
                        new BackgroundLocation(
                                "Contacts",
                                new DescriptionLocation(
                                        new Paragraph(137, makeRectOddRight(407, 116)),
                                        new Paragraph(137, makeRectOddRight(526, 117)),
                                        new Paragraph(137, makeRectOddRight(645, 12))
                                ),
                                new TableParagraph(new Paragraph(137, makeRectOddRight(661, 68)))
                        ),
                        new BackgroundLocation(
                                "Totem",
                                new DescriptionLocation(
                                        new Paragraph(141, makeRectOddLeft(70, 64)),
                                        new Paragraph(141, makeRectOddLeft(136, 209)),
                                        new Paragraph(141, makeRectOddLeft(346, 90))
                                ),
                                new TableParagraph(
                                        new Paragraph(141, makeRectOddLeft(441, 232)),
                                        new Paragraph(
                                                new TextArea(141, makeRectOddLeft(681, 54)),
                                                new TextArea(141, makeRectOddRight(432, 102))
                                        )
                                )
                        )
                ),
                Arrays.asList(
                        new GiftLocations(new BreedGifts(Breeds.HOMID), Arrays.asList(
                                new Paragraph(153, makeRectOddRight(363, 370)),
                                new Paragraph(154, makeRectEvenLeft(404, 329)),
                                new Paragraph(154, makeRectEvenRight(404, 329)),
                                new Paragraph(155, makeRectOddLeft(135, 597)),
                                new Paragraph(155, makeRectOddRight(45, 695)),
                                new Paragraph(156, makeRectEvenLeft(45, 115)),
                                new Paragraph(156, makeRectEvenLeft(257, 473)),
                                new Paragraph(156, makeRectEvenRight(45, 512))
                        ), new GiftChart("Jam Technology", new TableParagraph(
                                new Paragraph(155, makeRectOddLeft(45, 85))
                        )), new GiftChart("Bury the Wolf", new TableParagraph(
                                new Paragraph(156, makeRectOddLeft(165, 87))
                        ))),
                        new GiftLocations(new BreedGifts(Breeds.METIS), Arrays.asList(
                                new Paragraph(156, makeRectEvenRight(663, 77)),
                                new Paragraph(157, makeRectOddLeft(45, 695)),
                                new Paragraph(157, makeRectOddRight(45, 695)),
                                new Paragraph(158, makeRectEvenLeft(45, 695)),
                                new Paragraph(158, makeRectEvenRight(45, 695)),
                                new Paragraph(159, makeRectOddLeft(45, 263))
                        )),
                        new GiftLocations(new BreedGifts(Breeds.LUPUS), Arrays.asList(
                                new Paragraph(159, makeRectOddRight(59, 315)),
                                new Paragraph(160, makeRectEvenLeft(45, 695)),
                                new Paragraph(160, makeRectEvenRight(45, 695)),
                                new Paragraph(161, makeRectOddLeft(45, 695)),
                                new Paragraph(161, makeRectOddRight(45, 695)),
                                new Paragraph(162, makeRectEvenLeft(45, 50))
                        )),
                        new GiftLocations(new AuspiceGifts(Auspices.RAGABASH), Arrays.asList(
                                new Paragraph(162, makeRectEvenLeft(253, 474)),
                                new Paragraph(162, makeRectEvenRight(45, 695)),
                                new Paragraph(163, makeRectOddLeft(45, 277)),
                                new Paragraph(163, makeRectOddLeft(416, 320)),
                                new Paragraph(163, makeRectOddRight(45, 695)),
                                new Paragraph(164, makeRectEvenLeft(45, 327)),
                                new Paragraph(164, makeRectEvenRight(45, 327)),
                                new Paragraph(165, makeRectOddLeft(45, 155))
                        ), new GiftChart("Gremlins", new TableParagraph(
                                new Paragraph(163, makeRectOddLeft(325, 86))
                        ))),
                        new GiftLocations(new AuspiceGifts(Auspices.THEURGE), Arrays.asList(
                                new Paragraph(165, makeRectOddLeft(265, 467)),
                                new Paragraph(165, makeRectOddRight(45, 695)),
                                new Paragraph(166, makeRectEvenLeft(45, 695)),
                                new Paragraph(166, makeRectEvenRight(45, 695)),
                                new Paragraph(167, makeRectOddLeft(45, 580))
                        )),
                        new GiftLocations(new AuspiceGifts(Auspices.PHILODOX), Arrays.asList(
                                new Paragraph(167, makeRectOddLeft(714, 26)),
                                new Paragraph(167, makeRectOddRight(45, 695)),
                                new Paragraph(168, makeRectEvenLeft(45, 695)),
                                new Paragraph(168, makeRectEvenRight(45, 695)),
                                new Paragraph(169, makeRectOddLeft(45, 695)),
                                new Paragraph(170, makeRectEvenLeft(45, 77))
                        )),
                        new GiftLocations(new AuspiceGifts(Auspices.GALLIARD), Arrays.asList(
                                new Paragraph(170, makeRectEvenLeft(200, 540)),
                                new Paragraph(170, makeRectEvenRight(45, 695)),
                                new Paragraph(171, makeRectOddLeft(45, 695)),
                                new Paragraph(171, makeRectOddRight(45, 570))
                        )),
                        new GiftLocations(new AuspiceGifts(Auspices.AHROUN), Arrays.asList(
                                new Paragraph(171, makeRectOddRight(691, 37)),
                                new Paragraph(172, makeRectEvenLeft(45, 695)),
                                new Paragraph(172, makeRectEvenRight(45, 695)),
                                new Paragraph(173, makeRectOddLeft(45, 695)),
                                new Paragraph(173, makeRectOddRight(45, 475))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.BLACK_FURIES), Arrays.asList(
                                new Paragraph(174, makeRectEvenLeft(119, 609)),
                                new Paragraph(174, makeRectEvenRight(45, 695)),
                                new Paragraph(175, makeRectOddLeft(45, 695)),
                                new Paragraph(175, makeRectOddRight(45, 198))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.BONE_GNAWERS), Arrays.asList(
                                new Paragraph(175, makeRectOddRight(295, 427)),
                                new Paragraph(176, makeRectEvenLeft(45, 695)),
                                new Paragraph(176, makeRectEvenRight(45, 695)),
                                new Paragraph(177, makeRectOddLeft(404, 331)),
                                new Paragraph(177, makeRectOddRight(404, 331)),
                                new Paragraph(178, makeRectEvenLeft(45, 90))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.CHILDREN_OF_GAIA), Arrays.asList(
                                new Paragraph(178, makeRectEvenLeft(253, 485)),
                                new Paragraph(178, makeRectEvenRight(45, 695)),
                                new Paragraph(179, makeRectOddLeft(45, 695)),
                                new Paragraph(179, makeRectOddRight(45, 596))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.FIANNA), Arrays.asList(
                                new Paragraph(179, makeRectOddRight(705, 25)),
                                new Paragraph(180, makeRectEvenLeft(45, 695)),
                                new Paragraph(180, makeRectEvenRight(45, 695)),
                                new Paragraph(181, makeRectOddLeft(45, 622))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.GET_OF_FENRIS), Arrays.asList(
                                new Paragraph(181, makeRectOddRight(45, 695)),
                                new Paragraph(182, makeRectEvenLeft(45, 348)),
                                new Paragraph(182, makeRectEvenRight(45, 348)),
                                new Paragraph(183, makeRectOddLeft(45, 695)),
                                new Paragraph(183, makeRectOddRight(392, 66))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.GLASS_WALKERS), Arrays.asList(
                                new Paragraph(183, makeRectOddRight(547, 188)),
                                new Paragraph(184, makeRectEvenLeft(45, 268)),
                                new Paragraph(184, makeRectEvenRight(45, 268)),
                                new Paragraph(185, makeRectOddLeft(45, 695)),
                                new Paragraph(185, makeRectOddRight(45, 695)),
                                new Paragraph(186, makeRectEvenLeft(45, 695))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.RED_TALONS), Arrays.asList(
                                new Paragraph(186, makeRectEvenRight(120, 610)),
                                new Paragraph(187, makeRectOddLeft(45, 239)),
                                new Paragraph(187, makeRectOddRight(45, 239)),
                                new Paragraph(188, makeRectEvenLeft(45, 695)),
                                new Paragraph(188, makeRectEvenRight(45, 695)),
                                new Paragraph(189, makeRectEvenRight(45, 264))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.SHADOW_LORDS), Arrays.asList(
                                new Paragraph(189, makeRectEvenRight(385, 352)),
                                new Paragraph(190, makeRectEvenLeft(45, 211)),
                                new Paragraph(190, makeRectEvenRight(45, 211)),
                                new Paragraph(191, makeRectOddLeft(45, 695)),
                                new Paragraph(191, makeRectOddRight(45, 695)),
                                new Paragraph(192, makeRectEvenLeft(45, 278)),
                                new Paragraph(192, makeRectEvenRight(45, 224))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.SILENT_STRIDERS), Arrays.asList(
                                new Paragraph(192, makeRectEvenRight(319, 415)),
                                new Paragraph(193, makeRectOddLeft(45, 695)),
                                new Paragraph(193, makeRectOddRight(45, 695)),
                                new Paragraph(194, makeRectEvenLeft(167, 695)),
                                new Paragraph(194, makeRectEvenRight(167, 118))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.SILVER_FANGS), Arrays.asList(
                                new Paragraph(194, makeRectEvenRight(227, 509)),
                                new Paragraph(195, makeRectOddLeft(45, 481)),
                                new Paragraph(195, makeRectOddRight(45, 695)),
                                new Paragraph(196, makeRectEvenLeft(45, 423))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.STARGAZERS), Arrays.asList(
                                new Paragraph(196, makeRectEvenLeft(557, 171)),
                                new Paragraph(196, makeRectEvenRight(45, 695)),
                                new Paragraph(197, makeRectOddLeft(45, 695)),
                                new Paragraph(197, makeRectOddRight(45, 571))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.UKTENA), Arrays.asList(
                                new Paragraph(197, makeRectOddRight(691, 40)),
                                new Paragraph(198, makeRectEvenLeft(45, 695)),
                                new Paragraph(198, makeRectEvenRight(45, 695)),
                                new Paragraph(199, makeRectOddLeft(45, 695)),
                                new Paragraph(199, makeRectOddRight(45, 695)),
                                new Paragraph(200, makeRectEvenLeft(45, 424))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.WENDIGO), Arrays.asList(
                                new Paragraph(200, makeRectEvenLeft(533, 201)),
                                new Paragraph(200, makeRectEvenRight(45, 146)),
                                new Paragraph(201, makeRectOddLeft(45, 695)),
                                new Paragraph(201, makeRectOddRight(45, 695)),
                                new Paragraph(202, makeRectEvenLeft(45, 695)),
                                new Paragraph(202, makeRectEvenRight(45, 533))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.BUNYIP), Arrays.asList(
                                new Paragraph(395, makeRectOddLeft(462, 279)),
                                new Paragraph(395, makeRectOddRight(462, 279)),
                                new Paragraph(396, makeRectEvenLeft(45, 646))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.CROATAN), Arrays.asList(
                                new Paragraph(396, makeRectEvenLeft(714, 17)),
                                new Paragraph(396, makeRectEvenRight(45, 695)),
                                new Paragraph(397, makeRectOddLeft(45, 328)),
                                new Paragraph(397, makeRectOddRight(45, 171))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.WHITE_HOWLERS), Arrays.asList(
                                new Paragraph(397, makeRectOddRight(240, 135)),
                                new Paragraph(398, makeRectEvenLeft(45, 584)),
                                new Paragraph(398, makeRectEvenRight(45, 314))
                        )),
                        new GiftLocations(new TribeGifts(Tribes.BLACK_SPIRAL_DANCERS), Arrays.asList(
                                new Paragraph(427, makeRectOddLeft(158, 573)),
                                new Paragraph(427, makeRectOddRight(45, 695)),
                                new Paragraph(428, makeRectEvenLeft(45, 695)),
                                new Paragraph(428, makeRectEvenRight(45, 533)),
                                new Paragraph(429, makeRectOddLeft(45, 610))
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

    private static Rectangle makeRectOddLeft(int top, int height) {
        return makeRect(42, top, 247, height);
    }

    private static Rectangle makeRectOddRight(int top, int height) {
        return makeRect(304, top, 247, height);
    }

    private static Rectangle makeRectEvenLeft(int top, int height) {
        return makeRect(61, top, 247, height);
    }

    private static Rectangle makeRectEvenRight(int top, int height) {
        return makeRect(322, top, 247, height);
    }

    private record Book(
            List<BreedLocation> breeds,
            List<AuspiceLocation> auspices,
            List<TribeLocation> tribes,
            List<BackgroundLocation> backgrounds,
            List<GiftLocations> gifts,
            List<RiteLocations> rites,
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
            GiftRoll giftRoll = null;
            if (fixedSys != null) {
                var matcher = Pattern.compile(".*?(([\\w-]+) \\+)? ([\\w-]+|Animal Ken)( roll)? \\([Dd]ifficulty ([^)]+)\\).*").matcher(fixedSys);
                if (matcher.matches()) {
                    // Poor templating means we pick up "bite attack rollable" which we can't use and need to exclude
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
        log.debug("Extracting Gifts");
        var giftProcessor = new GiftProcessor(parser);

        for (var textLocations : BOOK_DETAILS.gifts()) {
            log.trace("Extracting gift from {}", textLocations);
            giftProcessor.process(textLocations.group(), textLocations.locations(), textLocations.giftCharts());
            log.trace("Extracted {} gifts", giftProcessor.gifts.size());
        }

        var entries = new ArrayList<BookEntry>(giftProcessor.gifts);

        log.debug("Extracting breeds");
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

        log.debug("Extracting Auspices");
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

        log.debug("Extracting Tribes");
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

        log.debug("Extracting Melee weapons");
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

        log.debug("Extracting backgrounds");
        var dotPattern = Pattern.compile("(•+)\\s*(.*)");
        var totemHeaderPattern = Pattern.compile("(Cost)\\s*(Power)");
        var totemPattern = Pattern.compile("(\\d+)\\s*(.*)");
        for (var background : BOOK_DETAILS.backgrounds()) {
            var name = background.name();
            var description = String.join("\n", getText(parser, background.descriptionLocation()));
            if(!name.equals("Totem")) {
                List<Table.Row> rows = new ArrayList<>();
                var rating = new StringBuilder();
                var ratingDescription = new StringBuilder();
                for (String line : getTextAsLines(parser, background.tableParagraph().paragraphLocation())) {
                    var matcher = dotPattern.matcher(line);
                    if (matcher.matches()) {
                        if (rating.length() > 0) {
                            rows.add(new Table.Row(new Table.Column(fixText(rating.toString())), new Table.Column(fixText(ratingDescription.toString()))));
                            rating = new StringBuilder();
                            ratingDescription = new StringBuilder();
                        }

                        rating.append(matcher.group(1));
                        ratingDescription.append(matcher.group(2));
                    } else {
                        ratingDescription.append(line);
                    }
                }
                rows.add(new Table.Row(new Table.Column(fixText(rating.toString())), new Table.Column(fixText(ratingDescription.toString()))));
                entries.add(new Background(name, description, new Table(null, null, rows.toArray(new Table.Row[0])), "werewolf"));
            } else {
                var trailer = String.join("\n", getText(parser, background.tableParagraph().trailer()));
                Table.HeaderRow headerRow = new Table.HeaderRow(new Table.Column("Cost"), new Table.Column("Power"));
                List<Table.Row> rows = new ArrayList<>();
                var cost = new StringBuilder();
                var power = new StringBuilder();
                for (String line : getTextAsLines(parser, background.tableParagraph().paragraphLocation())) {
                    var headerMatcher = totemHeaderPattern.matcher(line);
                    var matcher = totemPattern.matcher(line);
                    if (headerMatcher.matches()) {
                        continue;
                    } else if (matcher.matches()) {
                        if (cost.length() > 0) {
                            rows.add(new Table.Row(new Table.Column(fixText(cost.toString())), new Table.Column(fixText(power.toString().replaceAll("Cost Power {2}", "")))));
                            cost = new StringBuilder();
                            power = new StringBuilder();
                        }

                        cost.append(matcher.group(1));
                        power.append(matcher.group(2));
                    } else {
                        power.append(line);
                    }
                }
                rows.add(new Table.Row(new Table.Column(fixText(cost.toString())), new Table.Column(fixText(power.toString()))));
                entries.add(new Background(name, description, new Table(headerRow, trailer, rows.toArray(new Table.Row[0])), "werewolf"));
            }
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
                    log.warn("Unable to find Tribe [{}] - assuming bad regex match and adding to paragraph", tribeName);
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
