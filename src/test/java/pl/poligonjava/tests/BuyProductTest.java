package pl.poligonjava.tests;

import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;

public class BuyProductTest extends BaseTest{

    @Test
    public void buyAllProducts() {
        new MainPage(driver)
                .shopClick()
                .addAllProducts()
                .cartLinkClick();
    }
}
