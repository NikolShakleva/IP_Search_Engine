package searchengine;
import java.util.*;

/**
 * Query
 * 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 */

public class Query {
   // private ArrayList<String> wrongWords;
    private ArrayList<Page> relevantPages;
    private Index index;

    public Query(String input, Index index){
        this.index = index;
        relevantPages = index.matchingPagesMap(input);
       
    }
    /**
     *
     * @return, a List with all the matching pages from the searchword returned from the Library
     */
    public ArrayList<Page> getCorrectPages(){
        return this.relevantPages;

    }


    
}