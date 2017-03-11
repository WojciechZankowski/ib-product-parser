package pl.zankowski.ibpp.io;

import org.junit.Test;
import pl.zankowski.ibpp.io.FileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Wojciech Zankowski
 */
public class FileWriterTest {

    @Test
    public void shouldSuccessfullyWriteTwoLinesOfText() throws IOException {
        final File testFile = new File("./testFile.txt");
        testFile.deleteOnExit();
        FileWriter fileWriter = new FileWriter(testFile);

        final String firstLine = "first";
        final String secondLine = "second";

        fileWriter.write(firstLine);
        fileWriter.write(secondLine);
        fileWriter.close();

        assertFileContent(testFile, "first\r\nsecond");
    }

    private void assertFileContent(File testFile, String expectedContent) throws FileNotFoundException {
        String actualContent = new Scanner(testFile).useDelimiter("\\Z").next();
        assertThat(actualContent).isEqualTo(expectedContent);
    }

}
