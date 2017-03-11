package pl.zankowski.ibpp.formatter;

/**
 * @author Wojciech Zankowski
 */
public interface OutputFormatter {

    String parse(String symbol, String description, String currency, String exchange, String secType, String ISIN);

}
