package pl.poligonjava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ErrorPage {

    protected WebDriver driver;
    @FindBy(xpath = "//body//p[contains(text(), 'Duplicate')]")
    private WebElement errorMsg;

    public ErrorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }
}
