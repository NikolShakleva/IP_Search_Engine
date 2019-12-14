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

    /**
     *@return The ArrayList<Page> of all page objects coming from the FileReader class
     
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
        } return wordsToPages;
    }

     /**
         * @return The ArrayList<Page> of all page objects coming from the FileReader class
         */
    public HashMap<String, ArrayList<Page>> getwordsToPages()   {
        return wordsToPages;
    }

    @Override
    public ArrayList<Page> matchingPages(String input) {
        return null;
    }
   
    
}