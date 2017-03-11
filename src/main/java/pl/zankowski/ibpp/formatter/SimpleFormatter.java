package pl.zankowski.ibpp.formatter;

/**
 * @author Wojciech Zankowski
 */
public class SimpleFormatter implements OutputFormatter {

    @Override
    public String parse(String symbol, String description, String currency, String exchange, String secType, String ISIN) {
        return exchange.toUpperCase() + ";" + symbol.toUpperCase() + ";" + description
                + ";" + secType.toUpperCase() + ";" + currency.toUpperCase()+";"+ISIN.toUpperCase();
    }

}
