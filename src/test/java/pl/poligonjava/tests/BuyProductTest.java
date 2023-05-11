package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.poligonjava.models.Customer;
import pl.poligonjava.pages.CartPage;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.pages.OrdersPage;
import pl.poligonjava.utils.Listener;
import pl.poligonjava.utils.ScreenShot;
import pl.poligonjava.utils.SeleniumHelper;

import java.io.IOException;

@Listeners(value = {Listener.class})

public class BuyProductTest extends BaseTest{

    @Test
    public void buyAllProducts() throws IOException {
        ExtentTest test = extentReports.createTest("Buy All products");
        Customer customer = new Customer();
        new MainPage(driver, test)
                .shopClick()
                .addAllProducts()
                .cartLinkClick()
                .checkAreAllProducts()
                .proceedBtnClick()
                .fillForm(customer)
                .checkProducts()
                .checkPayment()
                .placeOrderClick()
                .checkOrderDetails();
    }
}
