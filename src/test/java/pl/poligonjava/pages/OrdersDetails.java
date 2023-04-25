package pl.poligonjava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.poligonjava.models.Customer;
import pl.poligonjava.utils.SeleniumHelper;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersDetails {

    protected WebDriver driver;
    private Float sum = (float) 0;
    @FindBy(xpath = "//h1[@class='entry-title']")
    private WebElement orderDetailsHeading;

    @FindBy(xpath = "//table//a")
    private List<WebElement> productsNames;

    @FindBy(xpath = "//strong[@class='product-quantity']")
    private List<WebElement> productsQty;

    @FindBy(xpath = "//th[text()='Note:']/following-sibling::td")
    private WebElement customerNote;

    @FindBy(xpath = "//li[@class='woocommerce-order-overview__total total']/child::strong")
    private WebElement orderOverviewTotal;

    @FindBy(xpath = "//td[@class='woocommerce-table__product-total product-total']")
    private List<WebElement> productTotal;

    @FindBy(xpath = "//th[text()='Total:']/following-sibling::td")
    private WebElement orderTotal;

    public OrdersDetails(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void checkOrderDetails() {
        checkOrderProductsNames();
        checkQuanityOfProducts();
        checkCustomerNote();
        checkTotal();
    }

    public void checkOrderProductsNames() {
        SeleniumHelper.waitForElemetToBeVisible(driver, orderDetailsHeading);
        SeleniumHelper.waitForNotEmptyList(driver, By.xpath("//table//a"));

        List<String> products = productsNames.stream().map(WebElement::getText).collect(Collectors.toList());

        Assert.assertTrue(products.contains("BDD Cucumber"));
        Assert.assertTrue(products.contains("GIT basics"));
        Assert.assertTrue(products.contains("Java Selenium WebDriver"));

    }

    public void checkQuanityOfProducts() {
        List<String> qty = productsQty.stream().map(WebElement::getText).collect(Collectors.toList());
        qty.forEach(el -> {Assert.assertTrue(el.contains("× 1"));});

    }

    public void checkCustomerNote() {
        Assert.assertEquals(customerNote.getText(), new Customer().getComments());
    }

    public void checkTotal() {
        List<Float> fromProductsTotal = productTotal.stream().map(element -> {
            return Float.parseFloat(element.getText().replace(SeleniumHelper.subStringFromElement(element.getText(), "zł"), "").trim().replace(",", "."));
        }).collect(Collectors.toList());

        fromProductsTotal.forEach(el -> this.sum += el);
        String total = this.sum.toString().replace(".", ",") + " zł";

        Assert.assertEquals(orderOverviewTotal.getText(), total);
        Assert.assertEquals(orderTotal.getText(), total);
    }
}
