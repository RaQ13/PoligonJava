package pl.poligonjava.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.SeleniumHelper;
import pl.poligonjava.utils.filewirtter.ReadFile;

import java.io.FileNotFoundException;

public class PostTest extends BaseTest{

    @Test
    public void submitComment() throws FileNotFoundException {
        String email = ReadFile.readFile();
        String name = email.replace("@gmail.com", "");
        String url = driver.getCurrentUrl();

        new MainPage(driver)
                .getFirstPost()
                .fillCommentForm(email, name, email, url)
                .submitCommentForm();
    }

    @Test
    public void submitDuplicateComment() throws FileNotFoundException {
        String email = ReadFile.readFile();
        String name = email.replace("@gmail.com", "");
        String url = driver.getCurrentUrl();

        String errorMsg = new MainPage(driver)
                .getFirstPost()
                .fillCommentForm(email, name, email, url)
                .submitCommentForm()
                .getErrorMsg().getText();

        System.out.println(errorMsg);
        Assert.assertTrue(errorMsg.contains("Duplicate comment detected; it looks as though you’ve already said that!"));
    }
}