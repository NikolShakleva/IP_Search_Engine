package searchengine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * ResponderTest
 */

public class ResponderTest {
    RandomGenerator rnd = new RandomGenerator();
    Map<Page, Double> correctPages = new HashMap<>();
    

    /**
     * Setting up random ArrayList<Page> with 5-50 correct pages
     * This list should be handed to the responder by the WebServer
     * 
     * Pages will be made with a url, title and ArrayList of 2-50 words
     */
    @BeforeEach
    public void setUp()   {
   
       for(int i = 0 ; i < 10 ; i++)  {
            String title = rnd.generateString();
            String pageUrl = "*PAGE https://www." + title + ".com";
            double rlv = rnd.generateInt(1, 100);
            int no =rnd.generateInt(5, 50);
            var words = new ArrayList<String>();
            while(i < no)  {
                String currentWord = rnd.generateString();
                words.add(currentWord);
                i++;
            }
            var page = new Page(pageUrl, title, words);
            correctPages.put(page, rlv);
        } 
    }

    @Test
    public void noMatchesFoundInList()  {
        Map<Page, Double> mapFromWebserver = new HashMap<>();
        var responder = new Responder(mapFromWebserver);
        var pages = responder.getPageNames();
        
        assert(pages.isEmpty() == true);
    }

    @Test
    public void listOfStringsWithResponses()    {
        var responder = new Responder(correctPages);
        int responses = responder.getPageNames().size();
        int list = correctPages.size();

        assertEquals(list, responses);
    }

    @Test
    public void highestRelevanceIsFirst() {
        var responder = new Responder(correctPages);
        ArrayList<String> responses = responder.getPageNames();
        double highestValue = 0;
        String title = "";
        for(var entery : correctPages.entrySet()){
            double currentValue = entery.getValue();
            if(currentValue > highestValue) {
                highestValue = currentValue;
                title = entery.getKey().getTitle();
            }

        } assert(responses.get(0).contains(title));
    }

    @Test
    public void lowestRelevanceIsLast() {
        var responder = new Responder(correctPages);
        ArrayList<String> responses = responder.getPageNames();
        double lowestValue = 50;
        String title = "";
        for(var entery : correctPages.entrySet()){
            double currentValue = entery.getValue();
            if(currentValue < lowestValue) {
                lowestValue = currentValue;
                title = entery.getKey().getTitle();
            }

        } assert(responses.get(responses.size()-1).contains(title));
    }
}