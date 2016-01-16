package pl.zankowski.ibpp.io.parser;

import pl.zankowski.ibpp.data.IBProduct;

/**
 * @author Wojciech Zankowski
 */
@FunctionalInterface
public interface OutputParser {

    String parse(IBProduct product, String exchange, String secType);

}
