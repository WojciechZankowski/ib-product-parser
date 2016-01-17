package pl.zankowski.ibpp.io.parser;

/**
 * @author Wojciech Zankowski
 */
@FunctionalInterface
public interface OutputParser {

    String parse(String symbol, String description, String currency, String exchange, String secType);

}
