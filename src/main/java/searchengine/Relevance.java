package searchengine;
import java.util.*;
/** 
 * 
 * Interface
 * 
 * Calculating relevance for pages that fits the searchterm.
 * 
*/
public interface Relevance{

        
    public void calculatingRelevance(ArrayList<Page> matchesAllWords,String[] words);
    // calculates the relevance to the specific pages based on search word(s)
    public void makeRelevanceMap(Map<String, ArrayList<Page>> map);
    // calculating the relevance for each word in each page and stores the information in a map of <String, HashMap<Page, Double>>
    public Map<Page, Double> getMapOfRelevance();
    // getting the relevance map of <String, HashMap<Page, Double>>
    public void clearMap();
    //Clears the local sorted map after it has been retrived
    
}
