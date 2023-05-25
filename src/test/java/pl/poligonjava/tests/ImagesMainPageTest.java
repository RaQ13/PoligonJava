package pl.poligonjava.tests;

import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.poligonjava.pages.MainPage;
import pl.poligonjava.utils.Listener;

import java.io.IOException;

@Listeners (value = {Listener.class})

public class ImagesMainPageTest extends BaseTest {

    @Test
    public void CheckImagesOnMainPage() throws IOException {
        ExtentTest test = extentReports.createTest("Check images on main page");
        MainPage main = new MainPage(driver, test);
        main.checkImages();
    }
}
