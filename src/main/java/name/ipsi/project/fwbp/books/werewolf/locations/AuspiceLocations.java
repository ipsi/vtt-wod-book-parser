package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.shared.locations.*;

import java.util.Arrays;
import java.util.List;

import static name.ipsi.project.fwbp.books.Utils.makeRect;
import static name.ipsi.project.fwbp.books.Utils.makeRectOddLeft;

public class AuspiceLocations {
    public static final List<AuspiceLocation> DATA = Arrays.asList(
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
    );
}
