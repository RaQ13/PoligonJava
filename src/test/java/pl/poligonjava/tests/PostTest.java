package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.remote.Browser;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.SeleniumHelper;
import pl.poligonjava.utils.filewirtter.ReadFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PostTest extends BaseTest{

    @Test
    public void submitFirstPostComment() throws IOException {
        ExtentTest test = extentReports.createTest("Submit First Post Comment Test");
        String email = ReadFile.readFile();
        String name = email.replace("@gmail.com", "");
        String url = driver.getCurrentUrl();

        new MainPage(driver, test)
                .getFirstPost()
                .fillCommentForm(email, name, email, url)
                .submitCommentForm();
    }

    @Test
    public void submitDuplicateFirstPostComment() throws IOException {
        ExtentTest test = extentReports.createTest("Duplicate First Comment Test");
        String email = ReadFile.readFile();
        String name = email.replace("@gmail.com", "");
        String url = driver.getCurrentUrl();

        String errorMsg = new MainPage(driver, test)
                .getFirstPost()
                .fillCommentForm(email, name, email, url)
                .submitCommentForm()
                .getErrorMsg().getText();

        System.out.println(errorMsg);
        Assert.assertTrue(errorMsg.contains("Duplicate comment detected; it looks as though you’ve already said that!"));
    }

    @Test
    public void submitSecondPostComment() throws IOException {
        ExtentTest test = extentReports.createTest("Second Post Comment Test");
        String email = ReadFile.readFile();
        String name = email.replace("@gmail.com", "");
        String url = driver.getCurrentUrl();

        new MainPage(driver, test)
                .getSecondPost()
                .fillCommentForm(email, name, email, url)
                .submitCommentForm();
    }

    @Test
    public void submitDuplicateSecondPostComment() throws IOException {
        ExtentTest test = extentReports.createTest("Submit Duplicate Second Post Test");
        String email = ReadFile.readFile();
        String name = email.replace("@gmail.com", "");
        String url = driver.getCurrentUrl();

        String errorMsg = new MainPage(driver, test)
                .getSecondPost()
                .fillCommentForm(email, name, email, url)
                .submitCommentForm()
                .getErrorMsg().getText();

        Assert.assertTrue(errorMsg.contains("Duplicate comment detected; it looks as though you’ve already said that!"));
    }
}
