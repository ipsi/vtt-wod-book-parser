package name.ipsi.project.fwbp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

public class Preprocessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Preprocessor.class);

    private static final Pattern DOT_PATTERN = Pattern.compile("^(•+)");

    private final Map<Integer, Collection<IPdfTextLocation>> stringLocations;
    private final StringBuilder output;

    private Preprocessor(Map<Integer, Collection<IPdfTextLocation>> stringLocations) {
        this.stringLocations = stringLocations;
        output = new StringBuilder();
    }

    public static void process(Path jsonFile, String bookId) throws Exception {
        var entries = new ObjectMapper().readValue(Files.readAllBytes(jsonFile), RawBook.class);
//        var doc = Downloader.downloadFile(Werewolf20Extractor.BOOK_ID, System.getenv("DTRPG_TOKEN"));
        var doc = new PdfDocument(new PdfReader("/Volumes/books/Roleplaying/Werewolf the Apocalypse/Werewolf the Apocalypse 20th Anniversary Edition.pdf"));
        var parser = new PdfDocumentContentParser(doc);
        Map<Integer, Collection<IPdfTextLocation>> stringLocations = new HashMap<>(doc.getNumberOfPages());

        for (int i = 1; i <= doc.getNumberOfPages(); i++) {
            stringLocations.put(i, parser.processContent(i, new RegexBasedLocationExtractionStrategy(".*\n")).getResultantLocations());
        }

        var p = new Preprocessor(stringLocations);
        p.doProcessing(entries);
        System.out.println(p.output);
    }

    private void doProcessing(RawBook entries) {
        for (var background : entries.backgrounds()) {
            output.append("                        new BackgroundLocation(\n")
                    .append("                                \"").append(background.name())
                    .append("\",\n                                new DescriptionLocation(\n");
            for (var paragraph : background.paragraphs.stream().filter(p -> p.type().equals("text")).toList()) {
                output.append("                                        new Paragraph(\n");
                if (paragraph.areas != null && !paragraph.areas.isEmpty()) {
                    for (var area : paragraph.areas) {
                        buildTextArea(area.page(), area.content());
                    }
                    // remove trailing comma
                    output.deleteCharAt(output.length() - 2);
                } else {
                    buildTextArea(paragraph.page(), paragraph.content());
                    // remove trailing comma
                    output.deleteCharAt(output.length() - 2);
                }
                output.append("                                        ),\n");
            }
            output.deleteCharAt(output.length() - 2);
            output.append("                                ),\n");
            var tableOptional = background.paragraphs.stream().filter(p -> p.type.equals("table")).findFirst();
            if (tableOptional.isPresent()) {
                var table = tableOptional.get();
                output.append("                                new TableParagraph(new Paragraph(\n");
                buildTextArea(table.page(), table.content());
                // remove trailing comma
                output.deleteCharAt(output.length() - 2);
                output.append("                                ))\n");
            }
            // remove trailing comma
            output.append("                        ),\n");
        }
        // remove trailing comma
        output.deleteCharAt(output.length() - 2);
    }

    private void buildTextArea(int page, String content) {
        for (var rect : processLines(page, content)) {
            if (rect.getHeight() > 13.5f) {
                LOGGER.debug("rect{{ x: {}, y: {}, width: {}, height: {} }} on page {} has a height of greater than 13.5 - setting to 13.0", rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), page);
                rect.setHeight(13.0f);
            }
            output.append("                                                new TextArea(").append(page).append(", new Rectangle(")
                    .append(rect.getX()).append("f, ")
                    .append(rect.getY()).append("f, ")
                    .append(rect.getWidth()).append("f, ")
                    .append(rect.getHeight()).append("f")
                    .append(")),\n")
            ;
        }
    }

    private List<Rectangle> processLines(int page, String content) {
        var rv = new ArrayList<Rectangle>();
        boolean skipNext = false;
        String[] lines = content.split("\n");
        nextline:
        for (int i = 0; i < lines.length; i++) {
            var line = lines[i];
            if (skipNext) {
                skipNext = false;
                LOGGER.debug("skipping line [{}] as it's likely the next part of a hyphenated line", line);
                continue;
            }

            Rectangle matchedLocation = null;
            var pdfTextLocationCollection = stringLocations.get(page);
            if (pdfTextLocationCollection == null) {
                LOGGER.error("Unable to find list of text on page {}", page);
                return Collections.emptyList();
            }
            for (var textLocation : pdfTextLocationCollection) {
                var text = textLocation.getText().trim().replaceAll("\\s+", " ");

                var lineMatcher = DOT_PATTERN.matcher(line);
                var textMatcher = DOT_PATTERN.matcher(text);

                if (lineMatcher.find() && textMatcher.find() && (line.contains(text) || text.contains(line)) && !lineMatcher.group(1).equals(textMatcher.group(1))) {
                    LOGGER.trace("Line [{}] has identical text to [{}] but different number of dots - checking next textLocation", line, text);
                    continue;
                }

                if (text.equals(line)) {
                    matchedLocation = textLocation.getRectangle();
                    break;
                } else if (text.startsWith(line)) {
                    LOGGER.trace("Text [{}] matched start of line - limiting width and praying", line);
                    matchedLocation = makePrefixRect(page, textLocation);
                    break;
                } else if (text.endsWith(line)) {
                    LOGGER.trace("Text [{}] matched end of line - limiting width and y and praying", line);
                    matchedLocation = makePostFixRect(page, textLocation);
                    break;
                } else if (text.contains("-")) {
                    for (int idx = text.lastIndexOf("-"); idx != -1; idx = text.lastIndexOf("-", idx - 1)) {
                        String substring = text.substring(0, idx);
                        var nextLine = i + 1 < lines.length ? lines[i + 1] : "";
                        Rectangle nextLineLocation = null;

                        if (line.contains(substring)) {
                            LOGGER.trace("Line [{}] is hyphenated in PDF - matched against of [{}]", line, text);
                            var lineAfterHyphenation = line.replace(substring, "");
                            var postHyphenOptional = pdfTextLocationCollection.stream()
                                    .filter(l -> {
                                        var innerText = l.getText().trim().replaceAll("\\s+", " ");
                                        return innerText.startsWith(lineAfterHyphenation) || innerText.endsWith(lineAfterHyphenation);
                                    })
                                    .findFirst();
                            if (postHyphenOptional.isPresent()) {
                                IPdfTextLocation postHyphen = postHyphenOptional.get();
                                var postHyphenText = postHyphen.getText().trim().replaceAll("\\s+", " ");
                                if (postHyphenText.startsWith(lineAfterHyphenation) && postHyphenText.endsWith(nextLine)) {
                                    LOGGER.trace("Post-hyphen text [{}] starts with [{}] and ends with [{}] - that must be a full match, using full rectangle", postHyphenText, lineAfterHyphenation, nextLine);
                                    nextLineLocation = postHyphen.getRectangle();
                                } else if (postHyphenText.startsWith(lineAfterHyphenation)) {
                                    LOGGER.trace("Text [{}] matched start of line - limiting width and praying", lineAfterHyphenation);
                                    nextLineLocation = makePrefixRect(page, postHyphen);
                                } else {
                                    LOGGER.trace("Text [{}] matched end of line - limiting width and y and praying", lineAfterHyphenation);
                                    nextLineLocation = makePostFixRect(page, postHyphen);
                                }
                                if (postHyphenText.contains(nextLine)) {
                                    LOGGER.debug("Text [{}] contains next line [{}] - skipping", postHyphenText, nextLine);
                                    skipNext = true;
                                }

                                // Going to ignore hyphenated text for now if there's no matching line found - otherwise it gets really wonky
                                if (idx == text.length() - 1) {
                                    rv.add(textLocation.getRectangle());
                                    rv.add(nextLineLocation);
                                    continue nextline;
                                } else if (line.startsWith(substring)) {
                                    rv.add(makePrefixRect(page, textLocation));
                                    rv.add(nextLineLocation);
                                    continue nextline;
                                } else if (line.endsWith(substring)) {
                                    rv.add(makePostFixRect(page, textLocation));
                                    rv.add(nextLineLocation);
                                    continue nextline;
                                }
                            } else {
                                LOGGER.error("Unable to find second part of hyphenated line [{}]-[{}]", substring, lineAfterHyphenation);
                            }
                        }
                    }
                }
            }

            if (matchedLocation == null) {
                LOGGER.error("Unable to match line [{}]", line);
            } else {
                rv.add(matchedLocation);
            }
        }

        return rv;
    }

    private static Rectangle makePostFixRect(int page, IPdfTextLocation textLocation) {
        float x;
        if (page % 2 == 0) {
            x = 322;
        } else {
            x = 304;
        }
        return new Rectangle(
                x,
                textLocation.getRectangle().getY(),
                247.0f,
                textLocation.getRectangle().getHeight()
        );
    }

    private static Rectangle makePrefixRect(int page, IPdfTextLocation textLocation) {
        float x = textLocation.getRectangle().getX();
        float width;
        if (page % 2 == 0) {
            width = 308 - x;
        } else {
            width = 289 - x;
        }
        return new Rectangle(
                x,
                textLocation.getRectangle().getY(),
                width,
                textLocation.getRectangle().getHeight()
        );
    }

    //•• Moderate. You’re thoroughly middle-class in income,
    //and can afford the odd indulgence. You can
    //hire specific help as necessary. You have enough
    //available cash, portable property, and valuables
    //that you can maintain a one-dot standard of living
    //wherever you are for up to six months.

    public record RawBook(List<Entry> backgrounds) {}

    public record Entry(String name, List<Paragraph> paragraphs) {}

    public record Paragraph(String type, int page, String content, List<Area> areas) {}
    public record Area(int page, String content) {}
}
