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

    public AccountPage() {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void register(String email) {
        regInput.sendKeys(email);
    }

}