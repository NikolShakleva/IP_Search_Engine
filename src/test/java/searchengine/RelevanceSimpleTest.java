package searchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * RelevanceTFIDFTest
 */
public class RelevanceSimpleTest {

    String[] first = {"word1"};
    String[] second = {"word2"};
    IndexHash index;
    RelevanceSimple rlv;
    ArrayList<String> word1 = new ArrayList<>(){{
        add("word1");
        add("word2");
        add("word1");
    }};
    ArrayList<String> word2 = new ArrayList<>(){{
        add("word1");
        add("word3");
        add("word3");
    }};
    ArrayList<String> word3 = new ArrayList<>(){{
        add("word6");
        add("word5");
    }};
    ArrayList<Page> allPages = new ArrayList<>();


    @BeforeEach
    public void Setup(){
       
        var page1 = new Page("http://page1.com", "word1", this.word1);
        var page2 = new Page("http://page2.com", "title2", this.word2);
        var page3 = new Page("http://page3.com", "word6", this.word3);
        allPages.add(page1);
        allPages.add(page2);
        allPages.add(page3);
        index = new IndexHash(allPages);
        rlv = new RelevanceSimple(index.getwordsToPages());

}
    @AfterEach
    public void clear(){
        rlv.clearMap();
    }

    @Test
    void checkOneSimple(){ 
        var this1 = index.matchingPages("word2");
        rlv.calculatingRelevance(this1, first);
        var result = rlv.getMapOfRelevance();
        String showResult = "";
        for(var page : result.keySet())    {
            String url = page.getUrl();
            String title = page.getTitle();
            String words = page.getWords().toString();
            double relevance = result.get(page) ;
            showResult = showResult + "{ Url: " + url + ", Title: " + title + ", Words: " + words + ", Relevance: " + relevance + "}";
        }

    assertEquals("{ Url: http://page1.com, Title: word1, Words: [word1, word2, word1], Relevance: 4.0}", 
    showResult);
    }

    @Test
    void check2WordBlocksAndTheRlvSimple() {
        var this1 = index.matchingPages("word1");
        var this2 = index.matchingPages("word2");
        rlv.calculatingRelevance(this1, first);
        rlv.calculatingRelevance(this2, second);
        var result = rlv.getMapOfRelevance();
        String showResult = "";
        for(var page : result.keySet())    {
            String url = page.getUrl();
            String title = page.getTitle();
            String words = page.getWords().toString();
            double relevance = result.get(page) ;
            showResult = showResult + "{ Url: " + url + ", Title: " + title + ", Words: " + words + ", Relevance: " + relevance + "}";
        }

    assertEquals("{ Url: http://page2.com, Title: title2, Words: [word1, word3, word3], Relevance: 1.0}{ Url: http://page1.com, Title: word1, Words: [word1, word2, word1], Relevance: 4.0}", 
    showResult);
}
}