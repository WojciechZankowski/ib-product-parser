package pl.zankowski.ibpp.io;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.zankowski.ibpp.util.CaptchaWannabeSolver;

import java.io.IOException;

/**
 * @author Wojciech Zankowski
 */
public class IBSourceParser {

    private final CaptchaWannabeSolver captchaSolver = new CaptchaWannabeSolver();

    public boolean parseProducts(Document document, String secType, String exchange, FileWriter fileWriter) throws IOException {
        Element htmlTag = document.getElementsByTag("html").get(0);
        Element bodyTags = htmlTag.getElementsByTag("body").get(0);

        if (secType.equals("ETFS")) {
            parseBrokenTable(bodyTags, exchange, secType, fileWriter);
        } else {
            parseStdTable(bodyTags, exchange, secType, fileWriter);
        }

        return hasMore(bodyTags);
    }

    private void parseStdTable(Element bodyTags, String exchange, String secType, FileWriter
            writer) throws IOException {
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

    private void parseBrokenTable(Element bodyTags, String exchange, String secType, FileWriter
            writer) throws IOException {
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

    private void parseRow(Element row, String exchange, String secType, FileWriter writer) throws
            IOException {
        String symbol = row.getElementsByTag("td").get(0).text();
        Element descElement = row.getElementsByTag("td").get(1);
        Elements link = descElement.getElementsByTag("a");
        String description = link.get(0).text();
        String currency = row.getElementsByTag("td").get(3).text();

        System.out.println("PARSING: " + symbol + ", " + description);

        String ISIN = "";
        String url = extractLink(link.attr("href"));
        if (!url.isEmpty()) {
            ISIN = openDetailsPage(url);
        }

        writer.write(symbol, description, currency, exchange, secType, ISIN);
    }

    private boolean hasMore(Element body) {
        Elements pagination = body.getElementsByClass("pagination");
        if (pagination.size() == 0) {
            return false;
        }

        Element paginationContainer = pagination.get(0);
        Element disabled = paginationContainer.getElementsByClass("disabled").get(0);
        Element content = disabled.getElementsByTag("a").get(0);

        return !content.text().contains(">");
    }

    private String parseDetailsPage(Connection connection, Document document) throws IOException {
        if (captchaSolver.isCaptchaWannabe(document)) {
            String code = captchaSolver.solveCaptcha(document);
            Document doc = connection.data("filter", code).data("Submit", "Submit").post();
            return parseDetailsPage(connection, doc);
        } else {
            return pullISINCode(document);
        }
    }

    private String openDetailsPage(String url) {
        try {
            Connection connection = Jsoup.connect(url);
            return parseDetailsPage(connection, connection.get());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String pullISINCode(Document document) {
        Elements tables = document.getElementsByClass("resultsTbl");
        if (tables.size() >= 3) {
            Element table = tables.get(2);
            Elements rows = table.getElementsByTag("td");
            if (rows.size() < 2 || !rows.get(rows.size() - 2).text().contains("ISIN")) {
                return "";
            }
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).text().equals("ISIN")) {
                    if (i + 1 < rows.size()) {
                        return rows.get(i + 1).text();
                    }
                }
            }
        }
        return "";
    }

    private String extractLink(String line) {
        String[] elements = line.split("'");
        return elements.length >= 2 ? elements[1] : "";
    }

}