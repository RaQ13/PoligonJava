package pl.poligonjava.utils.filewirtter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    public static String readFile() throws FileNotFoundException {

        File readFile = new File("src/test/resources/register.txt");
        Scanner reader = new Scanner(readFile);
        String line = reader.nextLine();
        reader.close();
        return line;
    }
}
