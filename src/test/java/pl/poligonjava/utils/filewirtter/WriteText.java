package pl.poligonjava.utils.filewirtter;

import java.io.FileWriter;
import java.io.IOException;

public class WriteText {

    public static void writeText(String text) throws IOException {
        FileWriter writer = new FileWriter("src/test/resources/register.txt");
        writer.write(text);
        writer.close();
    }
}
