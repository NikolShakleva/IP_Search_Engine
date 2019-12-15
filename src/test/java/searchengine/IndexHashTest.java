package searchengine;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class IndexHashTest {

    ArrayList<Page> allPages = new ArrayList<>();
    
    /**
     * Setting up random ArrayList<Page> with 5-50 correct pages
     * This list should be handed to the responder by the WebServer
     * 
     * Pages will be made with a url, title and ArrayList of 2-50 words
     */
    @BeforeEach
    public void setUp()   {
   
       for(int i = 0 ; i < 10 ; i++)  {
            String title = "title" + i;
            String pageUrl = "*PAGE https://www." + title + ".com";
            var words = new ArrayList<String>();
            //for(int no = 0; no < 5 ; no++ )  {
                String currentWord = "word" + i;
                words.add(currentWord);
                //i++;
           // }
            var page = new Page(pageUrl, title, words);
            allPages.add(page);
        } 
        var words = new ArrayList<String>();
        words.add("word1");
        String pageUrl = "*PAGE https://www.title.com";
        var page = new Page(pageUrl, "title", words);
        allPages.add(page);

    }

    @Test
    public void makeEmptyHashMap()  {
        ArrayList<Page> emptyHashMap = new ArrayList<>();
        Index ind = new IndexHash(emptyHashMap);
        int wordsTP = ind.getwordsToPages().keySet().size();
        assertEquals(wordsTP, 0);
    }

    @Test
    public void makeHashM()  {
        Index ind = new IndexHash(allPages);
        int wordsTP = ind.getwordsToPages().keySet().size();
        assertEquals(wordsTP, 10);
    }

    @Test
    public void getAllPagesTest()   {
        Index ind = new IndexHash(allPages);
        int allPagesNB = ind.getAllPages().size();
        assertEquals(allPagesNB, 11);
    }

    @Test
    public void matchingPagesTest() {
        Index ind = new IndexHash(allPages);
        int matchingPage = ind.matchingPages("word1").size();
        assertEquals(matchingPage, 2);

    }    
}