package pl.zankowski.ibpp.io;

import pl.zankowski.ibpp.data.IBExchange;
import pl.zankowski.ibpp.data.IBProduct;
import pl.zankowski.ibpp.io.parser.OutputParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Wojciech Zankowski
 */
public class FileWriter {

    private final OutputParser outputParser;

    public FileWriter(OutputParser outputParser) {
        this.outputParser = outputParser;
    }

    public void write(List<IBProduct> productList, String exchange, String secType) {
        System.out.println("Saving " + exchange + " file.");
        BufferedWriter writer = null;
        try {
            File file = new File("/" + IBExchange.getExchangeFromCode(exchange) + " - " + secType + ".txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            writer = new BufferedWriter(new java.io.FileWriter(file.getAbsoluteFile()));

            for (IBProduct product : productList) {
                writer.write(outputParser.parse(product, exchange, secType) + "\n");
            }
        } catch (IOException e) {
            // cry
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                // cry
                e.printStackTrace();
            }
        }
    }

}
