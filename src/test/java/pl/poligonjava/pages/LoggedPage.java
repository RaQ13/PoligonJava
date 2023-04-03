package pl.poligonjava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoggedPage {

    protected WebDriver driver;

    @FindBy(xpath = "//div[@class='woocommerce-MyAccount-content']//strong")
    private List<WebElement> greetingParam;

    public LoggedPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getGreetingParam() {
        return greetingParam.get(0);
    }
}
