package pl.poligonjava.pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoggedPage {

    protected WebDriver driver;

    private ExtentTest test;

    @FindBy(xpath = "//div[@class='woocommerce-MyAccount-content']//strong")
    private List<WebElement> greetingParam;

    public LoggedPage(WebDriver driver, ExtentTest test) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.test = test;
    }

    public WebElement getGreetingParam() {
        return greetingParam.get(0);
    }
}
