package name.ipsi.project.fwbp.books.werewolf.locations;

import com.itextpdf.kernel.geom.Rectangle;
import name.ipsi.project.fwbp.books.shared.locations.*;

import java.util.Arrays;
import java.util.List;

import static name.ipsi.project.fwbp.books.Utils.*;

public class BackgroundLocations {

    public static final List<BackgroundLocation> DATA = Arrays.asList(
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
                    "Fate",
                    new DescriptionLocation(
                            new Paragraph(
                                    new TextArea(138, new Rectangle(81.0f, 709.89307f, 227.0f, 13.084961f)),
                                    new TextArea(138, new Rectangle(62.9974f, 696.89105f, 245.0026f, 13.084961f)),
                                    new TextArea(138, new Rectangle(62.9974f, 683.88904f, 245.0026f, 13.084961f)),
                                    new TextArea(138, new Rectangle(62.9974f, 670.887f, 245.65463f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 657.885f, 245.71936f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 644.883f, 245.69778f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 631.881f, 245.73013f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 618.87897f, 245.66544f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 605.87695f, 235.2843f, 12.9800415f))
                            ),
                            new Paragraph(
                                    new TextArea(138, new Rectangle(81.0f, 591.423f, 227.70593f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 578.42096f, 245.73016f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 565.41895f, 245.67621f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 549.107f, 245.0026f, 13.0f)),
                                    new TextArea(138, new Rectangle(62.9974f, 539.4149f, 245.70856f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 526.4129f, 245.66547f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 513.4109f, 245.67624f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(62.9974f, 500.4089f, 245.68701f, 12.980011f)),
                                    new TextArea(138, new Rectangle(62.9974f, 487.4069f, 245.67624f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 474.40488f, 245.68704f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 461.40286f, 153.2916f, 12.97998f))
                            ),
                            new Paragraph(
                                    new TextArea(138, new Rectangle(81.0f, 446.94885f, 227.66284f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 433.94684f, 245.67612f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 420.94482f, 245.65463f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 407.9428f, 245.71924f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 394.9408f, 245.66534f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 381.93878f, 245.67612f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 368.93677f, 245.7193f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 355.93475f, 245.73007f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 342.93274f, 245.65466f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 329.93073f, 245.73007f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 316.9287f, 245.6438f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 303.9267f, 245.64386f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 290.92468f, 208.26953f, 12.97998f))
                            ),
                            new Paragraph(
                                    new TextArea(138, new Rectangle(81.0f, 276.47067f, 227.67365f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 263.46866f, 245.46082f, 12.97998f)),
                                    new TextArea(138, new Rectangle(62.9974f, 250.46664f, 245.69778f, 12.980011f)),
                                    new TextArea(138, new Rectangle(62.9974f, 237.46465f, 245.73016f, 12.979996f)),
                                    new TextArea(138, new Rectangle(62.9974f, 224.46265f, 245.78394f, 12.979996f)),
                                    new TextArea(138, new Rectangle(62.9974f, 211.46065f, 245.69775f, 12.979996f)),
                                    new TextArea(138, new Rectangle(62.9974f, 198.45865f, 242.97043f, 12.979996f))
                            ),
                            new Paragraph(
                                    new TextArea(138, new Rectangle(81.0f, 184.00465f, 149.37848f, 12.979996f))
                            )
                    ),
                    new TableParagraph(new Paragraph(
                            new TextArea(138, new Rectangle(63.0f, 169.6431f, 245.73013f, 12.979996f)),
                            new TextArea(138, new Rectangle(99.0f, 156.6431f, 209.66025f, 12.979996f)),
                            new TextArea(138, new Rectangle(99.0f, 143.6411f, 209.66028f, 12.979996f)),
                            new TextArea(138, new Rectangle(99.0f, 130.6391f, 71.95653f, 12.979996f)),
                            new TextArea(138, new Rectangle(63.0f, 116.1931f, 245.54678f, 12.979996f)),
                            new TextArea(138, new Rectangle(99.0f, 103.1931f, 209.6926f, 12.980003f)),
                            new TextArea(138, new Rectangle(99.0f, 90.1911f, 209.70337f, 12.980003f)),
                            new TextArea(138, new Rectangle(99.0f, 77.1891f, 209.69257f, 12.980003f)),
                            new TextArea(138, new Rectangle(99.0f, 64.1871f, 203.89294f, 12.980003f)),
                            new TextArea(138, new Rectangle(324.0f, 736.0f, 245.71924f, 12.9800415f)),
                            new TextArea(138, new Rectangle(360.0f, 723.0f, 209.56317f, 12.9800415f)),
                            new TextArea(138, new Rectangle(322.0f, 709.89307f, 247.0f, 13.084961f)),
                            new TextArea(138, new Rectangle(322.0f, 696.89105f, 247.0f, 13.084961f)),
                            new TextArea(138, new Rectangle(322.0f, 683.88904f, 247.0f, 13.084961f)),
                            new TextArea(138, new Rectangle(324.0f, 669.54987f, 245.68701f, 12.9800415f)),
                            new TextArea(138, new Rectangle(360.0f, 656.54987f, 209.70331f, 12.9800415f)),
                            new TextArea(138, new Rectangle(360.0f, 643.54785f, 209.67102f, 12.9800415f)),
                            new TextArea(138, new Rectangle(360.0f, 630.54584f, 209.64948f, 12.9800415f)),
                            new TextArea(138, new Rectangle(360.0f, 617.5438f, 129.2522f, 12.9800415f)),
                            new TextArea(138, new Rectangle(324.0f, 603.1f, 245.73022f, 12.9800415f)),
                            new TextArea(138, new Rectangle(360.0f, 590.1f, 209.86505f, 12.9800415f)),
                            new TextArea(138, new Rectangle(360.0f, 577.09796f, 189.51239f, 12.9800415f))
                    ))
            ),
            new BackgroundLocation(
                    "Fetish",
                    new DescriptionLocation(
                            new Paragraph(
                                    new TextArea(138, new Rectangle(342.0f, 536.0f, 227.66278f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(323.9974f, 522.998f, 245.70859f, 12.9800415f)),
                                    new TextArea(138, new Rectangle(323.9974f, 509.996f, 245.71933f, 12.980011f)),
                                    new TextArea(138, new Rectangle(323.9974f, 496.994f, 245.70859f, 12.97998f)),
                                    new TextArea(138, new Rectangle(323.9974f, 483.99197f, 245.70859f, 12.97998f)),
                                    new TextArea(138, new Rectangle(323.9974f, 470.98996f, 120.746796f, 12.97998f)),
                                    new TextArea(138, new Rectangle(323.9974f, 457.98795f, 43.723694f, 12.97998f))
                            )
                    ),
                    new TableParagraph(new Paragraph(
                            new TextArea(139, new Rectangle(45.0f, 736.0f, 244.0f, 12.9800415f)),
                            new TextArea(139, new Rectangle(45.0f, 721.54987f, 244.0f, 12.9800415f)),
                            new TextArea(139, new Rectangle(81.0f, 708.54987f, 56.886063f, 12.9800415f)),
                            new TextArea(139, new Rectangle(45.0f, 694.1f, 245.67618f, 12.9800415f)),
                            new TextArea(139, new Rectangle(81.0f, 681.1f, 52.68187f, 12.9800415f)),
                            new TextArea(139, new Rectangle(45.0f, 666.64996f, 245.67618f, 12.9800415f)),
                            new TextArea(139, new Rectangle(81.0f, 650.20703f, 208.0f, 13.0f)),
                            new TextArea(139, new Rectangle(45.0f, 639.2f, 245.67618f, 12.9800415f)),
                            new TextArea(139, new Rectangle(81.0f, 626.2f, 46.106056f, 12.9800415f))
                    ))
            ),
            new BackgroundLocation(
                    "Kinfolk",
                    new DescriptionLocation(
                            new Paragraph(
                                    new TextArea(139, new Rectangle(63.0f, 585.0919f, 226.0f, 12.988098f)),
                                    new TextArea(139, new Rectangle(44.9974f, 572.0899f, 244.0026f, 12.988098f)),
                                    new TextArea(139, new Rectangle(44.9974f, 559.0879f, 244.0026f, 12.988098f)),
                                    new TextArea(139, new Rectangle(44.9974f, 546.0859f, 244.0026f, 12.988098f)),
                                    new TextArea(139, new Rectangle(44.9974f, 533.08386f, 244.0026f, 12.988098f)),
                                    new TextArea(139, new Rectangle(44.9974f, 520.08185f, 244.0026f, 12.988098f)),
                                    new TextArea(139, new Rectangle(44.9974f, 507.07986f, 244.0026f, 12.988068f)),
                                    new TextArea(139, new Rectangle(44.9974f, 494.07785f, 244.0026f, 12.988037f)),
                                    new TextArea(139, new Rectangle(44.9974f, 481.0839f, 245.67612f, 12.97998f)),
                                    new TextArea(139, new Rectangle(44.9974f, 468.08188f, 245.68707f, 12.97998f)),
                                    new TextArea(139, new Rectangle(44.9974f, 455.07986f, 245.68692f, 12.97998f)),
                                    new TextArea(139, new Rectangle(44.9974f, 442.07785f, 245.66537f, 12.97998f)),
                                    new TextArea(139, new Rectangle(44.9974f, 429.07584f, 192.86493f, 12.97998f))
                            ),
                            new Paragraph(
                                    new TextArea(139, new Rectangle(63.0f, 414.61377f, 226.0f, 12.988037f))
                            )
                    ),
                    new TableParagraph(new Paragraph(
                            new TextArea(139, new Rectangle(45.0f, 400.2f, 90.03456f, 12.97998f)),
                            new TextArea(139, new Rectangle(45.0f, 385.75f, 90.21782f, 12.97998f)),
                            new TextArea(139, new Rectangle(45.0f, 371.30002f, 82.43466f, 12.97998f)),
                            new TextArea(139, new Rectangle(45.0f, 356.85f, 82.43466f, 12.97998f)),
                            new TextArea(139, new Rectangle(45.0f, 342.40002f, 82.43466f, 12.97998f))
                    ))
            ),
            new BackgroundLocation(
                    "Mentor",
                    new DescriptionLocation(
                            new Paragraph(
                                    new TextArea(139, new Rectangle(63.0f, 301.30002f, 227.66284f, 12.97998f)),
                                    new TextArea(139, new Rectangle(44.9974f, 288.298f, 245.67621f, 12.97998f)),
                                    new TextArea(139, new Rectangle(44.9974f, 275.296f, 245.67621f, 12.97998f)),
                                    new TextArea(139, new Rectangle(44.9974f, 262.29398f, 245.70859f, 12.97998f)),
                                    new TextArea(139, new Rectangle(44.9974f, 249.29196f, 245.68701f, 12.980011f)),
                                    new TextArea(139, new Rectangle(44.9974f, 236.28996f, 245.70853f, 12.979996f)),
                                    new TextArea(139, new Rectangle(44.9974f, 223.28796f, 245.65463f, 12.979996f)),
                                    new TextArea(139, new Rectangle(44.9974f, 210.28596f, 245.65463f, 12.979996f)),
                                    new TextArea(139, new Rectangle(44.9974f, 197.28397f, 245.68701f, 12.979996f)),
                                    new TextArea(139, new Rectangle(44.9974f, 184.28197f, 245.68701f, 12.979996f)),
                                    new TextArea(139, new Rectangle(44.9974f, 171.27997f, 245.68695f, 12.979996f)),
                                    new TextArea(139, new Rectangle(44.9974f, 158.27797f, 245.70856f, 12.979996f)),
                                    new TextArea(139, new Rectangle(44.9974f, 145.27597f, 245.71933f, 12.979996f)),
                                    new TextArea(139, new Rectangle(44.9974f, 132.27397f, 245.70856f, 12.979996f)),
                                    new TextArea(139, new Rectangle(44.9974f, 119.27197f, 245.65466f, 12.979996f)),
                                    new TextArea(139, new Rectangle(44.9974f, 106.26997f, 210.72748f, 12.980003f))
                            ),
                            new Paragraph(
                                    new TextArea(139, new Rectangle(63.0f, 91.81597f, 226.0f, 13.010033f)),
                                    new TextArea(139, new Rectangle(44.9974f, 78.81397f, 244.0026f, 13.010033f)),
                                    new TextArea(139, new Rectangle(44.9974f, 65.81197f, 244.0026f, 13.010033f)),
                                    new TextArea(139, new Rectangle(44.9974f, 52.80998f, 242.94876f, 12.98f))
                            )
                    ),
                    new TableParagraph(new Paragraph(
                            new TextArea(139, new Rectangle(304.0f, 736.0f, 247.0f, 12.9800415f)),
                            new TextArea(139, new Rectangle(304.0f, 721.54987f, 247.0f, 12.9800415f)),
                            new TextArea(139, new Rectangle(306.0f, 707.1f, 112.359924f, 12.9800415f)),
                            new TextArea(139, new Rectangle(306.0f, 692.64996f, 112.359924f, 12.9800415f)),
                            new TextArea(139, new Rectangle(306.0f, 678.2f, 112.359924f, 12.9800415f))
                    ))
            ),
            new BackgroundLocation(
                    "Pure Breed",
                    new DescriptionLocation(
                            new Paragraph(
                                    new TextArea(139, new Rectangle(324.0f, 637.1f, 227.706f, 12.9800415f)),
                                    new TextArea(139, new Rectangle(305.9974f, 624.09796f, 245.73013f, 12.9800415f)),
                                    new TextArea(139, new Rectangle(305.9974f, 611.09595f, 245.70853f, 12.9800415f)),
                                    new TextArea(139, new Rectangle(305.9974f, 598.09393f, 245.40695f, 12.9800415f)),
                                    new TextArea(139, new Rectangle(304.0f, 585.0919f, 247.0f, 12.988098f)),
                                    new TextArea(139, new Rectangle(304.0f, 572.0899f, 247.0f, 12.988098f)),
                                    new TextArea(139, new Rectangle(304.0f, 559.0879f, 247.0f, 12.988098f)),
                                    new TextArea(139, new Rectangle(304.0f, 546.0859f, 247.0f, 12.988098f)),
                                    new TextArea(139, new Rectangle(304.0f, 533.08386f, 247.0f, 12.988098f)),
                                    new TextArea(139, new Rectangle(304.0f, 520.08185f, 247.0f, 12.988098f)),
                                    new TextArea(139, new Rectangle(304.0f, 507.07986f, 247.0f, 12.988068f)),
                                    new TextArea(139, new Rectangle(304.0f, 494.07785f, 247.0f, 12.988037f))
                            ),
                            new Paragraph(
                                    new TextArea(139, new Rectangle(324.0f, 479.62384f, 227.5658f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 466.62183f, 245.66544f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 453.6198f, 245.67624f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 440.6178f, 245.70853f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 427.61578f, 245.71939f, 12.97998f)),
                                    new TextArea(139, new Rectangle(304.0f, 414.61377f, 247.0f, 12.988037f)),
                                    new TextArea(139, new Rectangle(305.9974f, 401.61176f, 245.67624f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 388.60974f, 245.69778f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 375.60773f, 155.36136f, 12.97998f))
                            ),
                            new Paragraph(
                                    new TextArea(139, new Rectangle(324.0f, 361.15372f, 227.71674f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 348.1517f, 245.68698f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 335.1497f, 245.70859f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 322.14767f, 245.66544f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 309.14566f, 245.65463f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 296.14365f, 245.66544f, 12.97998f)),
                                    new TextArea(139, new Rectangle(305.9974f, 283.14163f, 92.4924f, 12.97998f))
                            )
                    ),
                    new TableParagraph(new Paragraph(
                            new TextArea(139, new Rectangle(306.0f, 268.75f, 155.24277f, 12.97998f)),
                            new TextArea(139, new Rectangle(306.0f, 254.3f, 245.73004f, 12.979996f)),
                            new TextArea(139, new Rectangle(342.0f, 241.3f, 209.68176f, 12.979996f)),
                            new TextArea(139, new Rectangle(342.0f, 228.298f, 47.313416f, 12.979996f)),
                            new TextArea(139, new Rectangle(306.0f, 213.84999f, 245.71936f, 12.979996f)),
                            new TextArea(139, new Rectangle(342.0f, 200.84999f, 120.477264f, 12.979996f)),
                            new TextArea(139, new Rectangle(306.0f, 186.4f, 243.0f, 12.979996f)),
                            new TextArea(139, new Rectangle(342.00247f, 173.398f, 60.465027f, 12.979996f)),
                            new TextArea(139, new Rectangle(306.0f, 158.95f, 197.67285f, 12.979996f))
                    ))
            ),
            new BackgroundLocation(
                    "Resources",
                    new DescriptionLocation(
                            new Paragraph(
                                    new TextArea(139, new Rectangle(324.0f, 117.85f, 227.70587f, 12.980003f)),
                                    new TextArea(139, new Rectangle(305.9974f, 104.848f, 245.68692f, 12.980003f)),
                                    new TextArea(139, new Rectangle(304.0f, 91.81597f, 247.0f, 13.010033f)),
                                    new TextArea(139, new Rectangle(304.0f, 78.81397f, 247.0f, 13.010033f)),
                                    new TextArea(139, new Rectangle(304.0f, 65.81197f, 247.0f, 13.010033f)),
                                    new TextArea(140, new Rectangle(63.0f, 735.994f, 245.0f, 12.986023f)),
                                    new TextArea(140, new Rectangle(63.0f, 722.992f, 245.0f, 12.988037f)),
                                    new TextArea(140, new Rectangle(63.0f, 709.99f, 245.0f, 12.988037f)),
                                    new TextArea(140, new Rectangle(63.0f, 696.988f, 245.0f, 12.988037f)),
                                    new TextArea(140, new Rectangle(63.0f, 683.98596f, 245.0f, 12.988037f))
                            ),
                            new Paragraph(
                                    new TextArea(140, new Rectangle(81.0026f, 669.54f, 227.7167f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(63.0f, 656.53796f, 245.66534f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(63.0f, 643.53595f, 245.39618f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(63.0f, 630.53394f, 245.7193f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(63.0f, 617.5319f, 245.67615f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(63.0f, 604.5299f, 245.0f, 13.0f)),
                                    new TextArea(140, new Rectangle(63.0f, 591.5279f, 245.69772f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(63.0f, 578.5259f, 245.70844f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(63.0f, 565.52386f, 245.67615f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(63.0f, 552.52185f, 189.08119f, 12.9800415f))
                            ),
                            new Paragraph(
                                    new TextArea(140, new Rectangle(81.0026f, 538.0679f, 171.5314f, 12.9800415f))
                            )
                    ),
                    new TableParagraph(new Paragraph(
                            new TextArea(140, new Rectangle(63.0f, 523.64996f, 245.69775f, 12.9800415f)),
                            new TextArea(140, new Rectangle(99.0f, 510.65f, 209.66022f, 12.980011f)),
                            new TextArea(140, new Rectangle(99.0f, 497.64798f, 209.75723f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 484.64597f, 131.1495f, 12.97998f)),
                            new TextArea(140, new Rectangle(63.0f, 470.2f, 243.00006f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0025f, 457.198f, 209.78958f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0025f, 444.19598f, 209.67102f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0025f, 431.19397f, 209.70337f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0025f, 418.19196f, 206.99756f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0025f, 405.18994f, 183.52954f, 12.97998f)),
                            new TextArea(140, new Rectangle(63.0f, 390.75f, 245.68698f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 377.75f, 209.71411f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 364.748f, 209.6926f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 351.74597f, 209.6926f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 338.74396f, 209.47693f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 325.74194f, 209.68182f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 312.73993f, 207.0299f, 12.97998f)),
                            new TextArea(140, new Rectangle(63.0f, 298.30002f, 245.70856f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 285.30002f, 209.6818f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 272.298f, 209.6926f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 259.296f, 209.66022f, 12.97998f)),
                            new TextArea(140, new Rectangle(99.0f, 246.29398f, 209.72491f, 12.980011f)),
                            new TextArea(140, new Rectangle(99.0f, 233.29198f, 209.71414f, 12.979996f)),
                            new TextArea(140, new Rectangle(99.0f, 220.28998f, 207.04068f, 12.979996f)),
                            new TextArea(140, new Rectangle(63.0f, 205.84999f, 245.55762f, 12.979996f)),
                            new TextArea(140, new Rectangle(99.0f, 192.84999f, 209.70331f, 12.979996f)),
                            new TextArea(140, new Rectangle(99.0f, 179.84799f, 209.72485f, 12.979996f)),
                            new TextArea(140, new Rectangle(99.0f, 166.846f, 209.68176f, 12.979996f)),
                            new TextArea(140, new Rectangle(99.0f, 153.833f, 209.70337f, 12.990997f)),
                            new TextArea(140, new Rectangle(99.0f, 140.842f, 209.72485f, 12.979996f)),
                            new TextArea(140, new Rectangle(99.0f, 127.84f, 209.71405f, 12.979996f)),
                            new TextArea(140, new Rectangle(99.0f, 114.838f, 196.30377f, 12.980003f))
                    ))
            ),
            new BackgroundLocation(
                    "Rites",
                    new DescriptionLocation(
                            new Paragraph(
                                    new TextArea(140, new Rectangle(81.0f, 73.75f, 227.67358f, 12.980003f)),
                                    new TextArea(140, new Rectangle(62.9974f, 60.748005f, 245.69781f, 12.98f)),
                                    new TextArea(140, new Rectangle(322.0f, 735.994f, 247.0f, 12.986023f)),
                                    new TextArea(140, new Rectangle(322.0f, 722.992f, 247.0f, 12.988037f)),
                                    new TextArea(140, new Rectangle(322.0f, 709.99f, 247.0f, 12.988037f)),
                                    new TextArea(140, new Rectangle(322.0f, 696.988f, 247.0f, 12.988037f)),
                                    new TextArea(140, new Rectangle(322.0f, 683.98596f, 247.0f, 12.988037f)),
                                    new TextArea(140, new Rectangle(323.99197f, 670.98395f, 245.68698f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(323.99197f, 657.98193f, 245.73013f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(323.99197f, 644.9799f, 245.67618f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(323.99197f, 631.9779f, 245.6547f, 12.9800415f)),
                                    new TextArea(140, new Rectangle(323.99197f, 618.9759f, 121.5553f, 12.9800415f))
                            )
                    ),
                    new TableParagraph(new Paragraph(
                            new TextArea(140, new Rectangle(322.0f, 604.5299f, 247.0f, 13.0f)),
                            new TextArea(140, new Rectangle(324.0f, 590.1f, 160.36328f, 12.9800415f)),
                            new TextArea(140, new Rectangle(324.0f, 575.64996f, 166.93909f, 12.9800415f)),
                            new TextArea(140, new Rectangle(324.0f, 561.2f, 161.55984f, 12.9800415f)),
                            new TextArea(140, new Rectangle(324.0f, 546.75f, 160.36328f, 12.9800415f))
                    ))
            ),
            new BackgroundLocation(
                    "Spirit Heritage",
                    new DescriptionLocation(
                            new Paragraph(
                                    new TextArea(140, new Rectangle(342.0f, 505.65002f, 227.68439f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 492.648f, 245.69778f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 479.646f, 245.67624f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 466.64398f, 245.6763f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 453.64197f, 245.6871f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 440.63995f, 245.65475f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 427.63794f, 245.69778f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 414.63593f, 245.68704f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 401.6339f, 245.69785f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 388.6319f, 245.70853f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 375.62988f, 87.856995f, 12.97998f))
                            ),
                            new Paragraph(
                                    new TextArea(140, new Rectangle(342.0f, 361.17587f, 227.7168f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 348.17386f, 245.6871f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 335.17184f, 245.6763f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 322.16983f, 245.71939f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 309.16782f, 245.6655f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 296.1658f, 245.67636f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 283.1638f, 245.73013f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 270.16177f, 245.71939f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 257.15976f, 245.6655f, 12.97998f)),
                                    new TextArea(140, new Rectangle(323.9974f, 244.15775f, 245.70859f, 12.980011f)),
                                    new TextArea(140, new Rectangle(323.9974f, 231.15575f, 70.36105f, 12.979996f))
                            )
                    ),
                    new TableParagraph(new Paragraph(
                            new TextArea(140, new Rectangle(324.0f, 216.75f, 245.7085f, 12.979996f)),
                            new TextArea(140, new Rectangle(360.0f, 203.75f, 53.285553f, 12.979996f)),
                            new TextArea(140, new Rectangle(324.0f, 189.2999f, 245.73004f, 12.979996f)),
                            new TextArea(140, new Rectangle(360.0f, 176.2999f, 209.72491f, 12.979996f)),
                            new TextArea(140, new Rectangle(360.0f, 163.2979f, 154.59601f, 12.979996f)),
                            new TextArea(140, new Rectangle(324.0f, 148.84999f, 245.49298f, 12.979996f)),
                            new TextArea(140, new Rectangle(360.0f, 135.84999f, 189.79266f, 12.979996f)),
                            new TextArea(140, new Rectangle(324.0f, 121.399994f, 245.7085f, 12.979996f)),
                            new TextArea(140, new Rectangle(360.0f, 108.399994f, 209.49847f, 12.980003f)),
                            new TextArea(140, new Rectangle(360.0f, 95.397995f, 209.72491f, 12.980003f)),
                            new TextArea(140, new Rectangle(360.0f, 82.395996f, 39.21765f, 12.980003f)),
                            new TextArea(140, new Rectangle(324.0f, 67.938995f, 236.88318f, 12.991005f))
                    ))
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
    );
}
