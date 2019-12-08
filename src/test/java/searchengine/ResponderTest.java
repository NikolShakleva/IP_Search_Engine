package searchengine;



import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;


/**
 * ResponderTest
 */

public class ResponderTest {
    private RandomGenerator rnd = new RandomGenerator();
    private ArrayList<Page> correctPages;

    /**
     * Setting up random ArrayList<Page> with 5-50 correct pages
     * This list should be handed to the responder by the WebServer
     * 
     * Pages will be made with a url, title and ArrayList of 2-50 words
     */
    @BeforeAll
    public void setUp()   {
        for(int i = 0 ; i < rnd.generateInt(5, 50) ; i++)  {
            String title = rnd.generateString();
            String pageUrl = "*PAGE https://www." + title + ".com";

            var words = new ArrayList<String>();
            while(i < rnd.generateInt(5, 50))  {
                String currentWord = rnd.generateString();
                words.add(currentWord);
            }
            var page = new Page(pageUrl, title, words);
            correctPages.add(page);
        } 
    }

    //@Test
   /* public void noMatchesFoundInList()  {
        var emptyList = new ArrayList<Page>();
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
    }*/
}