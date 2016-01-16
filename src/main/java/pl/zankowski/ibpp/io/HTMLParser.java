package pl.zankowski.ibpp.io;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.zankowski.ibpp.data.IBProduct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wojciech Zankowski
 */
public class HTMLParser {

    public static final String URL_TEMPLATE = "https://www.interactivebrokers.com/en/index.php?f=2222&exch=%s&showcategories=%s&showproducts=&sequence_idx=%s&sortproducts=&ib_entity=new#show";

    public List<IBProduct> parseProducts(String exchange, String secType) {
        List<IBProduct> products = new ArrayList<IBProduct>();

        int index = 100;
        while (true) {
            System.out.print("Downloading data.\r");
            Connection connection;
            try {
                String url = String.format(URL_TEMPLATE, exchange, secType, String.valueOf(index));
                connection = Jsoup.connect(url);
                Document doc = connection.get();

                Element htmlTag = doc.getElementsByTag("html").get(0);
                Element bodyTags = htmlTag.getElementsByTag("body").get(0);

                if (!hasMore(bodyTags)) {
                    break;
                }

                System.out.print("Downloading data..\r");

                Element tableDiv = bodyTags.getElementsByClass("table-responsive").get(3);
                Element table = tableDiv.getElementsByTag("table").get(0);
                Element tableBody = table.getElementsByTag("tbody").get(0);
                Elements rows = tableBody.getElementsByTag("tr");

                for (Element row : rows) {
                    String symbol = row.getElementsByTag("td").get(0).text();
                    Element descElement = row.getElementsByTag("td").get(1);
                    String description = descElement.getElementsByTag("a").get(0).text();
                    String currency = row.getElementsByTag("td").get(3).text();

                    IBProduct product = new IBProduct.IBProductBuilder()
                            .symbol(symbol)
                            .description(description)
                            .currency(currency)
                            .build();
                    products.add(product);
                }

                System.out.print("Downloading data...\r");
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                index += 100;
            }
        }

        return products;
    }

    protected boolean hasMore(Element body) {
        Element contentElement = body.getElementById("contents");
        Element section = contentElement.getElementById("products-exchanges");
        Element container = section.getElementsByClass("container").get(0);
        Element row = container.getElementsByClass("row").get(0);
        Elements elements = row.getElementsByTag("p");
        for (Element element : elements) {
            if (element.text().equals("No result for this combination.")) {
                return false;
            }
        }
        return true;
    }

}