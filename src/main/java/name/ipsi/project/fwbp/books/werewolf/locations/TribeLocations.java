package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.shared.locations.*;
import name.ipsi.project.fwbp.books.werewolf.Tribes;

import java.util.Arrays;
import java.util.List;

import static name.ipsi.project.fwbp.books.Utils.*;

public class TribeLocations {
    public static final List<TribeLocation> DATA = Arrays.asList(
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
    );
}
