package searchengine;
import java.util.*;

/**
 * Query
 */
public class Query {
   // private ArrayList<String> wrongWords;
    private ArrayList<Page> relevantPages;
    private Library library;

    public Query(String input, Library library){
        this.library = library;
        relevantPages = library.matchingPages(input);
       
    }

    public ArrayList<Page> getCorrectPages(){
        return this.relevantPages;

    }


    
}