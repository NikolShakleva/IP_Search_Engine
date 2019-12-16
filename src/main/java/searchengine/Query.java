package searchengine;
import java.util.*;

/**
 * Class Query -  It takes String input from the users of the WebServer.
 * It retrieves the pages matching the input String with their relevance score 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */

public class Query {
    private Index index;
    private HashMap<Page, Double> sorted;
    private Relevance relevance;
/**
 *  Creates a Query object and splits the user input into a single String input
 * @param input String search term coming from the user of the Webserver
 * @param index The database storing all Page objects 
 * @param relevance The dataase storing the relevance of each Page object
 */
    public Query(String input, Index index, Relevance relevance)  {
        this.index = index;
        this.relevance = relevance;
        splitSearch(input);    
    }
        /** 
         * Creates an array which holds the user input split by single word.
         * @param searchTerm The search input coming from the user of the WebServer
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
         * Fills up a map of pages which match every word of the user input to a page with a certain relevance score
         * @param searchTermsGrouped array which holds the user input split by single word
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
         * @return the map holding the Page objects with their corresponding relevance score matching the serach input
         */
        public HashMap<Page, Double> getCorrectPages()  {
            return sorted;
        }
}