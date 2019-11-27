package searchengine;
import java.util.*;

/**
 * Library
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 */
public class Index {
    private Map<String, ArrayList<Page>> wordsToMap;

    public Index(Map<String, ArrayList<Page>> wordsToMap){

        this.wordsToMap = new HashMap<>(wordsToMap);
        //this.pages = fromReader;
    }
    /**
     * Matches the searchTerm to all the pages stored in the Library
     * and creates a List of Matching Pages.
     * 
     * @param input, searchTerm from user
     * @return an ArrayList with matching pages to the serchTerm
     */
    public ArrayList<Page> matchingPages(String input){
        ArrayList<Page> matchingPages = new ArrayList<Page>();
        matchingPages = wordsToMap.get(input);
            
        return matchingPages;
    }
    /**
     * Adds Pages to the Library
     * 
     * @param x
     */
   /* public void addPage(Page x){
        allPages.add(x);
    }*/
    
}