package io.mc.demo;

import io.mc.demo.model.LinkInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Responsible for extracting link and convert to HTML anchor tag.
 */
public final class LinkParagraphExtractor {

    /**
     * Extracts link from the given text and converts to HTML anchor tag.
     * @param text input text
     * @return HTML anchor tags as {@link String}
     */
    public String extractLinkAndParagraph(String text) {

        requireNonNull(text, "text must not be null");
        checkArgument(StringUtils.isNotBlank(text), "text must not be blank");

        StringBuilder result = new StringBuilder();
        int currentIndex = 0;

        while (currentIndex < text.length()) {

            // potential link
            if (text.charAt(currentIndex) == '[') {

                Optional<LinkInfo> OptionalLinkInfo = extractLinkInfo(text, currentIndex);

                if (OptionalLinkInfo.isPresent()) {

                    LinkInfo linkInfo = OptionalLinkInfo.get();

                    result.append("<a href=\"").append(linkInfo.getUrl()).append("\">")
                            .append(linkInfo.getText()).append("</a>");
                    currentIndex = linkInfo.getEndIndex();
                    continue;
                }
            }
            // link not found, it's a character in a paragraph
            result.append(text.charAt(currentIndex));
            currentIndex++;
        }

        return result.toString();
    }

    /**
     * Extracts link data starting at a given index.
     * <p>It looks for a Markdown-style link [text](url).
     *
     * @param text the text to extract the link from.
     * @param startIndex the index to start looking for the link.
     * @return {@link LinkInfo} object
     */
    private Optional<LinkInfo> extractLinkInfo(String text, int startIndex) {

        int linkTextBegin = startIndex + 1;
        int linkTextEnd = text.indexOf(']', linkTextBegin);
        if (linkTextEnd != -1 && linkTextEnd + 1 < text.length() &&
                text.charAt(linkTextEnd + 1) == '(') {

            int linkUrlBegin = linkTextEnd + 2;
            int linkUrlEnd = text.indexOf(')', linkUrlBegin);
            if (linkUrlEnd != -1) {

                String linkText = text.substring(linkTextBegin, linkTextEnd);
                String linkUrl = text.substring(linkUrlBegin, linkUrlEnd);

                return Optional.of(LinkInfo.Builder.builder()
                        .text(linkText)
                        .url(linkUrl)
                        .endIndex(linkUrlEnd+1)
                        .build());
            }
        }
        return Optional.empty();
    }
}