package pl.poligonjava.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.filewirtter.ReadFile;

import java.io.FileNotFoundException;
import java.util.List;

public class LoginTest extends BaseTest{

    protected String pass;

    @Test
    public void loginTest() throws FileNotFoundException {

        String usernameEmail = ReadFile.readFile();
        this.pass = usernameEmail;
        String username = usernameEmail.replace("@gmail.com", "");

        WebElement greetingParam =
                new MainPage(driver)
                .myAccountClick()
                .loginDataFill(usernameEmail, pass)
                .loginClick()
                .getGreetingParam();

        Assert.assertEquals(username, greetingParam.getText());
    }

    @Test
    public void registerEmptyPass() throws FileNotFoundException {
        String email = ReadFile.readFile();
        List<String> errors = new MainPage(driver)
                .myAccountClick()
                .loginDataFill(email, "")
                .loginErrorClick()
                .getErrors();

        Assert.assertTrue(errors.contains("ERROR: The password field is empty."));
    }

}
