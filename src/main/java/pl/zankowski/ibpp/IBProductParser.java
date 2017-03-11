package pl.zankowski.ibpp;

import pl.zankowski.ibpp.cli.CommandLineParser;
import pl.zankowski.ibpp.cli.CommandLineProperties;
import pl.zankowski.ibpp.data.IBParserParameters;
import pl.zankowski.ibpp.formatter.OutputFormatter;
import pl.zankowski.ibpp.formatter.SimpleFormatter;
import pl.zankowski.ibpp.util.IBParserParametersConverter;

/**
 * @author Wojciech Zankowski
 */
public class IBProductParser {

    private final CommandLineParser commandLineParser = new CommandLineParser();
    private final IBProductParserService ibProductParserService = new IBProductParserService();

    public static void main(String[] args) {
        System.setProperty("jsse.enableSNIExtension", "false");
        IBProductParser ibpp = new IBProductParser();
        ibpp.start(args);
    }

    private void start(String[] args) {
        if (args.length == 0) {
            System.err.println("Failed to initialize application, no arguments.");
            return;
        }

        try {
            CommandLineProperties commandLineProperties = commandLineParser.parseCommandLineProperties(args);
            validateCommandLineProperties(commandLineProperties);

            IBParserParameters ibParserParameters = IBParserParametersConverter.convert(commandLineProperties);
            OutputFormatter formatter = initFormatterClass(commandLineProperties.getOutputFormatterName());

            run(ibParserParameters, formatter);
            ibProductParserService.close();
        } catch (Exception e) {
            System.err.println("Failed to initialize application, no arguments.");
            e.printStackTrace();
        }
    }

    private void validateCommandLineProperties(CommandLineProperties properties) {
        if (properties.getSecType().isEmpty()) {
            throw new IllegalArgumentException("Illegal security type value. Security type " +
                    "cannot" +
                    " " +
                    "" + "be empty.");
        }
    }

    private OutputFormatter initFormatterClass(String className) throws Exception {
        if (className == null) {
            return new SimpleFormatter();
        }
        Class<?> formatterClass = Class.forName(className);
        Object classInstance = formatterClass.newInstance();
        if (!(classInstance instanceof OutputFormatter)) {
            throw new IllegalArgumentException("Invalid class name, class must implements OutputFormatter interface.");
        }
        return (OutputFormatter) classInstance;
    }

    private void run(IBParserParameters ibParserParameters, OutputFormatter formatter) {
        ibProductParserService.parse(ibParserParameters, formatter);
    }

}
