package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pl.poligonjava.utils.DriverFactory;

public class BaseTest {

    public WebDriver driver;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;

    @BeforeSuite
    public void suite() {
        htmlReporter = new ExtentHtmlReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @AfterSuite
    public void afterSuite() {
        htmlReporter.flush();
        extentReports.flush();
    }

    @BeforeMethod
    public void setup() {
        this.driver = DriverFactory.getDriver();
        this.driver.manage().window().maximize();
        this.driver.get("http://seleniumdemo.com");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
