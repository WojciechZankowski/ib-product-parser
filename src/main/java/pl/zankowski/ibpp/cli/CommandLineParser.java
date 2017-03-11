package pl.zankowski.ibpp.cli;

/**
 * @author Wojciech Zankowski
 */
public class CommandLineParser {

    public static final String EXCHANGE_PARAM = "-e";
    public static final String SEC_TYPE_PARAM = "-t";
    public static final String FORMATTER_PARAM = "-f";

    public static final String ALL_EXCHANGES = "all";
    public static final String ALL_NA_EXCHANGES = "allna";
    public static final String ALL_EU_EXCHANGES = "alleu";
    public static final String ALL_ASIA_EXCHANGES = "allasia";

    public CommandLineProperties parseCommandLineProperties(String[] args) {
        CommandLineProperties properties = new CommandLineProperties();

        for (int i = 0; i < args.length; i++) {
            if (isExchangeParameter(args[i])) {
                while (++i < args.length) {
                    if (args[i].startsWith("-")) {
                        break;
                    }
                    parseExchange(properties, args[i]);
                }
            }
            if (isSecTypeParameter(args[i])) {
                if (++i < args.length) {
                    parseSecType(properties, args[i]);
                }
            }
            if (isFormatterParameter(args[i])) {
                if (++i < args.length) {
                    parseFormatter(properties, args[i]);
                }
            }
        }

        return properties;
    }

    private boolean isExchangeParameter(String value) {
        return value.equals(EXCHANGE_PARAM);
    }

    private void parseExchange(CommandLineProperties properties, String value) {
        if (value.equals(ALL_EXCHANGES)) {
            properties.setAll(true);
        } else if (value.equals(ALL_NA_EXCHANGES)) {
            properties.setAllNA(true);
        } else if (value.equals(ALL_EU_EXCHANGES)) {
            properties.setAllEU(true);
        } else if (value.equals(ALL_ASIA_EXCHANGES)) {
            properties.setAllAsia(true);
        } else {
            properties.addExchange(value);
        }
    }

    private boolean isSecTypeParameter(String value) {
        return value.equals(SEC_TYPE_PARAM);
    }

    private void parseSecType(CommandLineProperties properties, String value) {
        properties.setSecType(value);
    }

    private boolean isFormatterParameter(String value) {
        return value.equals(FORMATTER_PARAM);
    }

    private void parseFormatter(CommandLineProperties properties, String value) {
        properties.setOutputFormatterName(value);
    }

}
