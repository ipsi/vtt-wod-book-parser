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
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    // All non-word characters *except* the hyphen...
    private static final Pattern STRIP_PUNCTUATION_PATTERN = Pattern.compile("[(),.!@#$%^&*\\[\\]{}:;'\"/?<>_=+]*(\\w+)[(),.!@#$%^&*\\[\\]{}:;'\"/?<>_=+]*.*?");
    private static final Pattern SPACE_STRIPPER = Pattern.compile("(\\W)?([TVWY]) (\\w+)");

    public static final Collector<CharSequence, StringBuilder, String> CONTENT_AWARE_JOINER = Collector.of(
            StringBuilder::new,
            (stringBuilder, charSequence) -> {
                if(stringBuilder.isEmpty()) {
                    stringBuilder.append(charSequence);
                    return;
                }

                if (charSequence.isEmpty()) {
                    return;
                }

                char lastChar = stringBuilder.charAt(stringBuilder.length() - 1);
                char leadingChar = charSequence.charAt(0);

                if (((lastChar >= '!' && lastChar < '/') && lastChar != '(' && lastChar != '-')
                            || (lastChar >= ':' && lastChar <= '@')
                            || (lastChar >= '\\' && lastChar <= '^')
                            || (lastChar >= '{' && lastChar <= '~')
                ) {
                    stringBuilder.append(" ");
                } else if (leadingChar == '['
                        || leadingChar == '('
                        || leadingChar == '{'
                        || (leadingChar >= 'A' && leadingChar <= 'Z')
                        || (leadingChar >= 'a' && leadingChar <= 'z')
                        || (leadingChar >= '0' && leadingChar <= '9')
                ) {
                    stringBuilder.append(" ");
                }

                stringBuilder.append(charSequence);
            },
            (sb1, sb2) -> {
                sb1.append(sb2);
                return sb1;
            },
            StringBuilder::toString
    );

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
            paragraphs.add(fixText(Arrays.stream(location.locations())
                    .map(l -> parser.processContent(l.page(), new FilteredTextEventListener(new SimpleTextExtractionStrategy(), new TextRegionEventFilter(l.location()))).getResultantText())
                    .map(s -> s.trim().replaceAll("[\n\r]", ""))
                    .collect(CONTENT_AWARE_JOINER)));
        }
        return paragraphs;
    }

    public static String getText(PdfDocumentContentParser parser, TextArea... textLocations) {
        return fixText(Arrays.stream(textLocations)
                .map(l -> parser.processContent(l.page(), new FilteredTextEventListener(new SimpleTextExtractionStrategy(), new TextRegionEventFilter(l.location()))).getResultantText())
                .map(s -> s.trim().replaceAll("[\n\r]", ""))
                .collect(CONTENT_AWARE_JOINER));
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
                        .split("[\n\r]+")).toList()
                );
            }
        }
        return lines;
    }

    public static List<String> getTextAsLines(PdfDocumentContentParser parser, TextArea... locations) {
        List<String> lines = new ArrayList<>(locations.length*2);
        for (TextArea textArea : locations) {
            lines.addAll(Arrays.stream(parser
                    .processContent(textArea.page(), new FilteredTextEventListener(
                            new SimpleTextExtractionStrategy(),
                            new TextRegionEventFilter(textArea.location()))
                    ).getResultantText()
                    .trim()
                    .split("[\n\r]+")).toList()
            );
        }
        Arrays.stream(locations)
                .map(l -> parser.processContent(l.page(), new FilteredTextEventListener(new SimpleTextExtractionStrategy(), new TextRegionEventFilter(l.location()))).getResultantText())
                .map(s -> s.trim().replaceAll("[\n\r]+", ""))
                .collect(CONTENT_AWARE_JOINER);
        return lines;
    }

    public static String fixText(String text) {
        String s = text
                .replaceAll("’ s", "'s")
                .replaceAll("’", "'")
                .replaceAll("“", "\"")
                .replaceAll("”", "\"")
                .replaceAll("\\s+,", ",")
                .replaceAll(" \\. | \\.$", ". ")
                .replaceAll("(\\w)\\.(\\w)", "$1. $2")
                .replaceAll("andyet", "and yet")
                .replaceAll("Likehomids", "Like homids")
                .replaceAll("justas", "just as")
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

                    if (!fullMatch) {
                        for (int idx = word.lastIndexOf("-"); idx >= 0; idx = word.substring(0, idx).lastIndexOf("-")) {
                            var leftPart = word.substring(0, idx);
                            var rightPart = word.substring(idx + 1);
                            var leftPartMatcher = STRIP_PUNCTUATION_PATTERN.matcher(leftPart);
                            var rightPartMatcher = STRIP_PUNCTUATION_PATTERN.matcher(rightPart);
                            if (words.contains(leftPartMatcher.replaceAll("$1").replace("-", "").trim().toLowerCase())
                                    && words.contains(rightPartMatcher.replaceAll("$1").replace("-", "").trim().toLowerCase())) {
                                return leftPart.replace("-", "") + "-" + rightPart.replace("-", "");
                            }
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

    public static Rectangle makeRectOddLeft(int top, int height) {
        return makeRect(42, top, 247, height);
    }

    public static Rectangle makeRectOddRight(int top, int height) {
        return makeRect(304, top, 247, height);
    }

    public static Rectangle makeRectEvenLeft(int top, int height) {
        return makeRect(61, top, 247, height);
    }

    public static Rectangle makeRectEvenRight(int top, int height) {
        return makeRect(322, top, 247, height);
    }
}
