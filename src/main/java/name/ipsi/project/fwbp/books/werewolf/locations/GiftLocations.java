package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.shared.locations.Paragraph;
import name.ipsi.project.fwbp.books.shared.locations.TableParagraph;
import name.ipsi.project.fwbp.books.werewolf.Auspices;
import name.ipsi.project.fwbp.books.werewolf.Breeds;
import name.ipsi.project.fwbp.books.werewolf.Tribes;

import java.util.Arrays;
import java.util.List;

import static name.ipsi.project.fwbp.books.Utils.*;

public record GiftLocations(
        GiftGroup group,
        List<Paragraph> locations,
        GiftChart... giftCharts
) {
    public static final List<GiftLocations> DATA = Arrays.asList(
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
    );
}
