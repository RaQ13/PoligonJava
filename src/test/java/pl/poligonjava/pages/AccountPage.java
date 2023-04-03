package pl.poligonjava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    private WebDriver driver;

    @FindBy(id = "reg_email")
    private WebElement regInput;

    @FindBy(id = "reg_password")
    private WebElement regPass;

    @FindBy(name = "register")
    private WebElement registerBtn;

    public AccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public AccountPage registerDataFill(String email, String pass) {
        regInput.sendKeys(email);
        regPass.sendKeys(pass);
        return this;
    }

    public LoggedPage registerClick() {
        registerBtn.click();
        return new LoggedPage(driver);
    }

}
