package pl.zankowski.ibpp.cli;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wojciech Zankowski
 */
public class CommandLineProperties {

    private final List<String> exchanges = new ArrayList<>();
    private String secType;
    private boolean all;
    private boolean allEU;
    private boolean allNA;
    private boolean allAsia;
    private String outputFormatterName;

    public void addExchange(String exchange) {
        exchanges.add(exchange);
    }

    public List<String> getExchanges() {
        return exchanges;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public boolean isAllEU() {
        return allEU;
    }

    public void setAllEU(boolean allEU) {
        this.allEU = allEU;
    }

    public boolean isAllNA() {
        return allNA;
    }

    public void setAllNA(boolean allNA) {
        this.allNA = allNA;
    }

    public boolean isAllAsia() {
        return allAsia;
    }

    public void setAllAsia(boolean allAsia) {
        this.allAsia = allAsia;
    }

    public String getOutputFormatterName() {
        return outputFormatterName;
    }

    public void setOutputFormatterName(String outputFormatterName) {
        this.outputFormatterName = outputFormatterName;
    }

}
