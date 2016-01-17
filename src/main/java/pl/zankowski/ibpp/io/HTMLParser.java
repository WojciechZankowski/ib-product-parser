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

	public static final String URL_TEMPLATE = "https://www.interactivebrokers.com/en/index" +
			".php?f=2222&exch=%s&showcategories=%s&showproducts=&sequence_idx=%s&sortproducts" +
			"=&ib_entity=new#show";
	public static final String ETFS_URL_TEMPLATE = "https://www.interactivebrokers" + "" +
			".com/en/trading/etfs.php?exch=%s";

	public List<IBProduct> parseProducts(String exchange, String secType) {
		System.out.println("Downloading " + exchange + " data...");
		List<IBProduct> products = new ArrayList<>();

		int index = 100;
		while (true) {
			Connection connection;
			try {
				String url = getURL(exchange, secType, index);
				connection = Jsoup.connect(url);
				Document doc = connection.get();

				Element htmlTag = doc.getElementsByTag("html").get(0);
				Element bodyTags = htmlTag.getElementsByTag("body").get(0);

				if (! secType.equals("ETFS") && ! hasMore(bodyTags)) {
					break;
				}

				if (secType.equals("ETFS")) {
					parseBrokenTable(bodyTags, products);
					break;
				} else {
					parseStdTable(bodyTags, products);
				}
			} catch (IOException e) {
				// cry
				e.printStackTrace();
			} finally {
				index += 100;
			}
		}

		return products;
	}

	protected void parseStdTable(Element bodyTags, List<IBProduct> products) {
		Element tableDiv = bodyTags.getElementsByClass("table-responsive").get(bodyTags
				.getElementsByClass("table-responsive").size() - 1);
		Element table = tableDiv.getElementsByTag("table").get(0);
		Element tableBody = table.getElementsByTag("tbody").get(table.getElementsByTag("tbody")
				.size() - 1);
		Elements rows = tableBody.getElementsByTag("tr");

		for (Element row : rows) {
			products.add(parseRow(row));
		}
	}

	protected void parseBrokenTable(Element bodyTags, List<IBProduct> products) {
		int skip = 0;
		Element table = bodyTags.getElementsByClass("TableOutline").get(1);
		Elements rows = table.getElementsByTag("tr");

		for (Element row : rows) {
			if (skip++ < 2) {
				continue;
			}

			if (row.getElementsByTag("td").size() == 4) {
				products.add(parseRow(row));
			}
		}
	}

	protected IBProduct parseRow(Element row) {
		String symbol = row.getElementsByTag("td").get(0).text();
		Element descElement = row.getElementsByTag("td").get(1);
		String description = descElement.getElementsByTag("a").get(0).text();
		String currency = row.getElementsByTag("td").get(3).text();

		return new IBProduct.IBProductBuilder()
				.symbol(symbol)
				.description(description)
				.currency(currency)
				.build();
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

	protected String getURL(String exchange, String secType, int index) {
		switch (secType) {
			case "ETFS":
				return String.format(ETFS_URL_TEMPLATE, exchange);
			default:
				return String.format(URL_TEMPLATE, exchange, secType, String.valueOf(index));
		}
	}

}