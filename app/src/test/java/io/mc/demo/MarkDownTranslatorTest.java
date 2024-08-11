package io.mc.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkDownTranslatorTest {

    private MarkDownTranslator subject;

    @BeforeEach
    void setUp() {
        subject = new MarkDownTranslator();
    }

    @Test
    void testTranslateToHTMLFormat_NullInput() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> subject.translateToHTMLFormat(null));
        assertEquals("markdownText must not be null", exception.getMessage());
    }

    @Test
    void testTranslateToHTMLFormat_BlankInput() {
        String input = "   ";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.translateToHTMLFormat(input));
        assertEquals("markdownText must not be blank", exception.getMessage());
    }

    @Test
    void testTranslateToHTMLFormat_HeaderLevel1() {
        String input = "# Header 1";
        String expected = "<h1>Header 1</h1>";
        assertEquals(expected, subject.translateToHTMLFormat(input));
    }

    @Test
    void testTranslateToHTMLFormat_HeaderLevel2() {
        String input = "## Header 2";
        String expected = "<h2>Header 2</h2>";
        assertEquals(expected, subject.translateToHTMLFormat(input));
    }

    @Test
    void testTranslateToHTMLFormat_HeaderLevel6() {
        String input = "###### Header 6";
        String expected = "<h6>Header 6</h6>";
        assertEquals(expected, subject.translateToHTMLFormat(input));
    }

    @Test
    void testTranslateToHTMLFormat_InvalidHeaderLevel() {
        String input = "### Header 3\n#### Header 4\n##### Header 5";
        String expected = "<h3>Header 3</h3>\n<h4>Header 4</h4>\n<h5>Header 5</h5>";
        assertEquals(expected, subject.translateToHTMLFormat(input));
    }

    @Test
    void testTranslateToHTMLFormat_Paragraph() {
        String input = "This is a paragraph.";
        String expected = "<p>This is a paragraph.</p>";
        assertEquals(expected, subject.translateToHTMLFormat(input));
    }

    @Test
    void testTranslateToHTMLFormat_LinkTranslation() {
        String input = "This is a [link](http://intuit.com).";
        String expected = "<p>This is a <a href=\"http://intuit.com\">link</a>.</p>";
        assertEquals(expected, subject.translateToHTMLFormat(input));
    }

    @Test
    void testTranslateToHTMLFormat_MixedContent_01() {
        String input = "# Header 1\n\nThis is a paragraph.\n\n## Header 2";
        String expected = "<h1>Header 1</h1>\n<p>This is a paragraph.</p>\n<h2>Header 2</h2>";
        assertEquals(expected, subject.translateToHTMLFormat(input));
    }

    @Test
    void testTranslateToHTMLFormat_MixedContent_02() {
        String input = "# Header 1\n\nThis is a paragraph.\n\n## Header 2 ### Header 3";
        String expected = "<h1>Header 1</h1>\n<p>This is a paragraph.</p>\n<h2>Header 2 ### Header 3</h2>";
        assertEquals(expected, subject.translateToHTMLFormat(input));
    }

    @Test
    void testTranslateToHTMLFormat_NoSpace_after_hash1() {
        String input = "#Header 1\n\nThis is a paragraph";
        String expected = "<p>#Header 1</p>\n<p>This is a paragraph</p>";
        assertEquals(expected, subject.translateToHTMLFormat(input));
    }
}