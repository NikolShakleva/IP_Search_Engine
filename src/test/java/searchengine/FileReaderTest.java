package searchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotLinkException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * FileReaderTest
 */
public class FileReaderTest {

    private FileReader filereader;

    /*@BeforeAll
    public void setUp() throws IOException {
        var filename = Files.readString(Paths.get("config.txt")).strip();
        filereader = new FileReader(filename);
    }*/
    
    @Test
    public void fileContainsOneWebsiteWithoutStarPage()   {
        filereader = new FileReader("TestEmptyFile.txt");
        int result = filereader.getAllPagesList().size();
        assertEquals(0, result);
    }
    @Test
    public void fileContainsOneWebsiteWithStarPage()   {
        filereader = new FileReader("TestEmptyFile.txt");
        int result = filereader.getAllPagesList().size();
        assertEquals(1, result);
    }


}