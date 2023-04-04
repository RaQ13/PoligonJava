package pl.poligonjava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.poligonjava.utils.SeleniumHelper;

public class PostPage {

    protected WebDriver driver;

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

    public PostPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public PostPage fillCommentForm(String commentText, String name, String email, String url) {
        SeleniumHelper.waitForElemetToBeVisible(driver, commentTextInput);
        commentTextInput.sendKeys(commentText);
        commentNameInput.sendKeys(name);
        commentEmailInput.sendKeys(email);
        commentUrlInput.sendKeys(url);
        return this;
    }

    public ErrorPage submitCommentForm() {
        commentSubmitBtn.click();
        return new ErrorPage(driver);
    }
}
