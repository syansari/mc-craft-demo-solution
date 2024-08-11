package io.mc.demo;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Utility to handle file functions.
 */
public final class FileUtils {

    /**
     * Reads the contents of a Markdown file and returns it as a String.
     *
     * @param inputFileName the name of the input file containing Markdown text.
     * @return the contents of the Markdown file as a String.
     * @throws RuntimeException if the file cannot be read or does not exist.
     */
    public static String getMarkdownText(String inputFileName) {

        requireNonNull(inputFileName, "inputFileName cannot be null");
        checkArgument(StringUtils.isNotBlank(inputFileName), "inputFileName cannot be blank");

        try {
            return Files.readString(Path.of(inputFileName));

        } catch (IOException e) {
            throw new RuntimeException("Error: The file '" + inputFileName + "' was not found or could not be read.");
        }
    }

    /**
     * Writes HTML text to an output file.
     *
     * @param htmlText the HTML text to be written to the file.
     * @param outputFileName the name of the output file to write the HTML text to.
     * @throws NullPointerException if htmlText or outputFileName is null.
     * @throws RuntimeException if an error occurs while writing to the file.
     */
    public static void convertHTMLTextToFile(String htmlText, String outputFileName) {

        requireNonNull(htmlText, "htmlText cannot be null");
        requireNonNull(outputFileName, "outputFileName cannot be null");
        checkArgument(StringUtils.isNotBlank(htmlText), "htmlText cannot be blank");
        checkArgument(StringUtils.isNotBlank(outputFileName), "outputFileName cannot be blank");

        try {
            Files.writeString(Path.of(outputFileName), htmlText);
        } catch (IOException e) {

            throw new RuntimeException("Error: writing to the output file '" + outputFileName + "'");
        }
    }
}
