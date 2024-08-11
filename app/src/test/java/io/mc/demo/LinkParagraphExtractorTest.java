package io.mc.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkParagraphExtractorTest {

    private LinkParagraphExtractor subject;

    @BeforeEach
    void setUp() {
        subject = new LinkParagraphExtractor();
    }

    @Test
    void testExtractLinkAndParagraph_WithLink() {
        String input = "This is a [link](http://intuit.com).";
        String expected = "This is a <a href=\"http://intuit.com\">link</a>.";
        assertEquals(expected, subject.extractLinkAndParagraph(input));
    }

    @Test
    void testExtractLinkAndParagraph_WithMultipleLinks() {
        String input = "Visit [Facebook](http://facebook.com) or" +
                " [Instagram](http://instagram.com).";
        String expected = "Visit <a href=\"http://facebook.com\">Facebook</a> or" +
                " <a href=\"http://instagram.com\">Instagram</a>.";
        assertEquals(expected, subject.extractLinkAndParagraph(input));
    }

    @Test
    void testExtractLinkAndParagraph_WithoutLink() {
        String input = "This is a paragraph without a link.";
        String expected = "This is a paragraph without a link.";
        assertEquals(expected, subject.extractLinkAndParagraph(input));
    }

    @Test
    void testExtractLinkAndParagraph_WithMissingLinkSquareBracket() {
        String input = "This is a [link with no closing.";
        String expected = "This is a [link with no closing.";
        assertEquals(expected, subject.extractLinkAndParagraph(input));
    }

    @Test
    void testExtractLinkAndParagraph_WithMissingLinkRoundBracket() {
        String input = "This is a [link with no closing](http://intuit.com";
        String expected = "This is a [link with no closing](http://intuit.com";
        assertEquals(expected, subject.extractLinkAndParagraph(input));
    }

}