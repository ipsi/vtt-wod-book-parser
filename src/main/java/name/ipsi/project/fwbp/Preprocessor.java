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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

public class Preprocessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Preprocessor.class);

    private static final Pattern DOT_PATTERN = Pattern.compile("^(•+)");

    private final Map<Integer, Collection<IPdfTextLocation>> stringLocations;
    private final Path outputDir;
    private final StringBuilder output;

    private Preprocessor(Map<Integer, Collection<IPdfTextLocation>> stringLocations, Path outputDir) {
        this.stringLocations = stringLocations;
        this.outputDir = outputDir;
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

        var p = new Preprocessor(stringLocations, jsonFile.getParent());
        p.doProcessing(entries);
//        System.out.println(p.output);
    }

    private void doProcessing(RawBook entries) {
        processBackgrounds(entries);

        try {
            Files.writeString(outputDir.resolve("Backgrounds.java"), output.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        processRites(entries);
    }

    private void processBackgrounds(RawBook entries) {
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

    private void processRites(RawBook entries) {
        output.setLength(0);
        output.append("package name.ipsi.project.fwbp.books.werewolf.locations;\n" +
                "\n" +
                "import com.itextpdf.kernel.geom.Rectangle;\n" +
                "import name.ipsi.project.fwbp.books.shared.locations.TextArea;\n" +
                "import name.ipsi.project.fwbp.books.shared.locations.TextLocation;\n" +
                "import name.ipsi.project.fwbp.books.shared.locations.TextLocationType;\n" +
                "import name.ipsi.project.fwbp.books.werewolf.RiteLevel;\n" +
                "import name.ipsi.project.fwbp.books.werewolf.RiteType;\n" +
                "\n" +
                "import java.util.Arrays;\n" +
                "import java.util.List;\n\n");
        output.append("public class RiteLocations {\n")
                .append("    public static final List<RiteLocation> RITES = Arrays.asList(\n");

        for (var rite : entries.rites().accord()) {
            processRite(rite, "ACCORD");
        }

        for (var rite : entries.rites().caern()) {
            processRite(rite, "CAERN");
        }

        for (var rite : entries.rites().death()) {
            processRite(rite, "DEATH");
        }

        for (var rite : entries.rites().mystic()) {
            processRite(rite, "MYSTIC");
        }

        for (var rite : entries.rites().punishment()) {
            processRite(rite, "PUNISHMENT");
        }

        for (var rite : entries.rites().renown()) {
            processRite(rite, "RENOWN");
        }

        for (var rite : entries.rites().seasonal()) {
            processRite(rite, "SEASONAL");
        }

        for (var rite : entries.rites().minor()) {
            processRite(rite, "MINOR");
        }
        // remove trailing comma
        output.deleteCharAt(output.length() - 2);
        output.append("    );\n");
        output.append("};\n");

        try {
            Files.writeString(outputDir.resolve("RiteLocations.java"), output.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processRite(Entry rite, String type) {
        String level = rite.level();
        output.append("                        new RiteLocation(\n")
                .append("                                \"").append(rite.name()).append("\",\n")
                .append("                                RiteType.").append(type).append(",\n")
                .append("                                RiteLevel.").append(level == null ? "MINOR" : level.toUpperCase()).append(",\n");
        for (var paragraph : rite.paragraphs()) {
            output.append("                                new TextLocation(\n")
                    .append("                                        TextLocationType.")
                    .append(paragraph.type().toUpperCase())
                    .append(",\n");
            if (paragraph.areas() != null && !paragraph.areas().isEmpty()) {
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
        // remove trailing comma
        output.append("                        ),\n");
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
        var skipNext = 0;
        String[] lines = content.split("\n");
        nextline:
        for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
            var line = lines[lineNumber];
            if (skipNext > 0) {
                skipNext -= 1;
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
                    LOGGER.trace("Text [{}] starts with line [{}] - limiting width and praying", text, line);
                    matchedLocation = makePrefixRect(page, textLocation);
                    break;
                } else if (text.endsWith(line)) {
                    var charCount = text.indexOf(line);
                    if (charCount <= 9){
                        LOGGER.trace("Text [{}] ends with line [{}] but only [{}] chars unmatched - assuming bad match and skipping", text, line, charCount);
                        continue;
                    }

                    LOGGER.trace("Text [{}] ends with line [{}] with [{}] chars unmatched - limiting width and y and praying", text, line, charCount);
                    matchedLocation = makePostFixRect(page, textLocation);
                    break;
                } else if(line.replaceAll("th ", " ").equals(text)) {
                    LOGGER.trace("Text [{}] matched line [{}] after stripping [th] - hooray superscript...", text, line);
                    matchedLocation = textLocation.getRectangle();
                } else if (text.contains("-")) {
                    HyphenationResult hyphenationResult = processHyphenatedText(
                            page,
                            lineNumber,
                            lines,
                            line,
                            text,
                            textLocation,
                            pdfTextLocationCollection
                    );

                    if (hyphenationResult != null) {
                        rv.add(hyphenationResult.location());
                        rv.addAll(hyphenationResult.nextLineLocations());
                        skipNext = hyphenationResult.nextLineLocations().size();
                        continue nextline;
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

    private HyphenationResult processHyphenatedText(
            int page,
            int lineNumber,
            String[] lines,
            String line,
            String text,
            IPdfTextLocation textLocation,
            Collection<IPdfTextLocation> pdfTextLocationCollection
    ) {
        var nextLine = lineNumber + 1 < lines.length ? lines[lineNumber + 1] : "";
        for (int idx = text.lastIndexOf("-"); idx != -1; idx = text.lastIndexOf("-", idx - 1)) {
            String substring = text.substring(0, idx);

            if (line.contains(substring) || (line.lastIndexOf(" ") >= 0 && substring.contains(line.substring(0, line.lastIndexOf(" "))))) {
                LOGGER.trace("Line [{}] is hyphenated in PDF - matched against of [{}]", line, text);
                String lineAfterHyphenation;
                if (line.contains(substring)) {
                    lineAfterHyphenation = line.replace(substring, "");
                } else {
                    lineAfterHyphenation = line.replace(substring.substring(substring.indexOf(line.substring(0, line.lastIndexOf(" ")))), "");
                }

                var postHyphenTextLocations = pdfTextLocationCollection.stream()
                        .filter(l -> {
                            var innerText = l.getText().trim().replaceAll("\\s+", " ");
                            return innerText.startsWith(lineAfterHyphenation) || innerText.endsWith(lineAfterHyphenation) || innerText.endsWith(lineAfterHyphenation + " " + nextLine);
                        })
                        .toList();
                if (!postHyphenTextLocations.isEmpty()) {
                    for (var postHyphen : postHyphenTextLocations) {
                        var postHyphenText = postHyphen.getText().trim().replaceAll("\\s+", " ");

                        Rectangle nextLineLocation;
                        if (postHyphenText.contains(nextLine)) {
                            LOGGER.debug("Text [{}] contains next line [{}] - skipping", postHyphenText, nextLine);
                            if (postHyphenText.startsWith(lineAfterHyphenation)) {
                                if (postHyphenText.endsWith(nextLine)) {
                                    LOGGER.debug("Post-hyphen text [{}] starts with [{}] and ends with [{}] - that must be a full match, using full rectangle", postHyphenText, lineAfterHyphenation, nextLine);
                                    nextLineLocation = postHyphen.getRectangle();
                                } else {
                                    LOGGER.debug("Text [{}] matched start of line - limiting width and praying", lineAfterHyphenation);
                                    nextLineLocation = makePrefixRect(page, postHyphen);
                                }
                            } else {
                                LOGGER.debug("Text [{}] matched end of line - limiting width and y and praying", lineAfterHyphenation);
                                nextLineLocation = makePostFixRect(page, postHyphen);
                            }
                        } else {
                            LOGGER.trace("Text [{}] does not match line [{}] - ignoring", text, line);
                            continue;
                        }

                        // Going to ignore hyphenated text for now if there's no matching line found - otherwise it gets really wonky
                        var nextLineLocations = new ArrayList<Rectangle>();
                        nextLineLocations.add(nextLineLocation);
                        if (idx == text.length() - 1) {
                            return new HyphenationResult(
                                    textLocation.getRectangle(),
                                    nextLineLocations
                            );
                        } else if (line.startsWith(substring)) {
                            return new HyphenationResult(
                                    makePrefixRect(page, textLocation),
                                    nextLineLocations
                            );
                        } else if (line.endsWith(substring)) {
                            return new HyphenationResult(
                                    makePostFixRect(page, textLocation),
                                    nextLineLocations
                            );
                        }
                    }
                } else {
                    LOGGER.warn("Unable to find second part of hyphenated line [{}]-[{}] - might be false match", substring, lineAfterHyphenation);
                }
            }
        }
        return null;
    }

    private record HyphenationResult(Rectangle location, List<Rectangle> nextLineLocations) {}

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

    public record RawBook(List<Entry> backgrounds, Rites rites) {}

    public record Rites(
            List<Entry> accord,
            List<Entry> caern,
            List<Entry> death,
            List<Entry> mystic,
            List<Entry> punishment,
            List<Entry> renown,
            List<Entry> seasonal,
            List<Entry> minor
    ) {}

    public record Entry(String name, String level, List<Paragraph> paragraphs) {}

    public record Paragraph(String type, int page, String content, List<Area> areas) {}
    public record Area(int page, String content) {}
}
