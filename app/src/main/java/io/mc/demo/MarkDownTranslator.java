package io.mc.demo;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;


/**
 * Convert Markdown text to HTML format.
 * <p> Converts headings, paragraphs and links.
 */
public final class MarkDownTranslator {

    private static final char HASH = '#';
    private static final List<Integer> ALLOWED_HEADER_LEVEL = ImmutableList.of(1,2,3,4,5,6);

    private final LinkParagraphExtractor linkTranslator;

    public MarkDownTranslator() {

        this.linkTranslator = new LinkParagraphExtractor();
    }

    /**
     * Translates Markdown text into HTML format.
     * <p>Converts headers, paragraphs, and links.
     *
     * @param markdownText the Markdown text to be converted.
     * @return the converted HTML text as {@link String}
     */
    public String translateToHTMLFormat(String markdownText) {

        requireNonNull(markdownText, "markdownText must not be null");
        checkArgument(StringUtils.isNotBlank(markdownText), "markdownText must not be blank");

        List<String> htmlLines = new ArrayList<>();

        for (String line : markdownText.split("\n")) {

            String strippedLine = line.strip();
            if (strippedLine.isEmpty()) {
                continue;
            }

          int headingLevel = getHeadingLevel(strippedLine);
          if (ALLOWED_HEADER_LEVEL.contains(headingLevel)) {

                String content = strippedLine.substring(headingLevel).strip();
                content = linkTranslator.extractLinkAndParagraph(content);
                htmlLines.add("<h" + headingLevel + ">" + content + "</h" + headingLevel + ">");
            } else {
                String content = linkTranslator.extractLinkAndParagraph(strippedLine);
                htmlLines.add("<p>" + content + "</p>");
            }
        }
        return String.join("\n", htmlLines);
    }

    /**
     * Determines the heading level of a given Markdown.
     *
     * @param line text to analyze for a heading level.
     * @return the heading level as an integer.
     */
    private int getHeadingLevel(String line) {

        int headingLevel = 0;
        while (headingLevel < line.length() && line.charAt(headingLevel) == HASH) {
            headingLevel++;
        }

        return headingLevel < line.length() &&
                Character.isWhitespace(line.charAt(headingLevel)) ? headingLevel : 0;
    }
}