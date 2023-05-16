package pl.poligonjava.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.poligonjava.utils.ScreenShot;
import pl.poligonjava.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AccountPage {
    private WebDriver driver;

    protected ExtentTest test;

    public ScreenShot screenShot = new ScreenShot(driver);

    @FindBy(id = "reg_email")
    private WebElement regEmail;

    @FindBy(id = "reg_password")
    private WebElement regPass;

    @FindBy(id = "username")
    private WebElement loginName;

    @FindBy(id = "password")
    private WebElement loginPass;

    @FindBy(name = "register")
    private WebElement registerBtn;

    @FindBy(name = "login")
    private WebElement loginBtn;

    @FindBy(xpath = "//ul[@class='woocommerce-error']//li")
    private List<WebElement> errors;

    public AccountPage(WebDriver driver, ExtentTest test) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.test = test;
    }

    public AccountPage registerDataFill(String email, String pass) throws IOException {
        regEmail.sendKeys(email);
        regPass.sendKeys(pass);
        test.log(Status.PASS, "Register Form Filled", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }

    public AccountPage loginDataFill(String login, String pass) throws IOException {
        loginName.sendKeys(login);
        loginPass.sendKeys(pass);
        test.log(Status.PASS, "Login Data Filled", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }

    public LoggedPage registerClick() throws IOException {
        registerBtn.click();
        test.log(Status.PASS, "Register Button Clicked", screenShot.getScreenshotMethodName("pass", driver));
        return new LoggedPage(driver, test);
    }

    public AccountPage registerErrorClick() throws IOException {
        registerBtn.click();
        test.log(Status.PASS, "Register Button Error Clicked", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }

    public LoggedPage loginClick() throws IOException {
        loginBtn.click();
        test.log(Status.PASS, "Login Button Clicked", screenShot.getScreenshotMethodName("pass", driver));
        return new LoggedPage(driver, test);
    }

    public AccountPage loginErrorClick() throws IOException {
        loginBtn.click();
        test.log(Status.PASS, "Login Error Button Clicked", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }

    public List<String> getErrors() {
        SeleniumHelper.waitForNotEmptyList(driver, By.xpath("//ul[@class='woocommerce-error']//li"));
        return errors.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
