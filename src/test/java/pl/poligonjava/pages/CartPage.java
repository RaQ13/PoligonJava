package pl.poligonjava.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import pl.poligonjava.utils.ScreenShot;
import pl.poligonjava.utils.SeleniumHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    protected WebDriver driver;

    protected ExtentTest test;

    public ScreenShot screenShot = new ScreenShot(driver);

    @FindBy(xpath = "//td[@class='product-name']")
    private List<WebElement> productsListNames;

    @FindBy(xpath = "//a[contains(text(), 'Proceed to checkout')]")
    private WebElement proceedBtn;

    public CartPage(WebDriver driver, ExtentTest test) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.test = test;
    }

    public CartPage checkAreAllProducts() throws IOException {
        Assert.assertEquals(productsListNames.size(), 3);
        List<String> productsNames = productsListNames.stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println(productsNames.size());
        Assert.assertTrue(productsNames.contains("Java Selenium WebDriver"));
        Assert.assertTrue(productsNames.contains("GIT basics"));
        Assert.assertTrue(productsNames.contains("BDD Cucumber"));
        test.log(Status.PASS, "All Products Checked", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }

    public OrdersPage proceedBtnClick() throws IOException {
        SeleniumHelper.waitForElementToBeClicable(driver, proceedBtn);
        SeleniumHelper.scrollDown(driver);
        proceedBtn.click();
        test.log(Status.PASS, "Proceed To Checkout Button Clicked", screenShot.getScreenshotMethodName("pass", driver));
        return new OrdersPage(driver, test);
    }
}
