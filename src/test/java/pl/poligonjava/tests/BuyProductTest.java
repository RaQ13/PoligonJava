package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.poligonjava.models.Customer;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.Listener;
import pl.poligonjava.utils.ScreenShot;


import java.io.IOException;

@Listeners(value = {Listener.class})

public class BuyProductTest extends BaseTest{

    ScreenShot screenShot = new ScreenShot(driver);
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

        test.log(Status.PASS, "All Products Bought", screenShot.getScreenshotMethodName("pass", driver));
    }

    @Test
    public void buyBdbCucumber() throws IOException {
        ExtentTest test = extentReports.createTest("Buy BDB Cucumber");
        Customer customer = new Customer();
        new MainPage(driver, test)
                .shopClick()
                .addBdbCucumber()
                .cartLinkClick()
                .proceedBtnClick()
                .fillForm(customer)
                .checkProducts()
                .checkPayment()
                .placeOrderClick()
                .checkOrderDetails();
    }
}
