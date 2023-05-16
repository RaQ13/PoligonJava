package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.poligonjava.pages.LoggedPage;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.Listener;
import pl.poligonjava.utils.ScreenShot;
import pl.poligonjava.utils.filewirtter.CreateFile;
import pl.poligonjava.utils.filewirtter.ReadFile;
import pl.poligonjava.utils.filewirtter.WriteText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Listeners(value = {Listener.class})

public class RegisterTest extends BaseTest {

    /** Bazy na stronie resetujasię co 24 godziny
     *
     * Tworzy randomowy email
     * Zapisuje w pliku i korzysta w logowaniu
     *
     * */

    protected String pass;

    public ScreenShot screenShot = new ScreenShot(driver);
    @Test
    public void RegisterValidData() throws IOException {
        ExtentTest test = extentReports.createTest("Register Valid Data Test");
        int random = (int) (Math.random() * 1000);
        String email = "przyklad" + random + "@gmail.com";
        this.pass = email; //hasło takie samo jak email

        CreateFile.createFile();
        WriteText.writeText(email);
        String line = ReadFile.readFile();
        String username = line.replace("@gmail.com", "");

        WebElement greetingParam = new MainPage(driver, test)
                .myAccountClick()
                .registerDataFill(email, pass)
                .registerClick()
                .getGreetingParam();

        test.log(Status.PASS, "Gretting Param Found", screenShot.getScreenshotMethodName("pass", driver));
        Assert.assertEquals(greetingParam.getText(), username);
        test.log(Status.PASS, "Gretting Param Equals Username", screenShot.getScreenshotMethodName("pass", driver));
    }

    @Test
    public void RegisterExistingEmail() throws IOException {
        ExtentTest test = extentReports.createTest("Register Existing Email Test");
        String email = ReadFile.readFile();

        List<String> errors = new MainPage(driver, test)
                .myAccountClick()
                .registerDataFill(email, "")
                .registerErrorClick()
                .getErrors();
        test.log(Status.PASS, "Errors Displayed", screenShot.getScreenshotMethodName("pass", driver));
        Assert.assertTrue(errors.contains("Error: An account is already registered with your email address. Please log in."));
        test.log(Status.PASS, "Errors Equals", screenShot.getScreenshotMethodName("pass", driver));
    }

    @Test
    public void RegisterInvalidEmail() throws IOException {
        ExtentTest test = extentReports.createTest("Register Valid Data Test");
        String email = ReadFile.readFile();
        String invalidEmail = email.replace(".com", "");

        List<String> errors = new MainPage(driver, test)
                .myAccountClick()
                .registerDataFill(invalidEmail, "jakieś hasło")
                .registerErrorClick()
                .getErrors();
        test.log(Status.PASS, "Errors Displayed", screenShot.getScreenshotMethodName("pass", driver));
        Assert.assertTrue(errors.contains("Error: Please provide a valid email address."));
        test.log(Status.PASS, "Errors Equals", screenShot.getScreenshotMethodName("pass", driver));
    }

}

