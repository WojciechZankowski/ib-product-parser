package pl.zankowski.ibpp.cli;

import org.junit.Test;
import pl.zankowski.ibpp.cli.CommandLineParser;
import pl.zankowski.ibpp.cli.CommandLineProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static pl.zankowski.ibpp.cli.CommandLineParser.ALL_ASIA_EXCHANGES;
import static pl.zankowski.ibpp.cli.CommandLineParser.ALL_EU_EXCHANGES;
import static pl.zankowski.ibpp.cli.CommandLineParser.ALL_EXCHANGES;
import static pl.zankowski.ibpp.cli.CommandLineParser.ALL_NA_EXCHANGES;
import static pl.zankowski.ibpp.cli.CommandLineParser.EXCHANGE_PARAM;
import static pl.zankowski.ibpp.cli.CommandLineParser.FORMATTER_PARAM;
import static pl.zankowski.ibpp.cli.CommandLineParser.SEC_TYPE_PARAM;

/**
 * @author Wojciech Zankowski
 */
public class CommandLineParserTest {

    private final CommandLineParser commandLineParser = new CommandLineParser();

    @Test
    public void shouldSuccessfullyReadSecTypeParameter() {
        final String secType = "STK";
        final String[] inputArgs = new String[]{SEC_TYPE_PARAM, secType};

        CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(inputArgs);
        assertEquals(secType, commandLineProperties.getSecType());
    }

    @Test
    public void shouldSuccessfullyReadFormatterParameter() {
        final String formatterName = "SimpleFormatter";
        final String[] inputArgs = new String[]{FORMATTER_PARAM, formatterName};

        CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(inputArgs);
        assertEquals(formatterName, commandLineProperties.getOutputFormatterName());
    }

    @Test
    public void shouldSuccessfullyReadSingleExchange() {
        final String exchangeName = "nyse";
        final String[] inputArgs = new String[]{EXCHANGE_PARAM, exchangeName};

        CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(inputArgs);
        assertThat(commandLineProperties.getExchanges()).hasSize(1);
        assertThat(commandLineProperties.getExchanges().get(0)).isEqualTo(exchangeName);
    }

    @Test
    public void shouldSuccessfullyReadAllNAExchangesPreset() {
        final String[] inputArgs = new String[]{EXCHANGE_PARAM, ALL_NA_EXCHANGES};

        CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(inputArgs);
        assertThat(commandLineProperties.isAllNA()).isTrue();
    }

    @Test
    public void shouldSuccessfullyReadAllEUExchangesPreset() {
        final String[] inputArgs = new String[]{EXCHANGE_PARAM, ALL_EU_EXCHANGES};

        CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(inputArgs);
        assertThat(commandLineProperties.isAllEU()).isTrue();
    }

    @Test
    public void shouldSuccessfullyReadAllAsiaExchangesPreset() {
        final String[] inputArgs = new String[]{EXCHANGE_PARAM, ALL_ASIA_EXCHANGES};

        CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(inputArgs);
        assertThat(commandLineProperties.isAllAsia()).isTrue();
    }

    @Test
    public void shouldSuccessfullyReadAllExchangesPreset() {
        final String[] inputArgs = new String[]{EXCHANGE_PARAM, ALL_EXCHANGES};

        CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(inputArgs);
        assertThat(commandLineProperties.isAll()).isTrue();
    }

    @Test
    public void shouldSuccessfullyReadMultipleExchanges() {
        final String firstExchange = "nyse";
        final String secondExchange = "nasdaq";
        final String[] inputArgs = new String[]{EXCHANGE_PARAM, firstExchange, secondExchange};

        CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(inputArgs);
        assertThat(commandLineProperties.getExchanges()).hasSize(2);
        assertThat(commandLineProperties.getExchanges().get(0)).isEqualTo(firstExchange);
        assertThat(commandLineProperties.getExchanges().get(1)).isEqualTo(secondExchange);
    }

    @Test
    public void shouldSuccessfullyReadSecTypeBeforeExchange() {
        final String secType = "STK";
        final String exchange = "cme";
        final String[] inputArgs = new String[]{
                SEC_TYPE_PARAM, secType,
                EXCHANGE_PARAM, exchange
        };

        CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(inputArgs);
        assertThat(commandLineProperties.getSecType()).isEqualTo(secType);
        assertThat(commandLineProperties.getExchanges().get(0)).isEqualTo(exchange);
    }

    @Test
    public void shouldSuccessfullyReadAllParameters() {
        final String secType = "STK";
        final String outputFormatter = "SimpleFormatter";
        final String exchangeName = "nasdaq";
        final String[] inputArgs = new String[]{
                SEC_TYPE_PARAM, secType,
                EXCHANGE_PARAM, ALL_EXCHANGES, ALL_EU_EXCHANGES, ALL_ASIA_EXCHANGES, ALL_NA_EXCHANGES, exchangeName,
                FORMATTER_PARAM, outputFormatter
        };

        CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(inputArgs);
        assertThat(commandLineProperties.getSecType()).isEqualTo(secType);
        assertThat(commandLineProperties.getOutputFormatterName()).isEqualTo(outputFormatter);
        assertThat(commandLineProperties.getExchanges()).hasSize(1);
        assertThat(commandLineProperties.getExchanges().get(0)).isEqualTo(exchangeName);
        assertThat(commandLineProperties.isAll()).isTrue();
        assertThat(commandLineProperties.isAllNA()).isTrue();
        assertThat(commandLineProperties.isAllAsia()).isTrue();
        assertThat(commandLineProperties.isAllEU()).isTrue();
    }

}
