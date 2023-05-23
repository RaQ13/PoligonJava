package pl.poligonjava.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.xpath.operations.Or;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.poligonjava.models.Customer;
import pl.poligonjava.utils.ScreenShot;
import pl.poligonjava.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class OrdersPage {

    protected WebDriver driver;

    protected ExtentTest test;

    public ScreenShot screenShot = new ScreenShot(driver);

    public Float sum = (float) 0;

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

    @FindBy(xpath = "//h1[text()='Orders']")
    private WebElement orderHeading;

    @FindBy(xpath = "//td[@class='product-total']")
    private List<WebElement> productTotal;

    @FindBy(xpath = "//tr[@class='order-total']//span[@class='woocommerce-Price-amount amount']")
    private WebElement orderTotal;

    @FindBy(id = "place_order")
    private WebElement placeOrderBtn;

    public OrdersPage(WebDriver driver, ExtentTest test) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.test = test;
    }

    public OrdersPage fillForm(Customer customer) throws IOException {
        SeleniumHelper.waitForElemetToBeVisible(driver, orderHeading);
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
        phoneInput.sendKeys(customer.getPhone());
        emailInput.sendKeys(customer.getEmail());
        SeleniumHelper.scrollDown(driver);
        test.log(Status.PASS, "Form Filled With Customer Data", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }
    public OrdersPage checkProducts() throws IOException {

        try {
            List<String> productsListNames = productsList.stream().map(el -> {
                return el.getText().replace(SeleniumHelper.subStringFromElement(el.getText(), "×"), "").trim();
            }).collect(Collectors.toList());
            List<String> productsQuanity = productsList.stream().map(el -> SeleniumHelper.subStringFromElement(el.getText(), "×").replace("× ", "")).collect(Collectors.toList());

//            Assert.assertTrue(productsListNames.contains("BDD Cucumber"));
//            Assert.assertTrue(productsListNames.contains("GIT basics"));
//            Assert.assertTrue(productsListNames.contains("Java Selenium WebDriver"));

            productsQuanity.forEach(el-> {Assert.assertTrue(el.contains("1"));});
            test.log(Status.PASS, "Products Checked", screenShot.getScreenshotMethodName("pass", driver));
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            List<String> productsListNames = productsList.stream().map(el -> {
                return el.getText().replace(SeleniumHelper.subStringFromElement(el.getText(), "×"), "").trim();
            }).collect(Collectors.toList());
            List<String> productsQuanity = productsList.stream().map(el -> SeleniumHelper.subStringFromElement(el.getText(), "×").replace("× ", "")).collect(Collectors.toList());

            Assert.assertTrue(productsListNames.contains("BDD Cucumber"));
            Assert.assertTrue(productsListNames.contains("GIT basics"));
            Assert.assertTrue(productsListNames.contains("Java Selenium WebDriver"));

            productsQuanity.forEach(el-> {Assert.assertTrue(el.contains("1"));});
            test.log(Status.PASS, "Products Checked", screenShot.getScreenshotMethodName("pass", driver));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public OrdersPage checkPayment() throws IOException {
        List<Float> fromProductTotal = productTotal.stream().map(el -> {
            return Float.parseFloat(el.getText().replace(SeleniumHelper.subStringFromElement(el.getText(), "zł"), "").trim().replace(",", "."));
        }).collect(Collectors.toList());
        fromProductTotal.forEach(el-> this.sum+= el);
        Float totalSum = Float.parseFloat(orderTotal.getText().replace(" zł", "").replace(",", "."));

        Assert.assertEquals(totalSum, sum);
        test.log(Status.PASS, "Payment Checked", screenShot.getScreenshotMethodName("pass", driver));
        return this;
    }

    public OrdersDetails placeOrderClick() throws IOException {
        SeleniumHelper.scrollDown(driver);
        SeleniumHelper.waitForElementToBeClicable(driver, placeOrderBtn);
        try {
            placeOrderBtn.click();
            test.log(Status.PASS, "Place Order Clicked", screenShot.getScreenshotMethodName("pass", driver));
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            placeOrderBtn.click();
            test.log(Status.PASS, "Place Order Clicked", screenShot.getScreenshotMethodName("pass", driver));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new OrdersDetails(driver, test);
    }


}
