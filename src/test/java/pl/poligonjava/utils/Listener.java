package pl.poligonjava.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pl.poligonjava.tests.BaseTest;


public class Listener extends BaseTest implements ITestListener {

    protected WebDriver driver;
    protected String status;
    private ScreenShot screenShot;

    @Override
    public void onTestStart(ITestResult result) {

        try {
            this.driver=(WebDriver)result.getTestClass().getRealClass().getSuperclass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        this.screenShot = new ScreenShot(this.driver);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        this.status = "success";
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

    }

    @Override
    public void onFinish(ITestContext context) {
    }
}