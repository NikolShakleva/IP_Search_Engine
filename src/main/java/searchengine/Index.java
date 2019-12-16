package searchengine;
import java.util.*;

/**
 * Index
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 */
public interface Index {

    public ArrayList<Page> matchingPages(String input);
    // method that finds matching pages from searchTerm
    public ArrayList<Page> getAllPages();
    // method that gets the allPages ArrayList of Pages
    public Map<String, ArrayList<Page>> getwordsToPages();
    //method that gets a Map with String words as a key and an ArrayList of pages as value
  
}