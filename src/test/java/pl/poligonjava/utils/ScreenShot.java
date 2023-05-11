package pl.poligonjava.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {

    protected WebDriver driver;

    protected String path = "src/test/resources/screenshots/";

    public ScreenShot(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public static String getTimeStamp() {
        String pattern = "yyyy-MM-dd--HH-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public String takeScreenshot(String status, WebDriver driver) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        String screenName = this.path + status + "--" + getTimeStamp() + ".png";
        File screen = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screen, new File(screenName));
            return screenName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String takeScreenshotMethodName(String status, WebDriver driver) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        String screenName = this.path + status + "--" + SeleniumHelper.getMethodName() + "--" + getTimeStamp() + ".png";
        File screen = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screen, new File(screenName));
            return screenName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Obsługuje screenshota w htmlreporter
    public MediaEntityModelProvider getScreenshot(String status, WebDriver driver) throws IOException {
        String pathTo = takeScreenshot(status, driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(pathTo).build();
    }

    public MediaEntityModelProvider getScreenshotMethodName(String status, WebDriver driver) throws IOException {
        String pathTo = takeScreenshotMethodName(status, driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(pathTo).build();
    }

}
