package name.ipsi.project.fwbp.books;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import com.itextpdf.kernel.pdf.canvas.parser.filter.TextRegionEventFilter;
import com.itextpdf.kernel.pdf.canvas.parser.listener.FilteredTextEventListener;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<String> getText(PdfDocumentContentParser parser, Content content) {
        List<String> paragraphs = new ArrayList<>(content.content().length);
        for (var location : content.content()) {
            var builder = new StringBuilder();
            for (TextArea textArea : location.locations()) {
                builder.append(fixText(parser
                        .processContent(textArea.page(), new FilteredTextEventListener(
                                new SimpleTextExtractionStrategy(),
                                new TextRegionEventFilter(textArea.location()))
                        ).getResultantText()
                        .trim()
                        .replaceAll("[\n\r]", ""))
                ).append(" ");
            }
            paragraphs.add(builder.toString().trim());
        }
        return paragraphs;
    }

    public static List<String> getTextAsLines(PdfDocumentContentParser parser, Content content) {
        List<String> lines = new ArrayList<>(content.content().length*10);
        for (var location : content.content()) {
            for (TextArea textArea : location.locations()) {
                lines.addAll(Arrays.asList(parser
                        .processContent(textArea.page(), new FilteredTextEventListener(
                                new SimpleTextExtractionStrategy(),
                                new TextRegionEventFilter(textArea.location()))
                        ).getResultantText()
                        .trim()
                        .split("[\n\r]+"))
                );
            }
        }
        return lines;
    }

    public static String fixText(String text) {
        return text
                .replaceAll("’ s", "'s")
                .replaceAll("’", "'")
                .replaceAll("“", "\"")
                .replaceAll(" \\. | \\.$", ". ")
                .replaceAll("(\\w)\\.(\\w)", "$1. $2")
                .replaceAll("T wo", "Two")
                .replaceAll("de- ?formity", "deformity")
                .replaceAll("con- ?centration", "concentration")
                .replaceAll("andyet", "and yet")
                .replaceAll("T ?ake", "Take")
                .replaceAll("What- ?ever", "Whatever")
                .replaceAll("out- ?weighs", "outweighs")
                .replaceAll("• ", "")
                .replaceAll("Y ?our", "Your")
                .replaceAll("aggrava- ?tions", "aggravations")
                .replaceAll("W ?erewolves", "Werewolves")
                .replaceAll("were- ?wolf", "werewolf")
                .replaceAll("nu- ?ances", "nuances")
                .replaceAll("discon- ?nection", "disconnection")
                .replaceAll("suc- ?cessful", "successful")
                .replaceAll("crea- ?tures", "creatures")
                .replaceAll("(\\w+)- (\\w+)", "$1$2")
                .replaceAll("T aking", "Taking")
                .replaceAll("T rue", "True")
                .replaceAll("T oxin", "Toxin")
                .replaceAll("T ouch", "Touch")
                .replaceAll("T roll", "Troll")
                .replaceAll("V enom", "Venom")
                .replaceAll("hareor", "hare or")
                .replaceAll("Jam T echnology", "Jam Technology")
                .replaceAll("T rackless Waste", "Trackless Waste")
                .replaceAll("Sense W yrm", "Sense Wyrm")
                .replaceAll("roadrunneror", "roadrunner- or")
                .replaceAll("T read Sebek's Back", "Tread Sebek's Back")
                .replaceAll("T alons of the Falcon", "Talons of the Falcon")
                .replaceAll("Resist T emptation", "Resist Temptation")
                .replaceAll("Banish T otem", "Banish Totem")
                .replaceAll("hu- ?mankind", "humankind");
    }

    public static Rectangle makeRect(int left, int top, int width, int height) {
        return new Rectangle(left, (792 - top) - height, (float) width, (float) height);
    }
}
