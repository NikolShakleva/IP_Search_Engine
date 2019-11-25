package searchengine;
import java.util.*;

/**
 * Library
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 */
public class Library {
    private ArrayList<Page> allPages;

    public Library(ArrayList<Page> allPages){
        this.allPages = new ArrayList<Page>(allPages);
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
        for (Page page: allPages){
            if(page.getWords().contains(input))
                matchingPages.add(page);
            }
            
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