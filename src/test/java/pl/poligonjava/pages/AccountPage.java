package pl.poligonjava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class AccountPage {
    private WebDriver driver;

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

    public AccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public AccountPage registerDataFill(String email, String pass) {
        regEmail.sendKeys(email);
        regPass.sendKeys(pass);
        return this;
    }

    public AccountPage loginDataFill(String login, String pass) {
        loginName.sendKeys(login);
        loginPass.sendKeys(pass);
        return this;
    }

    public LoggedPage registerClick() {
        registerBtn.click();
        return new LoggedPage(driver);
    }

    public AccountPage registerErrorClick() {
        registerBtn.click();
        return this;
    }

    public LoggedPage loginClick() {
        loginBtn.click();
        return new LoggedPage(driver);
    }

    public List<String> getErrors() {
        return errors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
