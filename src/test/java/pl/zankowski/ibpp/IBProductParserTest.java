package pl.zankowski.ibpp;

import org.junit.Test;
import pl.zankowski.ibpp.IBProductParser;
import pl.zankowski.ibpp.formatter.OutputFormatter;
import pl.zankowski.ibpp.formatter.SimpleFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Wojciech Zankowski
 */
public class IBProductParserTest {

    private final IBProductParser ibProductParser = new IBProductParser();

    @Test
    public void shouldSuccessfullyCreateSimpleFormatterInstance() throws Exception {
        final String formatterName = "pl.zankowski.ibpp.formatter.SimpleFormatter";
        OutputFormatter outputFormatter = ibProductParser.initFormatterClass(formatterName);

        assertThat(outputFormatter).isExactlyInstanceOf(SimpleFormatter.class);
    }

}
