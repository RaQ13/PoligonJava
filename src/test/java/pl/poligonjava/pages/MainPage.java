package pl.poligonjava.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.poligonjava.utils.ScreenShot;
import pl.poligonjava.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class MainPage {

    private WebDriver driver;
    private ExtentTest test;

    public ScreenShot screenShot = new ScreenShot(driver);
    @FindBy (linkText = "My account")
    private WebElement myAccLink;

    @FindBy (linkText = "Shop")
    private WebElement shopLink;

    @FindBy (id = "nimble_name317664225")
    private WebElement formDataName;

    @FindBy (id = "nimble_email317664225")
    private WebElement formDataEmail;

    @FindBy (id = "nimble_message317664225")
    private WebElement formDataMessage;

    @FindBy (id = "nimble_submit317664225")
    private WebElement submitFormBtn;

    @FindBy (id = "sek-form-respond")
    private WebElement formRespondParam;

    @FindBy(xpath = "//a[@class='czr-title']")
    private List<WebElement> postsLinks;

    public MainPage(WebDriver driver, ExtentTest test) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.test = test;
    }

    public AccountPage myAccountClick() {
        myAccLink.click();
        return new AccountPage(driver);
    }

    public ProductsPage shopClick() throws IOException {
        shopLink.click();
        test.log(Status.PASS, "Shop Link Clicked", screenShot.getScreenshotMethodName("pass", driver));
        return new ProductsPage(driver, test);
    }

    public MainPage fillContactForm(String name, String email, String message) throws IOException {
        formDataName.sendKeys(name);
        formDataEmail.sendKeys(email);
        formDataMessage.sendKeys(message);
        test.log(Status.PASS, "Contact Form Filled With Data", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }

    public MainPage submitContactForm() throws IOException {
        submitFormBtn.click();
        test.log(Status.PASS, "Contact Form Submitted", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }

    public WebElement getSubmitRespond() throws IOException {
        SeleniumHelper.waitForElemetToBeVisible(driver, formRespondParam);
        test.log(Status.PASS, "Submit Respond Received", screenShot.getScreenshotMethodName("pass", driver));
        return formRespondParam;
    }

    public PostPage getFirstPost() throws IOException {
        postsLinks.get(0).click();
        test.log(Status.PASS, "Firts Post Found", screenShot.getScreenshotMethodName("pass", driver));
        return new PostPage(driver);
    }

    public PostPage getSecondPost() throws IOException {
        postsLinks.get(1).click();
        test.log(Status.PASS, "Second Post Found", screenShot.getScreenshotMethodName("pass", driver));
        return new PostPage(driver);
    }
}
