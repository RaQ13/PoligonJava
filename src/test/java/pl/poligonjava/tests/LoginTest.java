package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.ScreenShot;
import pl.poligonjava.utils.filewirtter.ReadFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LoginTest extends BaseTest{

    protected String pass;

    ScreenShot screenShot = new ScreenShot(driver);
    @Test
    public void loginTest() throws IOException {
        ExtentTest test = extentReports.createTest("Login Test");

        String usernameEmail = ReadFile.readFile();
        test.log(Status.PASS, "Email Read From File", screenShot.getScreenshotMethodName("pass", driver));
        this.pass = usernameEmail;
        String username = usernameEmail.replace("@gmail.com", "");
        test.log(Status.PASS, "Name Created");

        WebElement greetingParam =
                new MainPage(driver, test)
                .myAccountClick()
                .loginDataFill(usernameEmail, pass)
                .loginClick()
                .getGreetingParam();

        test.log(Status.PASS, "Greeting Param Found", screenShot.getScreenshotMethodName("pass", driver));
        Assert.assertEquals(username, greetingParam.getText());
        test.log(Status.PASS, "Greeting Param Equals", screenShot.getScreenshotMethodName("pass", driver));
    }

    @Test
    public void loginEmptyPass() throws IOException {
        ExtentTest test = extentReports.createTest("Login Empty Pass Test");
        String email = ReadFile.readFile();
        test.log(Status.PASS, "Email Read From File", screenShot.getScreenshotMethodName("pass", driver));
        List<String> errors = new MainPage(driver, test)
                .myAccountClick()
                .loginDataFill(email, "")
                .loginErrorClick()
                .getErrors();

        test.log(Status.PASS, "Errors Displayed", screenShot.getScreenshotMethodName("pass", driver));
        Assert.assertTrue(errors.contains("ERROR: The password field is empty."));
        test.log(Status.PASS, "Errors Equals", screenShot.getScreenshotMethodName("pass", driver));
    }

    @Test
    public void loginInvalidPass() throws IOException {
        ExtentTest test = extentReports.createTest("Login Invalid Password Test");
        String email = ReadFile.readFile();
        test.log(Status.PASS, "Email Read From File", screenShot.getScreenshotMethodName("pass", driver));
        List<String> errors = new MainPage(driver, test)
                .myAccountClick()
                .loginDataFill(email, "random")
                .loginErrorClick()
                .getErrors();

        test.log(Status.PASS, "Errors Displayed", screenShot.getScreenshotMethodName("pass", driver));
        Assert.assertTrue(errors.get(0).contains("ERROR: Incorrect username or password."));
        test.log(Status.PASS, "Errors Equals", screenShot.getScreenshotMethodName("pass", driver));
    }
}
