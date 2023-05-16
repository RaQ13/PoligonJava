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

public class PostPage {

    protected WebDriver driver;
    private ExtentTest test;

    public ScreenShot screenShot = new ScreenShot(driver);

    @FindBy(id = "comment")
    private WebElement commentTextInput;

    @FindBy(id = "author")
    private WebElement commentNameInput;

    @FindBy(id = "email")
    private WebElement commentEmailInput;

    @FindBy(id = "url")
    private WebElement commentUrlInput;

    @FindBy(id = "submit")
    private WebElement commentSubmitBtn;

    public PostPage(WebDriver driver, ExtentTest test) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.test = test;
    }

    public PostPage fillCommentForm(String commentText, String name, String email, String url) throws IOException {
        SeleniumHelper.waitForElemetToBeVisible(driver, commentTextInput);
        commentTextInput.sendKeys(commentText);
        commentNameInput.sendKeys(name);
        commentEmailInput.sendKeys(email);
        commentUrlInput.sendKeys(url);
        test.log(Status.PASS, "Comment Form Filled", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }

    public ErrorPage submitCommentForm() throws IOException {
        commentSubmitBtn.click();
        test.log(Status.PASS, "Submit Comment Button Clicked", screenShot.getScreenshotMethodName("pass", driver));
        return new ErrorPage(driver, test);
    }
}
