package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.Browser;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.ScreenShot;
import pl.poligonjava.utils.SeleniumHelper;
import pl.poligonjava.utils.filewirtter.ReadFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public class  PostTest extends BaseTest{

    public ScreenShot screenShot = new ScreenShot(driver);

    //TODO jeżeli pierwszy jest pisany, ma wykrywać czy jest zduplikowany

    @Test (priority = 1)
    public void submitFirstPostComment() throws IOException {
        ExtentTest test = extentReports.createTest("Submit First Post Comment Test");
        String email = ReadFile.readFile();
        test.log(Status.PASS, "Email Read From File");
        String name = email.replace("@gmail.com", "");
        test.log(Status.PASS, "Name Created");

        String url = driver.getCurrentUrl();

        new MainPage(driver, test)
                .getFirstPost()
                .fillCommentForm(email, name, email, url)
                .submitCommentForm()
                .checkIsSubmited();
        test.log(Status.PASS, "First Post Comment Submited", screenShot.getScreenshotMethodName("pass", driver));
    }

    @Test (priority = 2)
    public void submitDuplicateFirstPostComment() throws IOException {
        ExtentTest test = extentReports.createTest("Duplicate First Comment Test");
        String email = ReadFile.readFile();
        test.log(Status.PASS, "Email Read From File");
        String name = email.replace("@gmail.com", "");
        test.log(Status.PASS, "Name Created");
        String url = driver.getCurrentUrl();

        String errorMsg = new MainPage(driver, test)
                .getFirstPost()
                .fillCommentForm(email, name, email, url)
                .submitCommentForm()
                .getErrorMsg().getText();

        Assert.assertTrue(errorMsg.contains("Duplicate comment detected; it looks as though you’ve already said that!"));
        test.log(Status.PASS, "Error Message Equals", screenShot.getScreenshotMethodName("pass", driver));
    }

    @Test (priority = 3)
    public void submitSecondPostComment() throws IOException {
        ExtentTest test = extentReports.createTest("Second Post Comment Test");
        String email = ReadFile.readFile();
        test.log(Status.PASS, "Email Read From File");
        String name = email.replace("@gmail.com", "");
        test.log(Status.PASS, "Name Created");
        String url = driver.getCurrentUrl();

        new MainPage(driver, test)
                .getSecondPost()
                .fillCommentForm(email, name, email, url)
                .submitCommentForm()
                .checkIsSubmited();
        test.log(Status.PASS, "Second Post Comment Submited", screenShot.getScreenshotMethodName("pass", driver));
    }

    @Test (priority = 4)
    public void submitDuplicateSecondPostComment() throws IOException {
        ExtentTest test = extentReports.createTest("Submit Duplicate Second Post Test");
        String email = ReadFile.readFile();
        test.log(Status.PASS, "Email Read From File");
        String name = email.replace("@gmail.com", "");
        test.log(Status.PASS, "Name Created");
        String url = driver.getCurrentUrl();

        String errorMsg = new MainPage(driver, test)
                .getSecondPost()
                .fillCommentForm(email, name, email, url)
                .submitCommentForm()
                .getErrorMsg().getText();

        Assert.assertTrue(errorMsg.contains("Duplicate comment detected; it looks as though you’ve already said that!"));
        test.log(Status.PASS, "Error Message Equals", screenShot.getScreenshotMethodName("pass", driver));
    }
}
