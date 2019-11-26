package searchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotLinkException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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
     // the test is wrong because if the file doesn't start with a "*PAGE" we just hava an infinite loop
     public void fileContainsOneWebsiteWithoutStarPage()   {
         filereader = new FileReader("FirstLinestartsWithNostarPage.txt");
         Scanner sc = new Scanner ("FirstLinestartsWithNostarPage.txt");
         String notReachable = sc.nextLine();
         assertNull(notReachable);
    }

    @Test 
    // assume that if the page object has no title or words is not aded to the list of PageObjects
    public void Websites_With_No_Words_OR_No_Title_Are_Ommited(){
        filereader = new FileReader("NoWordsORNoTitle.txt");
        int result = filereader.getAllPagesList().size();
        assertEquals(0, result);
    }

    @Test 
    // assume that the text file is not empthy and has next line
    public void readFile_Has_At_Least_One_Line_Of_Input(){
        filereader = new FileReader("config.txt");
        assertFalse("config.txt".isEmpty());
        Scanner sc = new Scanner("config.txt");
        boolean line = sc.hasNext();
        assertTrue(line);
    }
    //asummes that if a line starts with * a page object is created
    @Test
    public void ReadFile_Assumes_That_If_A_Line_Starts_With_A_StarPage_A_New_Page_Object_Is_Created(){
        filereader = new FileReader("OneStarPageLine.txt");
        int result = filereader.getAllPagesList().size();
        assertEquals(1, result);
    }

    @Test 
    public void ReadFiles_Assume_That_Only_Lines_Without_A_StarPage_Will_Be_Added_To_The_List_Of_Words(){
        filereader = new FileReader("config.txt");
        filereader.readFile("config.txt");
        boolean result = filereader.getAllPagesList().get(0).getWords().contains("*");
        assertFalse(result);
    }

}
// it doesnt have next line
// it doesnt start with *PAGE
// it doesnt start with *
// is not Empthy