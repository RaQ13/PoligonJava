package pl.poligonjava.utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class JsReader {

    protected WebDriver driver;

    public static void jsReader(String jsFilePath, WebDriver driver) throws FileNotFoundException {

        File jsfile = new File(jsFilePath);

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String content = FileUtils.readFileToString(jsfile, "UTF-8");
            js.executeScript(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
