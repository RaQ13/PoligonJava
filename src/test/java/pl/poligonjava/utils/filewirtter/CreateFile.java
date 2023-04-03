package pl.poligonjava.utils.filewirtter;

import java.io.File;
import java.io.IOException;

public class CreateFile {

    public static void createFile() throws IOException {
        File registerFile = new File("src/test/resources/register.txt");
        if(registerFile.createNewFile()) {
            System.out.println("Stworzono nowy plik");
        } else {
            System.out.println("plik juz istnieje");
        }
    }
}
