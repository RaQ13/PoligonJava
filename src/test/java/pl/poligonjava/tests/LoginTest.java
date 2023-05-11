package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.filewirtter.ReadFile;

import java.io.FileNotFoundException;
import java.util.List;

public class LoginTest extends BaseTest{

    protected String pass;

    @Test
    public void loginTest() throws FileNotFoundException {
        ExtentTest test = extentReports.createTest("Login Test");

        String usernameEmail = ReadFile.readFile();
        this.pass = usernameEmail;
        String username = usernameEmail.replace("@gmail.com", "");

        WebElement greetingParam =
                new MainPage(driver, test)
                .myAccountClick()
                .loginDataFill(usernameEmail, pass)
                .loginClick()
                .getGreetingParam();

        Assert.assertEquals(username, greetingParam.getText());
    }

    @Test
    public void loginEmptyPass() throws FileNotFoundException {
        ExtentTest test = extentReports.createTest("Login Empty Pass Test");
        String email = ReadFile.readFile();
        List<String> errors = new MainPage(driver, test)
                .myAccountClick()
                .loginDataFill(email, "")
                .loginErrorClick()
                .getErrors();

        Assert.assertTrue(errors.contains("ERROR: The password field is empty."));
    }

    @Test
    public void loginInvalidPass() throws FileNotFoundException {
        ExtentTest test = extentReports.createTest("Login Invalid Password Test");
        String email = ReadFile.readFile();
        List<String> errors = new MainPage(driver, test)
                .myAccountClick()
                .loginDataFill(email, "random")
                .loginErrorClick()
                .getErrors();

        Assert.assertTrue(errors.get(0).contains("ERROR: Incorrect username or password."));
    }
}
