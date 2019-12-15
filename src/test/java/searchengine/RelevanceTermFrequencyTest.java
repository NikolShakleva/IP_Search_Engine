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
public class RelevanceTermFrequencyTest {

    String[] first = {"word1"};
    String[] second = {"word2"};
    IndexHash index;
    RelevanceTermFrequency rlv;
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
        rlv = new RelevanceTermFrequency(index.getwordsToPages());

}
    @AfterEach
    public void clear(){
        rlv.clearMap();
    }

    @Test
    void checkOneTF(){ 
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

    assertEquals("{ Url: http://page1.com, Title: word1, Words: [word1, word2, word1], Relevance: 1.3333333333333333}", 
    showResult);
    }

    @Test
    void check2WordBlocksAndTheRlvTF() {
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

    assertEquals("{ Url: http://page2.com, Title: title2, Words: [word1, word3, word3], Relevance: 0.3333333333333333}{ Url: http://page1.com, Title: word1, Words: [word1, word2, word1], Relevance: 1.3333333333333333}", 
    showResult);
}
}