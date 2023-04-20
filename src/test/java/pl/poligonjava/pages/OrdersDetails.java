package pl.poligonjava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersDetails {

    protected WebDriver driver;

    @FindBy(xpath = "//h1[@class='entry-title']")
    private WebElement orderDetailsHeading;

    @FindBy(xpath = "//td[@class='woocommerce-table__product-name product-name']")
    private List<WebElement> productsNames;

    public OrdersDetails(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void checkOrder() {

    }
}
