package pl.zankowski.ibpp.io;

import java.io.File;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.zankowski.ibpp.data.IBExchange;
import pl.zankowski.ibpp.io.parser.SimpleParser;

/**
 * @author Wojciech Zankowski
 */
public class HTMLParser {

	public static final String URL_TEMPLATE = "https://www.interactivebrokers.com/en/index" +
			".php?f=2222&exch=%s&showcategories=%s&showproducts=&sequence_idx=%s&sortproducts" +
			"=&ib_entity=new#show";
	public static final String ETFS_URL_TEMPLATE = "https://www.interactivebrokers" + "" +
			".com/en/trading/etfs.php?exch=%s";

	public static void parseProducts(String exchange, String secType) {
		System.out.println("Downloading " + exchange + " data...");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(new File("./" + IBExchange.getExchangeFromCode(exchange) +
					" - " + secType + ".txt"), new SimpleParser());

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
						parseBrokenTable(bodyTags, exchange, secType, fileWriter);
						break;
					} else {
						parseStdTable(bodyTags, exchange, secType, fileWriter);
					}
				} catch (IOException e) {
					// cry
					e.printStackTrace();
				} finally {
					index += 100;
				}
			}
		} catch (IOException e) {
			// cry
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				fileWriter.close();
			}
		}
	}

	private static void parseStdTable(Element bodyTags, String exchange, String secType,
	                                  FileWriter writer) throws IOException {
		Element tableDiv = bodyTags.getElementsByClass("table-responsive").get(bodyTags
				.getElementsByClass("table-responsive").size() - 1);
		Element table = tableDiv.getElementsByTag("table").get(0);
		Element tableBody = table.getElementsByTag("tbody").get(table.getElementsByTag("tbody")
				.size() - 1);
		Elements rows = tableBody.getElementsByTag("tr");

		for (Element row : rows) {
			parseRow(row, exchange, secType, writer);
		}
	}

	private static void parseBrokenTable(Element bodyTags, String exchange, String secType,
	                                     FileWriter writer) throws IOException {
		int skip = 0;
		Element table = bodyTags.getElementsByClass("TableOutline").get(1);
		Elements rows = table.getElementsByTag("tr");

		for (Element row : rows) {
			if (skip++ < 2) {
				continue;
			}

			if (row.getElementsByTag("td").size() == 4) {
				parseRow(row, exchange, secType, writer);
			}
		}
	}

	private static void parseRow(Element row, String exchange, String secType, FileWriter writer)
			throws IOException {
		String symbol = row.getElementsByTag("td").get(0).text();
		Element descElement = row.getElementsByTag("td").get(1);
		String description = descElement.getElementsByTag("a").get(0).text();
		String currency = row.getElementsByTag("td").get(3).text();

		writer.write(symbol, description, currency, exchange, secType);
	}

	private static boolean hasMore(Element body) {
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

	private static String getURL(String exchange, String secType, int index) {
		switch (secType) {
			case "ETFS":
				return String.format(ETFS_URL_TEMPLATE, exchange);
			default:
				return String.format(URL_TEMPLATE, exchange, secType, String.valueOf(index));
		}
	}

}