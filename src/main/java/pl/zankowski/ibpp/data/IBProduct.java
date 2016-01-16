package pl.zankowski.ibpp.data;

/**
 * @author Wojciech Zankowski
 */
public class IBProduct {

    private final String symbol;
    private final String description;
    private final String currency;

    public IBProduct(String symbol, String description, String currency) {
        this.symbol = symbol;
        this.description = description;
        this.currency = currency;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "IBProduct{" +
                "symbol='" + symbol + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }

    public static class IBProductBuilder {

        private String symbol = "";
        private String description = "";
        private String currency = "";

        public IBProductBuilder() {
        }

        public IBProductBuilder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public IBProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public IBProductBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public IBProduct build() {
            return new IBProduct(symbol, description, currency);
        }

    }
}
