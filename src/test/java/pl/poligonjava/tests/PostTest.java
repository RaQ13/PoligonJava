package pl.poligonjava.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;
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
                .fillCommentForm("random comment", name, email, url)
                .submitCommentForm();
    }
}
