package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.SeleniumHelper;
import pl.poligonjava.utils.filewirtter.ReadFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ContactFormTest extends BaseTest{

    @Test
    public void submitForm() throws IOException {
        ExtentTest test = extentReports.createTest("Contact form test Test");
        String email = ReadFile.readFile();
        String name = email.replace("@gmail.com", "");

        String respond = new MainPage(driver, test)
                .fillContactForm(name, email, "random message")
                .submitContactForm()
                .getSubmitRespond().getText();

        Assert.assertEquals(respond, "It is demo page! We are not sending emails!");
    }

    @Test
    public void submitFormInvalidEmail() throws IOException {
        ExtentTest test = extentReports.createTest("Submit invalid Email Test Test");
        String name = ReadFile.readFile().replace("@gmail.com", "");

        String respond = new MainPage(driver, test)
                .fillContactForm(name, name, "random message")
                .submitContactForm()
                .getSubmitRespond().getText();

        Assert.assertTrue(respond.contains("Invalid form submission : some fields have not been entered properly. The following field is not well formed : Test Data."));
    }
}

