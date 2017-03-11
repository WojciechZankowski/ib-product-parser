package pl.zankowski.ibpp.io;

import pl.zankowski.ibpp.formatter.OutputFormatter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * @author Wojciech Zankowski
 */
public class FileWriter {

    private final OutputFormatter outputParser;
    private final File file;
    private BufferedWriter writer;

    public FileWriter(File file, OutputFormatter outputParser) throws IOException {
        this.outputParser = outputParser;
        this.file = file;
        initWriter(file);
    }

    private void initWriter(File file) throws IOException {
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }

        writer = new BufferedWriter(new java.io.FileWriter(file.getAbsoluteFile()));
    }

    public void write(String symbol, String description, String currency, String exchange, String secType, String ISIN) throws IOException {
        writer.write(outputParser.parse(symbol, description, currency, exchange, secType, ISIN) + "\n");
    }

    public void close() {
        System.out.println("Saved " + file.getName() + " file.");
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
