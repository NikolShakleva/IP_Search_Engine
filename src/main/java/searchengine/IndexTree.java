package searchengine;

import java.util.*;

/**
 * Class IndexTree - Stores all page objects from the file in a TreeMap where the key is String and the value is ArrayList of Page objects
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class IndexTree implements Index {

    private Map<String, ArrayList<Page>> wordsToPages;
    private ArrayList<Page> allPages;

     /**
     * Creates a TreeMap which stores all page objects
     * @param allPages The ArrayList of Page objects of allPages passed from the FileReader class
     */
    public IndexTree(ArrayList<Page> allPages)  {
        this.allPages = allPages;
        wordsToPages = new TreeMap<>();
        makeTreeMap();
    }
        /**
        * Converts the allPages ArrayList from the FileReader to a TreeMap called wordsToPages
        * @return return the wordsToPages TreeMap
        */
        public Map<String, ArrayList<Page>> makeTreeMap() {
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
        * Check the mapKey matching the input paratemer and returns the value corresponding to the key as an ArrayList of Page object
        * @param input String input coming from the Query class representing a key of the wordsToPages map
        * @return  The values of the corresponding key as an ArrayList of Page objects
        */
        public ArrayList<Page> matchingPages(String input)   {
            ArrayList<Page> matchingPages = new ArrayList<Page>();
            if(wordsToPages.containsKey(input))  {
                matchingPages = wordsToPages.get(input);   
            } return matchingPages;
        }

        /**
        * @return The ArrayList of Page objects of all page objects coming from the FileReader class
        */
        public ArrayList<Page> getAllPages()  {
            return allPages;
        }

        /**
        * @return TreeMap mapping words to page objects
        */
        public Map<String, ArrayList<Page>> getwordsToPages()   {
            return wordsToPages;
        }
}
