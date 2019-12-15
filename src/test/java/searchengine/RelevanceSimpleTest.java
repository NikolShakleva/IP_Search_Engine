package searchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * RelevanceTFIDFTest
 */
public class RelevanceSimpleTest {

    ArrayList<String> word1 = new ArrayList<>();
    ArrayList<String> word2 = new ArrayList<>();
    ArrayList<String> word3 = new ArrayList<>();

    String[] first = {"word1"};
    String[] second = {"word2"};
    String[] third = {"word1", "word2"};
    String[] fourth = {"word1", "word3"};

    IndexTree index;
    RelevanceSimple rlv;
    ArrayList<Page> allPages = new ArrayList<>();
    Responder responder;
    Query query;

    public RelevanceSimpleTest()    {
        word3.add("word6");
        word3.add("word5");
        word2.add("word1");
        word2.add("word3");
        word2.add("word3");
        word1.add("word1");
        word1.add("word2");
        word1.add("word1");

    }
    @BeforeEach
    public void Setup(){
       
        var page1 = new Page("http://page1.com", "word1", this.word1);
        var page2 = new Page("http://page2.com", "title2", this.word2);
        var page3 = new Page("http://page3.com", "word6", this.word3);
        allPages.add(page1);
        allPages.add(page2);
        allPages.add(page3);
        index = new IndexTree(allPages);
        rlv = new RelevanceSimple(index.getwordsToPages());
        query = new Query("word1 word2 OR word2 word3", index, rlv);
        

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
        rlv.calculatingRelevance(this2, second);
        rlv.calculatingRelevance(this1, first);
        var result = rlv.getMapOfRelevance();
        responder = new Responder(result);
        var resultOrderd = responder.getPageNames();


    assertEquals(("[{\"url\": \"http://page1.com\", \"title\": \"word1\", \"relevance\": \"4.0\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}, {\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"1.0\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]"),
    resultOrderd.toString());
}

@Test
void checkaWithORSimple() {
    ArrayList<String[]> list = new ArrayList<>();
    list.add(third);
    list.add(fourth);
    query.getKeyPages(list);
    var pages = query.getCorrectPages();
    ArrayList<String> result = new ArrayList<>();
    for(var page : pages.keySet())  {
        double currentRelevance = pages.get(page);
        String url = page.getUrl();
        result.add(url + " = Relevance: " + currentRelevance);
    }
    Collections.sort(result);
    assertEquals("[http://page1.com = Relevance: 8.0, http://page2.com = Relevance: 2.0]",
        result.toString());

}
}