package searchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;



public class QueryTest{
    IndexHash index;
    RelevanceSimple rlv;
    ArrayList<Page> allPages = new ArrayList<>();
    Responder responder;
    ArrayList<String> words1 = new ArrayList<>();
    ArrayList<String> words2 = new ArrayList<>();
    ArrayList<String> words3 = new ArrayList<>();
   
    @BeforeEach
    public void Setup(){
        words1.add("Denmark");
        words1.add("Queen");
        words1.add("Copenhagen");

        words2.add("Denmark");
        words2.add("Queen");

        words3.add("Denmark");
    
       
        var page1 = new Page("http://page1.com", "title1", this.words1);
        var page2 = new Page("http://page2.com", "title2", this.words2);
        var page3 = new Page("http://page3.com", "title3", this.words3);
        allPages.add(page1);
        allPages.add(page2);
        allPages.add(page3);
        index = new IndexHash(allPages);
        rlv = new RelevanceSimple(index.getwordsToPages());
    }



    @Test

    void checkAnds(){
    
    Query testQuery = new Query("Denmark%20Queen%20Copenhagen", index, rlv);
    HashMap<Page,Double> returned = testQuery.getCorrectPages();
    int pagesReturned = returned.size();

    assertEquals(1, pagesReturned);

}

@Test

void checkOr(){

Query testQuery = new Query("Denmark%20Queen%20Copenhagen%20OR%20Denmark%20Queen", index,rlv);
HashMap<Page,Double> returned = testQuery.getCorrectPages();
int pagesReturned = returned.size();

assertEquals(2, pagesReturned);



}

@Test

public void checkNoMatch(){
    Query testQuery = new Query("jjjyyyxxx", index,rlv);
    HashMap<Page,Double> returned = testQuery.getCorrectPages();
    int pagesReturned = returned.size();

    assertEquals(0, pagesReturned);


}


}



