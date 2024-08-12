package io.mc.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @TempDir
    Path tempDir;

    private String inputFileName;
    private String emptyInputFileName;
    private String outputFileName;

    @BeforeEach
    void setUp() throws IOException {
        inputFileName = Files.createTempFile(tempDir, "input", ".md").toString();
        String content = "# Markdown Title\n\nThis is some markdown content.";
        Files.writeString(Path.of(inputFileName), content);
        outputFileName = Files.createTempFile(tempDir, "output", ".html").toString();
        emptyInputFileName = Files.createTempFile(tempDir, "empty-input", ".md").toString();

    }

    @Test
    void testMainWithCorrectArguments() {
        Application.main(new String[]{inputFileName, outputFileName});
        assertTrue(Files.exists(Path.of(outputFileName)));
    }

    @Test
    void testMainWithIncorrectArguments() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Application.main(new String[]{"onlyOneArgument"}));
        assertEquals("Usage: java MarkdownApp <input_file.md> <output_file.html>",
                exception.getMessage());
    }


    @Test
    void testProcessMarkdownFileThrowsRuntimeException() {

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> Application.main(new String[]{emptyInputFileName, outputFileName}));
        assertTrue(exception.getMessage().contains("Error: unable to process the markdown file:"));
    }
}