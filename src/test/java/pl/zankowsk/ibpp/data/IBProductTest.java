package pl.zankowsk.ibpp.data;

import org.junit.Test;
import pl.zankowski.ibpp.data.IBProduct;

import static org.junit.Assert.assertEquals;

/**
 * @author Wojciech Zankowski
 */
public class IBProductTest {

    @Test
    public void testProductBuilder() {
        IBProduct expectedProduct = new IBProduct("", "", "");
        IBProduct actualProduct = new IBProduct.IBProductBuilder().build();
        assertProducts(expectedProduct, actualProduct);

        expectedProduct = new IBProduct("", "", "USD");
        actualProduct = new IBProduct.IBProductBuilder()
                .currency("USD")
                .build();
        assertProducts(expectedProduct, actualProduct);

        expectedProduct = new IBProduct("", "GOOGLE", "USD");
        actualProduct = new IBProduct.IBProductBuilder()
                .description("GOOGLE")
                .currency("USD")
                .build();
        assertProducts(expectedProduct, actualProduct);

        expectedProduct = new IBProduct("GOOG", "GOOGLE", "USD");
        actualProduct = new IBProduct.IBProductBuilder()
                .symbol("GOOG")
                .description("GOOGLE")
                .currency("USD")
                .build();
        assertProducts(expectedProduct, actualProduct);
    }


    private void assertProducts(IBProduct expectedProduct, IBProduct actualProduct) {
        assertEquals(expectedProduct.getSymbol(), actualProduct.getSymbol());
        assertEquals(expectedProduct.getDescription(), actualProduct.getDescription());
        assertEquals(expectedProduct.getCurrency(), actualProduct.getCurrency());
    }
}
