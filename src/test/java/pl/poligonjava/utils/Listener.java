package pl.poligonjava.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listener implements ITestListener {

    protected WebDriver driver = DriverFactory.getDriver();
    protected String status;

    private ScreenShot screenShot = new ScreenShot(driver);

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("I am starting Test");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        this.status = "pass";
        screenShot.takeScreenshot(this.status, driver);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        this.status = "fail";
        screenShot.takeScreenshot(this.status, driver);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        this.status = "skipped";
        screenShot.takeScreenshot(this.status, driver);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        this.status = "skippedWithPercentage";
        screenShot.takeScreenshot(this.status, driver);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
        this.status = "timeOut";
        screenShot.takeScreenshot(this.status, driver);
    }

    @Override
    public void onStart(ITestContext context) {
        this.status = "start";
        screenShot.takeScreenshot(this.status, driver);
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}