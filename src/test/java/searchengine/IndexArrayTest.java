package searchengine;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexArrayTest {
 private ArrayList<Page> allPages = new ArrayList<>();
    


 @BeforeEach
 public void setUp()   {

    FileReader freader = new FileReader("test-file1.txt");
    var index = new IndexListArray(freader.getAllPages());
    allPages = index.getAllPages();
   
 }


 @Test
    public void ThereArePagesMatchingTheInputString()    {
        var index = new IndexListArray (allPages);
        int result = index.matchingPages("word1").size();
        assertEquals(2,result);
    }

    @Test
    public void ThereAreNoPagesMatchingTheInputString() {
        var index = new IndexListArray (allPages);

        int result = index.matchingPages("word7").size();
        assertEquals(0,result);
    }






}