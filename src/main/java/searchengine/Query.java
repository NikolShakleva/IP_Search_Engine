package searchengine;
import java.util.*;

/**
 * Query
 * 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 */

public class Query {
    private Index index;
    private HashMap<Page, Double> sorted;
    private Relevance relevance;

    public Query(String input, Index index, Relevance relevance)  {
        this.index = index;
        this.relevance = relevance;
        splitSearch(input);    
    }
        /**
         * 
         * @param searchTerm
         */
        public void splitSearch(String searchTerm)  {
            var searchTermsGrouped = new ArrayList<String[]>();
            String[] arrayOfOR = searchTerm.split("%20OR%20");
            for(String array : arrayOfOR) {
                String[] arrayPairingWords = array.split("%20");
                searchTermsGrouped.add(arrayPairingWords); 
            }
            getKeyPages(searchTermsGrouped);
        }
        /**
         * 
         * @param searchTermsGrouped
         */
        public void getKeyPages(ArrayList<String[]> searchTermsGrouped) {
            for(String[] words : searchTermsGrouped)    {
                ArrayList<Page> matchingPagesfromIndex = new ArrayList<>();
                ArrayList<Page> matchesAllWords = new ArrayList<>();
                for(String word : words) {
                    word.toLowerCase();
                    matchingPagesfromIndex.addAll(index.matchingPages(word));
                }
                for(Page page : matchingPagesfromIndex) {
                    int occurences = Collections.frequency(matchingPagesfromIndex, page);
                    if(occurences==words.length&&!matchesAllWords.contains(page))   {  
                        matchesAllWords.add(page);
                    }          
                } 
                relevance.calculatingRelevance(matchesAllWords, words);  
                } 
                sorted = new HashMap<>(relevance.getMapOfRelevance()); 
                relevance.clearMap();
        }
        /**
         * 
         * @return
         */
        public HashMap<Page, Double> getCorrectPages()  {
            return sorted;
        }
}