package name.ipsi.project.fwbp.books;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import com.itextpdf.kernel.pdf.canvas.parser.filter.TextRegionEventFilter;
import com.itextpdf.kernel.pdf.canvas.parser.listener.FilteredTextEventListener;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;
import name.ipsi.project.fwbp.books.shared.locations.Content;
import name.ipsi.project.fwbp.books.shared.locations.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {

    // All non-word characters *except* the hyphen...
    private static final Logger log = LoggerFactory.getLogger(Utils.class);
    private static final Pattern STRIP_PUNCTUATION_PATTERN = Pattern.compile("[(),.!@#$%^&*\\[\\]{}:;'\"/?<>_=+]*(\\w+)[(),.!@#$%^&*\\[\\]{}:;'\"/?<>_=+]*.*?");
    private static final Pattern SPACE_STRIPPER = Pattern.compile("(\\W)?([TVWY]) (\\w+)");

    private static final Set<String> words;

    static {
        words = new HashSet<>(1_000_000);
        InputStream inputStream = Utils.class.getResourceAsStream("/words.txt");
        if (inputStream == null) {
            throw new RuntimeException("Unable to find /words.txt in classpath");
        }
        try (var br = new BufferedReader(new InputStreamReader(inputStream))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                words.add(line.toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.debug("Loaded {} words for de-hyphenation checking", words.size());
    }

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
                lines.addAll(Arrays.stream(parser
                        .processContent(textArea.page(), new FilteredTextEventListener(
                                new SimpleTextExtractionStrategy(),
                                new TextRegionEventFilter(textArea.location()))
                        ).getResultantText()
                        .trim()
                        .split("[\n\r]+")).map(Utils::fixText).toList()
                );
            }
        }
        return lines;
    }

    public static String fixText(String text) {
        String s = text
                .replaceAll("’ s", "'s")
                .replaceAll("’", "'")
                .replaceAll("“", "\"")
                .replaceAll("”", "\"")
                .replaceAll(" \\. | \\.$", ". ")
                .replaceAll("(\\w)\\.(\\w)", "$1. $2")
                .replaceAll("andyet", "and yet")
                .replaceAll("Likehomids", "Like homids")
                .replaceAll("thought,and", "thought, and")
                .replaceAll("(\\w+)- (\\w+)", "$1-$2")
                .replaceAll("hareor", "hare or")
                .replaceAll("roadrunneror", "roadrunner- or")
        ;

        var spaceStripperMatcher = SPACE_STRIPPER.matcher(s);
        if (spaceStripperMatcher.groupCount() == 3) {
            s = spaceStripperMatcher.replaceAll("$1$2$3");
        } else if (spaceStripperMatcher.groupCount() == 2) {
            s = spaceStripperMatcher.replaceAll("$1$2");
        }

        s = Arrays.stream(s.split(" ")).map(word -> {
            var matcher = STRIP_PUNCTUATION_PATTERN.matcher(word);
            if (word.contains("-") && !word.endsWith("-")) {
                if (words.contains(matcher.replaceAll("$1").trim().toLowerCase())) {
                    // Easy match - exact word found after stripping punctuation
                    return word;
                } else if(matcher.matches() && words.contains(matcher.replaceAll("$1").trim().toLowerCase().replaceAll("-", ""))) {
                    // Easy match - exact word found after stripping punctuation and hyphens
                    return word.replaceAll("-", "");
                } else {
                    var fullMatch = true;
                    for (var part : word.split("-")) {
                        var partMatcher = STRIP_PUNCTUATION_PATTERN.matcher(part);
                        if (partMatcher.matches() && !words.contains(partMatcher.replaceAll("$1").trim().toLowerCase())) {
                            fullMatch = false;
                            break;
                        }
                    }

                    if (fullMatch) {
                        // All parts of the word match, so we're good to return as-is
                        return word;
                    } else {
                        log.warn("Found partial match for word [{}] - add unknown words to list", word);

                        // No part of the word matched, so remove all the hyphens and pray
                        return word.replaceAll("-", "");
                    }
                }
            } else {
                return word;
            }
        }).collect(Collectors.joining(" "));

        return s;
    }

    public static Rectangle makeRect(int left, int top, int width, int height) {
        return new Rectangle(left, (792 - top) - height, (float) width, (float) height);
    }
}
