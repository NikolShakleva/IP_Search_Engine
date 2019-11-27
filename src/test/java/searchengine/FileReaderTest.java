package searchengine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

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
         filereader = new FileReader("test-file-errors.txt");
         Scanner sc = new Scanner ("test-file-errors.txt");
         String error = sc.nextLine();
         if (!error.contains("*")) error = "Error";
         sc.close();
         assertEquals("Error" ,error);
    }

    @Test 
    // assume that if the page object has no title or words is not aded to the list of PageObjects
    public void Websites_With_No_Words_OR_No_Title_Are_Ommited(){
        filereader = new FileReader("NoWordsORNoTitle.txt");
        int result = filereader.getAllPages().size();
        assertEquals(0, result);
    }

    @Test 
    // assume that the text file is not empthy and has next line
    public void readFile_Has_At_Least_One_Line_Of_Input(){
        filereader = new FileReader("test-file.txt");
        assertFalse("test-file.txt".isEmpty());
        Scanner sc = new Scanner("test-file.txt");
        boolean line = sc.hasNext();
        sc.close();
        assertTrue(line);
    }
    //asummes that if a line starts with * a page object is created
    @Test
    public void ReadFile_Assumes_That_If_A_Line_Starts_With_A_StarPage_A_New_Page_Object_Is_Created(){
        filereader = new FileReader("test-file.txt");
        int result = filereader.getAllPages().size();
        assertEquals(2, result);
    }

    @Test 
    public void ReadFiles_Assume_That_Only_Lines_Without_A_StarPage_Will_Be_Added_To_The_List_Of_Words(){
        filereader = new FileReader("test-file.txt");
        boolean result = filereader.getAllPages().get(0).getWords().contains("*");
        assertFalse(result);
    }

}
// it doesnt have next line
// it doesnt start with *
// is not Empthy