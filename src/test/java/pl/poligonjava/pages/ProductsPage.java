package pl.poligonjava.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.poligonjava.utils.ScreenShot;
import pl.poligonjava.utils.SeleniumHelper;

import javax.naming.ldap.ExtendedRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage {

    protected WebDriver driver;

    protected ExtentTest test;

    public ScreenShot screenShot = new ScreenShot(driver);

    @FindBy(xpath = "//a[text()='Add to cart']")
    List<WebElement> addToCartLinks;

    @FindBy(linkText = "Cart")
    private WebElement cartLink;

    @FindBy(xpath = "//sup[@class='count czr-wc-count']")
    private List<WebElement> countProductsInCart;

    public ProductsPage(WebDriver driver, ExtentTest test) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.test = test;
    }

    /** Dodaje wszystkie produkty do koszyka */
    public ProductsPage addAllProducts() throws IOException {
        SeleniumHelper.waitForElemetToBeVisible(driver, addToCartLinks.get(0));

        addToCartLinks
                .stream()
                .map(element -> element.getAttribute("data-product_id"))
                .forEach(uniqueID -> {
                    try {
                        addToCartClick(addToCartLinks, uniqueID);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        SeleniumHelper.waitForPseudoElement(driver, getPseudoelem(uniqueID));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        test.log(Status.PASS, "All Produtcts Added", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }
    /** Sprawdza content pseudoelementu */
    public Object getPseudoelem(String uniqueId) throws IOException {
        String script = "return window.getComputedStyle(document.querySelector('a.add_to_cart_button[data-product_id=\""+ uniqueId + "\"]'), ':after').getPropertyValue('content')";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String pseudoElem = (String) js.executeScript(script);
        if(pseudoElem != "none") {
            test.log(Status.PASS, "Pseudeo Elem Appeared", screenShot.getScreenshotMethodName("pass", driver));
            return true; } else {return false;}
    }

    /** Klika na add to cart z delikatnym opóźnieniem */
    public void addToCartClick(List<WebElement> addToCartLinks, String uniqueID) throws IOException {
        addToCartLinks.forEach(element -> {
            if (element.getAttribute("data-product_id").equals(uniqueID)) {
                try {
                    element.click();
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        test.log(Status.PASS, "Add To Cart Clicked", screenShot.getScreenshotMethodName("pass", driver));
    }

    public CartPage cartLinkClick() throws IOException {
        SeleniumHelper.waitForElemetToBeVisible(driver, cartLink);
        String productsInCart = countProductsInCart.stream().filter(WebElement::isDisplayed).toList().get(0).getText();
        if (addToCartLinks.size() == Integer.parseInt(productsInCart)) {
            cartLink.click();
        }
        test.log(Status.PASS, "Cart Link Clicked", screenShot.getScreenshotMethodName("pass", driver));
        return new CartPage(driver, test);
    }
}
