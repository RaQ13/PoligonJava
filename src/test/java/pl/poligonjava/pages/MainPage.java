package pl.poligonjava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.poligonjava.utils.SeleniumHelper;

import java.util.List;

public class MainPage {

    private WebDriver driver;
    @FindBy (linkText = "My account")
    private WebElement myAccLink;

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

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public AccountPage myAccountClick() {
        myAccLink.click();
        return new AccountPage(driver);
    }

    public MainPage fillContactForm(String name, String email, String message) {
        formDataName.sendKeys(name);
        formDataEmail.sendKeys(email);
        formDataMessage.sendKeys(message);
        return this;
    }

    public MainPage submitContactForm() {
        submitFormBtn.click();
        return this;
    }

    public WebElement getSubmitRespond() {
        SeleniumHelper.waitForElemetToBeVisible(driver, formRespondParam);
        return formRespondParam;
    }

    public PostPage getFirstPost() {
        postsLinks.get(0).click();
        return new PostPage(driver);
    }

    public PostPage getSecondPost() {
        postsLinks.get(1).click();
        return new PostPage(driver);
    }
}
