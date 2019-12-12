package searchengine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.*;



/**
 * ResponderTest
 */

public class ResponderTest {
    private RandomGenerator rnd = new RandomGenerator();
    private Map<Page, Double> correctPages;

    /**
     * Setting up random ArrayList<Page> with 5-50 correct pages
     * This list should be handed to the responder by the WebServer
     * 
     * Pages will be made with a url, title and ArrayList of 2-50 words
     */
    @BeforeAll
    public void setUp()   {
        int i = 0;
        while(i < 20)   {
       // for(int i = 0 ; i < rnd.generateInt(5, 50) ; i++)  {
            String title = rnd.generateString();
            String pageUrl = "*PAGE https://www." + title + ".com";
            double rlv = rnd.generateInt(1, 100);

            var words = new ArrayList<String>();
            while(i < rnd.generateInt(5, 50))  {
                String currentWord = rnd.generateString();
                words.add(currentWord);
            }
            var page = new Page(pageUrl, title, words);
            correctPages.put(page, rlv);
        } 
    }

    @Test
    public void noMatchesFoundInList()  {
        Map<Page, Double> emptyList = new HashMap<>();
        var responder = new Responder(emptyList);
        int responses = responder.getPageNames().size();

        assertEquals(0, responses);
    }

    @Test
    public void listOfStringsWithResponses()    {
        var responder = new Responder(correctPages);
        int responses = responder.getPageNames().size();
        int list = correctPages.size();

        assertEquals(list, responses);
    }
}