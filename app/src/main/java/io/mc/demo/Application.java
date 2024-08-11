package io.mc.demo;

/**
 * Entry point to the application
 */
public class Application {

    /**
     * The main method that serves as the entry point of the application.
     * <p>
     * It expects two command-line arguments: the input Markdown file and the output HTML file.
     *
     * @param args the command-line arguments; expects input and output file paths.
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: java MarkdownApp <input_file.md> <output_file.html>");
        }
        processMarkdownFile(args[0], args[1]);
    }

    /**
     * Processes the markdown file by reading it, converting it to HTML, and writing the output.
     *
     * @param inputFileName  the input markdown file
     * @param outputFileName the output HTML file
     */
    static void processMarkdownFile(String inputFileName, String outputFileName) {

        try {
            String markDownFormatText = FileUtils.getMarkdownText(inputFileName);
            MarkDownTranslator translator = new MarkDownTranslator();
            String htmlFormatText = translator.translateToHTMLFormat(markDownFormatText);
            FileUtils.convertHTMLTextToFile(htmlFormatText, outputFileName);
        } catch (Exception e) {
            throw new RuntimeException("Error: unable to process the markdown file: " + e);
        }
    }
}
