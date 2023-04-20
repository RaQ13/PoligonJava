package pl.poligonjava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.poligonjava.models.Customer;
import pl.poligonjava.utils.SeleniumHelper;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersPage {

    protected WebDriver driver;

    @FindBy(name = "billing_first_name")
    private WebElement firstNameInput;

    @FindBy(name = "billing_last_name")
    private WebElement lastNameInput;

    @FindBy(id = "order_comments")
    private WebElement commentsInput;

    @FindBy(name = "billing_company")
    private WebElement companyNameInput;

    @FindBy(id = "billing_country")
    private WebElement selectCountry;

    @FindBy(xpath = "//li[@class='select2-results__option' and text()='Poland']")
    private WebElement polandCountry;

    @FindBy(name = "billing_address_1")
    private WebElement streetAddressInput;

    @FindBy(name = "billing_address_2")
    private WebElement apartmentInput;

    @FindBy(name = "billing_postcode")
    private WebElement postalCodeInput;

    @FindBy(name = "billing_city")
    private WebElement cityInput;

    @FindBy(name = "billing_phone")
    private WebElement phoneInput;

    @FindBy(name = "billing_email")
    private WebElement emailInput;

    @FindBy(xpath = "//td[@class='product-name']")
    private List<WebElement> productsList;

    public OrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public OrdersPage fillForm(Customer customer) {
        firstNameInput.sendKeys(customer.getFirstName());
        lastNameInput.sendKeys(customer.getLastName());
        commentsInput.sendKeys(customer.getComments());
        companyNameInput.sendKeys(customer.getCompanyName());
        Select select = new Select(selectCountry);
        select.selectByVisibleText(customer.getCountrySelect());
        streetAddressInput.sendKeys(customer.getStreetHouseAddress());
        apartmentInput.sendKeys(customer.getStreetApartmentAddress());
        postalCodeInput.sendKeys(customer.getPostalCode());
        cityInput.sendKeys(customer.getCity());
        phoneInput.sendKeys(customer.getCity());
        emailInput.sendKeys(customer.getEmail());
        return this;
    }
}
