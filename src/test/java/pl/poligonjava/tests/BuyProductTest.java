package pl.poligonjava.tests;

import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.Test;
import pl.poligonjava.models.Customer;
import pl.poligonjava.pages.CartPage;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.pages.OrdersPage;

public class BuyProductTest extends BaseTest{

    @Test
    public void buyAllProducts() {
        Customer customer = new Customer();
        new MainPage(driver)
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
