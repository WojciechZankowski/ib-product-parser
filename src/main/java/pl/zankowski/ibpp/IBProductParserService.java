package pl.zankowski.ibpp;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.zankowski.ibpp.data.IBExchange;
import pl.zankowski.ibpp.data.IBParserParameters;
import pl.zankowski.ibpp.formatter.OutputFormatter;
import pl.zankowski.ibpp.io.FileWriter;
import pl.zankowski.ibpp.io.IBSourceParser;
import pl.zankowski.ibpp.util.IBUrls;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Wojciech Zankowski
 */
public class IBProductParserService {

    private final ExecutorService crawlerExecutorService = Executors.newFixedThreadPool(4);
    private final IBSourceParser sourceParser = new IBSourceParser();

    public void parse(IBParserParameters parserParameters, OutputFormatter outputFormatter) {
        for (String exchange : parserParameters.getExchanges()) {
            crawlerExecutorService.submit(() ->
                    parse(exchange, parserParameters.getSecType(), outputFormatter)
            );
        }
    }

    private void parse(String exchange, String secType, OutputFormatter outputFormatter) {
        System.out.println("Downloading " + exchange + " data...");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("./" + IBExchange.getExchangeFromCode(exchange) +
                    " - " + secType + ".txt"), outputFormatter);

            int page = 1;
            while (true) {
                Connection connection;
                try {
                    String url = getURL(exchange, secType, page);
                    connection = Jsoup.connect(url);
                    Document doc = connection.get();

                    if (!sourceParser.parseProducts(doc, secType, exchange, fileWriter)) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    page++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }

    }

    private String getURL(String exchange, String secType, int page) {
        switch (secType) {
            case "ETFS":
                return String.format(IBUrls.ETFS_URL_TEMPLATE, exchange);
            default:
                return String.format(IBUrls.URL_TEMPLATE, exchange, secType, String.valueOf
                        (page));
        }
    }

    public void close() {
        crawlerExecutorService.shutdown();
    }

}
