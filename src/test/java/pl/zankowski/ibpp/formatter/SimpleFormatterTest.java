package pl.zankowski.ibpp.formatter;

import org.junit.Test;
import pl.zankowski.ibpp.formatter.SimpleFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @author Wojciech Zankowski
 */
public class SimpleFormatterTest {

    private final SimpleFormatter simpleFormatter = new SimpleFormatter();

    @Test
    public void shouldSuccessffullyFormatEmptyOutput() {
        String expectedOutput = ";;;;";
        String actualOutput = simpleFormatter.parse("", "", "", "", "", "");
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    public void shouldSuccessfullyFormatOutput() {
        final String expectedOutput = "IBM;IBMDesc;USD;NYSE;STK;ISB123";
        final String actualOutput = simpleFormatter.parse("IBM", "IBMDesc", "USD", "NYSE", "STK", "ISB123");
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

}
