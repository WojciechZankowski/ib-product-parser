package pl.zankowski.ibpp.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * @author Wojciech Zankowski
 */
public class FileWriter {

    private final File file;
    private BufferedWriter writer;

    public FileWriter(File file) throws IOException {
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

    public void write(String line) throws IOException {
        writer.write(line);
        writer.newLine();
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
