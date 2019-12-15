package searchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * RelevanceTFIDFTest
 */
public class RelevanceTFIDFTest {

    String[] first = {"word1", "word1"};
    String[] second = {"word3", "word2"};
    FileReader fr;
    IndexHash index;
    RelevanceTFIDF rlv;


    @BeforeAll
    public void Setup(){
        fr = new FileReader("test-file.txt");
        index = new IndexHash(fr.getAllPages());
        rlv = new RelevanceTFIDF(index.getwordsToPages(), index);
    }
    @AfterEach
    public void clear(){
        rlv.clearMap();
    }

    @Test
    void checkOne(){ 
    }

    @Test
    void check2WordBlocksAndTheRlv() {
        for(int i = 0 ; i > first.length; i++){
            var matches = index.matchingPages(first[i]);
            rlv.calculatingRelevance(matches, first);
        }
        for(int i = 0 ; i > second.length; i++){
            var matches = index.matchingPages(first[i]);
            rlv.calculatingRelevance(matches, first);
    }   var result = rlv.getMapOfRelevance().toString();

    assertEquals("word1", result);
}
}