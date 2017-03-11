package pl.zankowsk.ibpp.io.parser;

import org.junit.Test;
import pl.zankowski.ibpp.formatter.OutputFormatter;
import pl.zankowski.ibpp.formatter.SimpleFormatter;

import static org.junit.Assert.assertEquals;

/**
 * @author Wojciech Zankowski
 */
public class ParserTest {

	@Test
	public void shouldSuccessfullyCreateObjectFromStringLine() {
		OutputFormatter parser = new SimpleFormatter();

		String expectedOutput = ";;;;";
		String actualOutput = parser.parse("", "", "", "", "", "");
		assertEquals(expectedOutput, actualOutput);

		expectedOutput = "NYSE;GOOG;GOOGLE;STK;USD;US42313AS23";
		actualOutput = parser.parse("GOOG", "GOOGLE", "USD", "NYSE", "STK", "US42313AS23");
		assertEquals(expectedOutput, actualOutput);
	}

}
