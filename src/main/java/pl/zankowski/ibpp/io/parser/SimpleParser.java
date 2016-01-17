package pl.zankowski.ibpp.io.parser;

/**
 * @author Wojciech Zankowski
 */
public class SimpleParser implements OutputParser {

    @Override
    public String parse(String symbol, String description, String currency, String exchange, String secType) {
        return exchange + ";" + symbol + ";" + description
                + ";" + secType + ";" + currency;
    }

}
