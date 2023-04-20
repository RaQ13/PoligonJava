package pl.poligonjava.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.poligonjava.utils.SeleniumHelper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage {

    protected WebDriver driver;

    @FindBy(xpath = "//a[text()='Add to cart']")
    List<WebElement> addToCartLinks;

    @FindBy(linkText = "Cart")
    private WebElement cartLink;

    @FindBy(xpath = "//sup[@class='count czr-wc-count']")
    private List<WebElement> countProductsInCart;

    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /** Dodaje wszystkie produkty do koszyka */
    public ProductsPage addAllProducts() {
        SeleniumHelper.waitForElemetToBeVisible(driver, addToCartLinks.get(0));

        addToCartLinks
                .stream()
                .map(element -> element.getAttribute("data-product_id"))
                .forEach(uniqueID -> {
                    addToCartClick(addToCartLinks, uniqueID);
                    SeleniumHelper.waitForPseudoElement(driver, getPseudoelem(uniqueID));
                });

        return this;
    }
    /** Sprawdza content pseudoelementu */
    public Object getPseudoelem(String uniqueId) {
        String script = "return window.getComputedStyle(document.querySelector('a.add_to_cart_button[data-product_id=\""+ uniqueId + "\"]'), ':after').getPropertyValue('content')";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String pseudoElem = (String) js.executeScript(script);
        if(pseudoElem != "none") {return true;} else {return false;}
    }

    /** Klika na add to cart z delikatnym opóźnieniem */
    public void addToCartClick(List<WebElement> addToCartLinks, String uniqueID) {
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
    }

    public CartPage cartLinkClick() {
        SeleniumHelper.waitForElemetToBeVisible(driver, cartLink);
        String productsInCart = countProductsInCart.stream().filter(WebElement::isDisplayed).toList().get(0).getText();
        if (addToCartLinks.size() == Integer.parseInt(productsInCart)) {
            cartLink.click();
        }
        return new CartPage(driver);
    }
}
