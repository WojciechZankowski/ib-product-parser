package pl.zankowski.ibpp;

import pl.zankowski.ibpp.data.IBExchange;
import pl.zankowski.ibpp.data.IBExchanges;
import pl.zankowski.ibpp.io.FileWriter;
import pl.zankowski.ibpp.io.HTMLParser;
import pl.zankowski.ibpp.io.parser.SimpleParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wojciech Zankowski
 */
public class IBProductParser {

    public HTMLParser htmlParser;
    public FileWriter fileWriter;

    public static void main(String[] args) {
        IBProductParser ibpp = new IBProductParser();
        ibpp.start(args);
    }

    public void start(String[] args) {
        CommandLineProperties properties = new CommandLineProperties();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-e")) {
                while (++i < args.length) {
                    if (args[i].startsWith("-")) {
                        break;
                    }
                    if (args[i].equals("all")) {
                        properties.setAll(true);
                    } else if (args[i].equals("allna")) {
                        properties.setAllna(true);
                    } else if (args[i].equals("alleu")) {
                        properties.setAlleu(true);
                    } else if (args[i].equals("allasia")) {
                        properties.setAllasia(true);
                    } else {
                        properties.addExchange(args[i]);
                    }
                }
            }
            if (args[i].equals("-t")) {
                if (++i < args.length) {
                    properties.setSecType(args[i]);
                }
            }
        }

        setUpProperties(properties);
        run(properties);
    }

    public void setUpProperties(CommandLineProperties properties) {
        if (properties.getSecType().isEmpty()) {
            throw new IllegalArgumentException("Illegal security type value. Security type cannot be empty.");
        }

        switch (properties.getSecType()) {
            case "STK":
                setUpStockExchanges(properties);
                break;
            default:
                throw new IllegalArgumentException("Illegal security type value. Security type cannot has " + properties.getSecType() + " value.");
        }

        if (properties.getExchanges().isEmpty()) {
            throw new IllegalArgumentException("Exchange list cannot be empty.");
        }
    }

    public void setUpStockExchanges(CommandLineProperties properties) {
        if (properties.isAllna() || properties.isAll()) {
            for (IBExchange exchange : IBExchanges.ALL_STOCK_NA) {
                properties.addExchange(exchange.getCode());
            }
        }
        if (properties.isAlleu() || properties.isAll()) {
            for (IBExchange exchange : IBExchanges.ALL_STOCK_EU) {
                properties.addExchange(exchange.getCode());
            }
        }
        if (properties.isAllasia() || properties.isAll()) {
            for (IBExchange exchange : IBExchanges.ALL_STOCK_ASIA) {
                properties.addExchange(exchange.getCode());
            }
        }
    }

    public void run(CommandLineProperties properties) {
        htmlParser = new HTMLParser();
        fileWriter = new FileWriter(new SimpleParser());

        for (String exchange : properties.getExchanges()) {
            try {
                fileWriter.write(htmlParser.parseProducts(exchange, properties.getSecType()), exchange, properties.getSecType());
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public class CommandLineProperties {

        private final List<String> exchanges = new ArrayList<String>();
        private String secType;
        private boolean all;
        private boolean alleu;
        private boolean allna;
        private boolean allasia;

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

        public boolean isAlleu() {
            return alleu;
        }

        public void setAlleu(boolean alleu) {
            this.alleu = alleu;
        }

        public boolean isAllna() {
            return allna;
        }

        public void setAllna(boolean allna) {
            this.allna = allna;
        }

        public boolean isAllasia() {
            return allasia;
        }

        public void setAllasia(boolean allasia) {
            this.allasia = allasia;
        }
    }

}
