package searchengine;

import java.util.*;

/**
 * Class IndexTree - Stores all page objects from the file in a TreeMap<String,ArrayList<Page>>
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class IndexTree implements Index {

    private Map<String, ArrayList<Page>> wordsToPages;
    private ArrayList<Page> allPages;

     /**
     * Creates a TreeMap which stores all page objects
     * @param allPages The ArrayList<Page> of allPages passed from the FileReader class
     */
    public IndexTree(ArrayList<Page> allPages)  {
        this.allPages = allPages;
        wordsToPages = new TreeMap<>();
        makeTreeMap();
    }
    /**
    * Converts the allPages ArrayList from the FileReader to a TreeMap<String,ArrayList<Page>> called wordsToPages
    * @return return the wordsToPages TreeMap<String,ArrayList<Page>>
    */
    public Map<String, ArrayList<Page>> makeTreeMap() {
        for(Page page : allPages) {
            var words = page.getWords();
            for(String word : words)    {
                if(!wordsToPages.containsKey(word)){
                    var list = new ArrayList<Page>();
                    list.add(page);
                       wordsToPages.put(word, list);
                }
            } 
        }return wordsToPages;
    }

     /**
     * Check the mapKey matching the input paratemer and returns the value corresponding to the key as an ArrayList<Page>
     * @param input String input coming from the Query class representing a key of the wordsToPages map
     * @return  The values of the corresponding key as an ArrayList<Page>
     */
    public ArrayList<Page> matchingPages(String input)   {
        ArrayList<Page> matchingPages = new ArrayList<Page>();
       if(wordsToPages.containsKey(input))  {
        matchingPages = wordsToPages.get(input);   {
        }     
    }   return matchingPages;
    }

    /**
     * @return The ArrayList<Page> of all page objects coming from the FileReader class
     */
    public ArrayList<Page> getAllPages()  {
        return allPages;
    }

     /**
     * @return the TreeMap<String,ArrayList<Page>> mapping words to page objects
     */
    public Map<String, ArrayList<Page>> getwordsToPages()   {
        return wordsToPages;
    }
}
