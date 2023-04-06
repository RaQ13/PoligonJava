package pl.poligonjava.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;

import java.util.List;

public class BuyProductTest extends BaseTest{

    @Test
    public void buyProduct() {
        new MainPage(driver)
                .shopClick()
                .addAllProducts()
                .cartLinkClick();
    }
}
