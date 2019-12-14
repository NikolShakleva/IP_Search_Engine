package searchengine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class IndexArrayList - Stores all page objects from the file in an ArrayList<Page>
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class IndexListArray implements Index    {
    private HashMap<String, ArrayList<Page>> wordsToPages;
    private ArrayList<Page> allPages;

    /**
     * Stores all page object in an ArrayList<Page>
     * @param allPages The ArrayList<Page> of allPages passed from the FileReader class
     */
    public IndexListArray(ArrayList<Page> allPages)  {
        this.allPages = allPages;
        makeHashMap();
        
    }
    /** Return an ArrayList of page objects containing the input parameter
     * @param input Sring input coming from the Query class which represents a word  contained in a page object
     * @return An ArraList<Page> that matches the String input parameter
     */
    public ArrayList<Page> matchingPages(String input) {
        ArrayList<Page> matchingPages = new ArrayList<Page>();
        for (Page page: allPages){
            if(page.getWords().contains(input))
                matchingPages.add(page);
            }   
        return matchingPages;
    }

    /**
    * @return The ArrayList<Page> of all page objects coming from the FileReader class
    */
    public ArrayList<Page> getAllPages()    {
        return allPages;
    }

    public HashMap<String, ArrayList<Page>> makeHashMap() {
        for (Page page : allPages) {
            var words = page.getWords();
            for (String word : words) {
                if (!wordsToPages.containsKey(word)) {
                    var list = new ArrayList<Page>();
                    list.add(page);
                    wordsToPages.put(word, list);
                } else if (wordsToPages.containsKey(word)) {
                    var list = wordsToPages.get(word);
                    if (!list.contains(page)) {
                        list.add(page);
                    }
                }
            }
        }
        return wordsToPages;
    }
     /**
         * @return The ArrayList<Page> of all page objects coming from the FileReader class
         */
    public HashMap<String, ArrayList<Page>> getwordsToPages()   {
        return wordsToPages;
    }
   
    
}