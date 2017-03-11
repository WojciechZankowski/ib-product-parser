package pl.zankowski.ibpp.data;

import java.util.List;

/**
 * @author Wojciech Zankowski
 */
public class IBParserParameters {

    private final List<String> exchanges;
    private final String secType;

    public IBParserParameters(List<String> exchanges, String secType) {
        this.exchanges = exchanges;
        this.secType = secType;
    }

    public List<String> getExchanges() {
        return exchanges;
    }

    public String getSecType() {
        return secType;
    }

    @Override
    public String toString() {
        return "IBParserParameters{" +
                "exchanges=" + exchanges +
                ", secType='" + secType + '\'' +
                '}';
    }

}
