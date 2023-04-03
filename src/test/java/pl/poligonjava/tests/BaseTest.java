package pl.poligonjava.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.poligonjava.utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("http://seleniumdemo.com");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
