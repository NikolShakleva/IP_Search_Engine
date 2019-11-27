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
    private List<Page> allPages;

    public Index(Map<String, ArrayList<Page>> wordsToMap, ArrayList<Page> allPages){

        this.wordsToMap = new HashMap<>(wordsToMap);
        this.allPages = new ArrayList<>(allPages);
        //this.pages = fromReader;
    }
    
    /**
     * Matches the searchTerm to all the pages stored in the Library
     * and creates a List of Matching Pages.
     * 
     * @param input, searchTerm from user
     * @return an ArrayList with matching pages to the serchTerm
     */
    public ArrayList<Page> matchingPagesMap(String input){
        ArrayList<Page> matchingPages = new ArrayList<Page>();
        matchingPages = wordsToMap.get(input);
            
        return matchingPages;
    }

    public ArrayList<Page> matchingPagesList (String input) {
        ArrayList<Page> matchingPages = new ArrayList<Page>();
        for (Page page: allPages){
            if(page.getWords().contains(input))
                matchingPages.add(page);
            }
            
        return matchingPages;
    }
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