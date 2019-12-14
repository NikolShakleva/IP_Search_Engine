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
    public void makeRelevanceMap(Map<String, ArrayList<Page>> map);
    public Map<Page, Double> getMapOfRelevance();
    public void clearMap();
    
}
