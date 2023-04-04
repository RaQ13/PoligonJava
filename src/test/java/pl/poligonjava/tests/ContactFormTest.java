package pl.poligonjava.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.filewirtter.ReadFile;

import java.io.FileNotFoundException;

public class ContactFormTest extends BaseTest{

    @Test
    public void submitForm() throws FileNotFoundException {
        String email = ReadFile.readFile();
        String name = email.replace("@gmail.com", "");

        String respond = new MainPage(driver)
                .fillContactForm(name, email, "random message")
                .submitForm()
                .getSubmitRespond().getText();

        Assert.assertEquals(respond, "It is demo page! We are not sending emails!");
    }

    @Test
    public void submitFormInvalidEmail() throws FileNotFoundException {
        String name = ReadFile.readFile().replace("@gmail.com", "");

        String respond = new MainPage(driver)
                .fillContactForm(name, name, "random message")
                .submitForm()
                .getSubmitRespond().getText();

        Assert.assertTrue(respond.contains("Invalid form submission : some fields have not been entered properly. The following field is not well formed : Test Data."));
    }
}

