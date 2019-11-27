package searchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.*;




public class PageTest{


    @Test

    public void retrievePageTitle(){
        ArrayList<String> words = new ArrayList<String>();
        words.add("demoWord");
        Page page1 = new Page("demoURL","demoTitle");
        String title = page1.getTitle();
        assertEquals("demoTitle", title);
    }

    @Test

    public void retrievePageURL(){
        ArrayList<String> words = new ArrayList<String>();
        words.add("demoWord");
        Page page1 = new Page("demoURL","demoTitle");
        String url = page1.getUrl();
        assertEquals("demoURL", url);
    }

    @Test

    public void retrieveAllWords(){
        ArrayList<String> words = new ArrayList<String>();
        words.add("demoWord");
        words.add("demoWord2");
        Page page1 = new Page("demoURL","demoTitle");
        ArrayList<String> AllWords = page1.getWords();
        assertEquals(words, AllWords);
    }

}