package searchengine;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

/**
 * WebServer class - It recieves a search request from the user and sends back a list of responses correspindig to the user search
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.12.16
 * 
 */

public class IndexHashTest {

    ArrayList<Page> allPages = new ArrayList<>();
    
    /**
     * Setting up an ArrayList<Page> with 11 correct pages
     * This list should be handed to the Index
     * 
     * Pages will be made with a url, title and ArrayList of 1-3 words
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
        words.add("word1");
        words.add("word2");
        String pageUrl = "*PAGE https://www.title.com";
        var page = new Page(pageUrl, "title", words);
        allPages.add(page);
    }

    @Test
    public void checksIfTheHashMapIsCreatedAndReturnedProperly()  {
        Index ind = new IndexHash(allPages);
        int numberOfKeyWords = ind.getwordsToPages().keySet().size();
        assertEquals(numberOfKeyWords, 10);
    }

    @Test
    public void checksIfReturnesAllPagesFromTheFile()   {
        Index ind = new IndexHash(allPages);
        int allPagesNB = ind.getAllPages().size();
        assertEquals(allPagesNB, 11);
    }

    @Test
    public void checksIfReturnesRightNumberOfPagesThatContainAKeyWord() {
        Index ind = new IndexHash(allPages);
        int matchingPage = ind.matchingPages("word1").size();
        assertEquals(matchingPage, 2);
    }  
    
}