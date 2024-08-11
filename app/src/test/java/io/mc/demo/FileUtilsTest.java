package io.mc.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {

    private static final String TEST_HTML_CONTENT = "<h1>Test Header</h1>\n<p>This is a test paragraph.</p>";
    private static final String TEST_HTML_FILE = "output.html";
    private static final String TEST_MARKDOWN_CONTENT = "# Test Header\n\nThis is a test paragraph.";
    private static final String TEST_MARKDOWN_FILE = "input.md";

    @BeforeEach
    void setUp() throws IOException {
        Files.writeString(Path.of(TEST_MARKDOWN_FILE), TEST_MARKDOWN_CONTENT);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(TEST_MARKDOWN_FILE));
        Files.deleteIfExists(Path.of(TEST_HTML_FILE));
    }

    @Test
    void testGetMarkdownText_Success() {
        String content = FileUtils.getMarkdownText(TEST_MARKDOWN_FILE);
        assertEquals(TEST_MARKDOWN_CONTENT, content);
    }

    @Test
    void testGetMarkdownText_FileNotFound() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> FileUtils.getMarkdownText("non_existent.md"));
        assertEquals("Error: The file 'non_existent.md' was not found or could not be read.", exception.getMessage());
    }

    @Test
    void testGetMarkdownText_NullInput() {
        assertThrows(NullPointerException.class, () -> FileUtils.getMarkdownText(null));
    }

    @Test
    void testGetMarkdownText_BlankInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> FileUtils.getMarkdownText("   "));
        assertEquals("inputFileName cannot be blank", exception.getMessage());
    }

    @Test
    void testConvertHTMLTextToFile_Success() throws IOException {
        FileUtils.convertHTMLTextToFile(TEST_HTML_CONTENT, TEST_HTML_FILE);
        String content = Files.readString(Path.of(TEST_HTML_FILE));
        assertEquals(TEST_HTML_CONTENT, content);
    }

    @Test
    void testConvertHTMLTextToFile_NullHtmlText() {
        assertThrows(NullPointerException.class, () -> FileUtils.convertHTMLTextToFile(null, TEST_HTML_FILE));
    }

    @Test
    void testConvertHTMLTextToFile_NullOutputFileName() {
        assertThrows(NullPointerException.class, () -> FileUtils.convertHTMLTextToFile(TEST_HTML_CONTENT, null));
    }

    @Test
    void testConvertHTMLTextToFile_BlankHtmlText() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> FileUtils.convertHTMLTextToFile("   ", TEST_HTML_FILE));
        assertEquals("htmlText cannot be blank", exception.getMessage());
    }

    @Test
    void testConvertHTMLTextToFile_BlankOutputFileName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> FileUtils.convertHTMLTextToFile(TEST_HTML_CONTENT, "   "));
        assertEquals("outputFileName cannot be blank", exception.getMessage());
    }

    @Test
    void testConvertHTMLTextToFile_IOError() {
        String invalidPath = "/invalid_path/output.html";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> FileUtils.convertHTMLTextToFile(TEST_HTML_CONTENT, invalidPath));
        assertEquals("Error: writing to the output file '" + invalidPath + "'", exception.getMessage());
    }
}