package searchengine;
import java.util.*;

/**
 * Class Query -  It takes String input from the users of the WebServer.
 * It retrieves the pages matching matching the input String with their relevant relevance score 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */

public class Query {
    private Index index;
    private HashMap<Page, Double> sorted;
    private Relevance relevance;
/**
 *  Creates a query object and splits the user input into a single String input
 * @param input String search term coming from the user of the Webserver
 * @param index The database storing all page object 
 * @param relevance The dataase storing the relevance of each page object
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
        public void splitSearch(String searchTerm)  {  //splits first by or, then by whitespaces, creating lists of strings for each part of the or split
            var searchTermsGrouped = new ArrayList<String[]>();
            String[] arrayOfOR = searchTerm.split("%20OR%20");//splits by or, storing in a list of strings
            for(String array : arrayOfOR) { //each string correlating to each part of the or split further gets split by whitespace
                String[] arrayPairingWords = array.split("%20");
                searchTermsGrouped.add(arrayPairingWords); //each list of strings split by whitespaces gets sent to getKeyPages, to match it against the index
            }                                              //each list of strings split by whitespace exists in the greater or split, such as to keep association with that part of the or split
            getKeyPages(searchTermsGrouped);               //each part of the or split gets sent into getKeyPages separately 
        }
        /**
         *  Creates a map of pages which match every word of the user input to a page with a certain relevance score
         * @param searchTermsGrouped array which holds the user input split by single word
         */
        public void getKeyPages(ArrayList<String[]> searchTermsGrouped) { 
            for(String[] words : searchTermsGrouped)    { //taking in the arraylist of strings for each part of the or split, they get iterated through, each word being used as a key to return matching pages
                ArrayList<Page> matchingPagesfromIndex = new ArrayList<>();
                ArrayList<Page> matchesAllWords = new ArrayList<>();
                for(String word : words) {
                    word.toLowerCase();
                    matchingPagesfromIndex.addAll(index.matchingPages(word)); //adds pages that exist as values for every single word in that split
                }
                for(Page page : matchingPagesfromIndex) {  //counts the number of times a page appears in matching pages. If it matches the number of words in that part of the split, 
                    int occurences = Collections.frequency(matchingPagesfromIndex, page);//it moves forward and gets stored in matchesallwords since its been returned for every word
                    if(occurences==words.length&&!matchesAllWords.contains(page))   {  
                        matchesAllWords.add(page);
                    }          
                } 
                relevance.calculatingRelevance(matchesAllWords, words); //sends all the matching pages to the relevance class 
                } 
                sorted = new HashMap<>(relevance.getMapOfRelevance()); //retrieves the now sorted pages to store locally 
                relevance.clearMap();//clears relevance to prevent from conflicting with previous searches
        }
        /**
         * 
         * @return the map holding the pages with their corresponding relevance score matching the serach input
         */
        public HashMap<Page, Double> getCorrectPages()  { //sends sorted to the web server when called upon
            return sorted;
        }
}