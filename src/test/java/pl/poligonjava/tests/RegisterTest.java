package pl.poligonjava.tests;

import org.testng.annotations.Test;
import pl.poligonjava.utils.filewirtter.CreateFile;
import pl.poligonjava.utils.filewirtter.ReadFile;
import pl.poligonjava.utils.filewirtter.WriteText;

import java.io.IOException;

public class RegisterTest extends BaseTest {

    @Test
    public void Register() throws IOException {

        int random = (int) (Math.random() * 1000);
        String email = "przyklad" + random + "@gmail.com";

        CreateFile.createFile();
        WriteText.writeText(email);
        String line = ReadFile.readFile();
        System.out.println(line);

//        new MainPage(driver).myAccountClick().register(email);
    }
}

