package pl.poligonjava.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.poligonjava.pages.LoggedPage;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.filewirtter.CreateFile;
import pl.poligonjava.utils.filewirtter.ReadFile;
import pl.poligonjava.utils.filewirtter.WriteText;

import java.io.IOException;

public class RegisterTest extends BaseTest {

    /** Bazy na stronie resetujasię co 24 godziny
     *
     * Tworzy randomowy email
     * Zapisuje w pliku i korzysta w logowaniu
     *
     * */

    protected String pass;
    @Test
    public void Register() throws IOException {

        int random = (int) (Math.random() * 1000);
        String email = "przyklad" + random + "@gmail.com";
        this.pass = email; //hasło takie samo jak email

        CreateFile.createFile();
        WriteText.writeText(email);
        String line = ReadFile.readFile();
        String username = line.replace("@gmail.com", "");

        WebElement greetingParam = new MainPage(driver)
                .myAccountClick()
                .registerDataFill(email, pass)
                .registerClick()
                .getGreetingParam();

        Assert.assertEquals(greetingParam.getText(), username);
    }
}

