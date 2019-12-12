package searchengine;
import java.util.*;

/**
 * Library
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 */
public interface Index {

    public ArrayList<Page> matchingPages(String input);
    public ArrayList<Page> getAllPages();
    public Map<String, ArrayList<Page>> getwordsToPages();
    //public Set<String> returnKeySet();
}