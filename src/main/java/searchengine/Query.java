package searchengine;
import java.util.*;

/**
 * Query
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 */
public class Query {

    private ArrayList<Page> relevantPages;
    private Library library;

    public Query(String input, Library library){
        this.library = library;
        relevantPages = library.matchingPages(input);
    }

    /**
     * 
     * @return, ArrayList of pages matching the search Term
     */

    public ArrayList<Page> getCorrectPages()    {
        return this.relevantPages;
    }


    
}