package searchengine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * FileReaderTest
 */
public class FileReaderTest {

    private FileReader filereader;

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
        filereader = new FileReader("GeneralTestFile.txt");
        assertFalse("GeneralTestFile".isEmpty());
    }
    //asummes that if a line starts with * a page object is created
    @Test
    public void ReadFile_Assumes_That_If_A_Line_Starts_With_A_StarPage_A_New_Page_Object_Is_Created(){
        filereader = new FileReader("GeneralTestFile.txt");
        int result = filereader.getAllPages().size();
        assertEquals(2, result);
    }

    @Test 
    public void ReadFiles_Assume_That_Only_Lines_Without_A_StarPage_Will_Be_Added_To_The_List_Of_Words(){
        filereader = new FileReader("GeneralTestFile.txt");
        boolean result = filereader.getAllPages().get(0).getWords().contains("*");
        assertFalse(result);
    }

}
