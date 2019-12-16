package searchengine;

import java.util.*;

/**
 * Class IndexHash - Stores all Page objects from the file in a HashMap
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class IndexHash implements Index {
    private HashMap<String, ArrayList<Page>> wordsToPages;
    private ArrayList<Page> allPages;

    /**
     * Creates a HashMap which stores all Page objects
     * @param allPages The ArrayList of Page objects of allPages passed from the FileReader class
     */
    public IndexHash(ArrayList<Page> allPages) {
        this.allPages = allPages;
        wordsToPages = new HashMap<>();
        makeHashMap();
    }
        /**
        * Converts the allPages ArrayList of Pages coming from the FileReader to a HashMap where the key is a String object and the value is ArrayList of Page objects called wordsToPages
        * @return return the wordsToPages HashMap
        */
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
         * Check if the map key matches the input paratemer and returns the value corresponding to the map key as an ArrayList of Page objects
         * @param input String input coming from the Query class representing a key of the wordsToPages map
         * @return  The values of the corresponding key as an ArrayList of Page objects
         */
    
        public ArrayList<Page> matchingPages(String input)   {
            ArrayList<Page> matchingPages = new ArrayList<Page>();
            if(wordsToPages.containsKey(input))  {
                matchingPages = wordsToPages.get(input);      
            }  
            return matchingPages;
        }
        /**
         * @return The ArrayList of all Page objects coming from the FileReader class
         */
        public ArrayList<Page> getAllPages()  {
            return allPages;
        }
        /**
         * @return the wordsToPage map contains of String as its keys and ArrayList of Page objects as its values.
         */
        public HashMap<String, ArrayList<Page>> getwordsToPages()   {
            return wordsToPages;
        }

}