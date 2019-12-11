package searchengine;
import java.util.*;

public interface Relevance{

    
    public void calculatingRelevance(ArrayList<Page> matchesAllWords,String[] words);
    public void makeRelevanceMap(HashMap<String, ArrayList<Page>> map);
    public HashMap<Page, Double> getMapOfRelevance();
    

    

}
