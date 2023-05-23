package pl.poligonjava.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.poligonjava.utils.ScreenShot;

import java.io.IOException;

public class ErrorPage {

    protected WebDriver driver;
    private ExtentTest test;

    ScreenShot screenShot = new ScreenShot(driver);
    @FindBy(xpath = "//body//p[contains(text(), 'Duplicate')]")
    private WebElement errorMsg;

    public ErrorPage(WebDriver driver, ExtentTest test) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.test = test;
    }

    public WebElement getErrorMsg() throws IOException {
        test.log(Status.PASS, "Error Message Displayed", screenShot.getScreenshotMethodName("pass", driver));
        return errorMsg;
    }

    public void checkIsSubmited() throws IOException {
        if (errorMsg != null) {
            test.log(Status.FAIL, "Error Message Found", screenShot.getScreenshotMethodName("failed", driver));
            throw new IOException("Duplicate comment detected; it looks as though you’ve already said that!");
        }
    }
}
