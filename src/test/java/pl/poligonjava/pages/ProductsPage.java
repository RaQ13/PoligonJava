package pl.poligonjava.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.poligonjava.utils.SeleniumHelper;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    protected WebDriver driver;

    @FindBy(xpath = "//a[text()='Add to cart']")
    List<WebElement> addToCartLinks;

    @FindBy(linkText = "Cart")
    private WebElement cartLink;

    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public ProductsPage addAllProducts() {
        SeleniumHelper.waitForElemetToBeVisible(driver, addToCartLinks.get(0));

        addToCartLinks
                .stream()
                .map(element -> element.getAttribute("data-product_id"))
                .forEach(uniqueID -> {
                    addToCartClick(addToCartLinks, uniqueID);
                    SeleniumHelper.waitForPseudoElement(driver, getPseudoelem(uniqueID));
                });
//        String uni = "29";
//        String script2 = "return window.getComputedStyle(document.querySelector('a.add_to_cart_button[data-product_id=\""+ uni + "\"]'), ':after').getPropertyValue('content')";
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        String pseudoElem2 = (String) js.executeScript(script2);
//        System.out.println(pseudoElem2);

        return this;
    }
 //\uE017
    public Object getPseudoelem(String uniqueId) {
        String script = "return window.getComputedStyle(document.querySelector('a.add_to_cart_button[data-product_id=\""+ uniqueId + "\"]'), ':after').getPropertyValue('content')";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String pseudoElem = (String) js.executeScript(script);
        if(pseudoElem != "none") {return true;} else {return false;}
    }

    public void addToCartClick(List<WebElement> addToCartLinks, String uniqueID) {
        addToCartLinks.forEach(element -> {
            if (element.getAttribute("data-product_id").equals(uniqueID)) {
                element.click();
            }
        });
    }

    public CartPage cartLinkClick() {
        cartLink.click();
        return new CartPage(driver);
    }
}
