package pl.poligonjava.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import pl.poligonjava.utils.SeleniumHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    protected WebDriver driver;

    @FindBy(xpath = "//td[@class='product-name']")
    private List<WebElement> productsListNames;

    @FindBy(xpath = "//a[contains(text(), 'Proceed to checkout')]")
    private WebElement proceedBtn;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CartPage checkAreAllProducts() {
        Assert.assertEquals(productsListNames.size(), 3);
        List<String> productsNames = productsListNames.stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue(productsNames.contains("Java Selenium WebDriver"));
        Assert.assertTrue(productsNames.contains("GIT basics"));
        Assert.assertTrue(productsNames.contains("BDD Cucumber"));
        return this;
    }

    public OrdersPage proceedBtnClick() {
        SeleniumHelper.waitForElementToBeClicable(driver, proceedBtn);
        SeleniumHelper.scrollDown(driver);
        proceedBtn.click();
        return new OrdersPage(driver);
    }
}
