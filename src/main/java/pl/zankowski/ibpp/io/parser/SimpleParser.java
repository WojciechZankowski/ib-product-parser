package pl.zankowski.ibpp.io.parser;

import pl.zankowski.ibpp.data.IBProduct;

/**
 * @author Wojciech Zankowski
 */
public class SimpleParser implements OutputParser {

    @Override
    public String parse(IBProduct product, String exchange, String secType) {
        return exchange + ";" + product.getSymbol() + ";" + product.getDescription()
                + ";" + secType + ";" + product.getCurrency();
    }

}
