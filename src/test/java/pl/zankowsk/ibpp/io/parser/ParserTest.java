package pl.zankowsk.ibpp.io.parser;

import org.junit.Test;
import pl.zankowski.ibpp.data.IBProduct;
import pl.zankowski.ibpp.io.parser.OutputParser;
import pl.zankowski.ibpp.io.parser.SimpleParser;

import static org.junit.Assert.assertEquals;

/**
 * @author Wojciech Zankowski
 */
public class ParserTest {

    @Test
    public void testSimpleParser() {
        OutputParser parser = new SimpleParser();

        String expectedOutput = ";;;;";
        String actualOutput = parser.parse(new IBProduct.IBProductBuilder().build(), "", "");
        assertEquals(expectedOutput, actualOutput);

        expectedOutput = "NYSE;GOOG;GOOGLE;STK;USD";
        actualOutput = parser.parse(new IBProduct.IBProductBuilder()
                                        .symbol("GOOG")
                                        .description("GOOGLE")
                                        .currency("USD")
                                        .build(), "NYSE", "STK");
        assertEquals(expectedOutput, actualOutput);
    }

}
