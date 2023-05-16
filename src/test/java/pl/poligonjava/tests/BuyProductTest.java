package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.poligonjava.models.Customer;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.Listener;


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
