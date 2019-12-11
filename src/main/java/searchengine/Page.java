package searchengine;
import java.util.*;

/**
 * Class Page - is the blueprint of a page object.
 * It describes all companents f a page object
 * It calculates the Simple count score relevance of a word in a page
 * It calculates the TF (term frequency) relevance of a word in a page
 * It calculates the TFIDF (term frequency - inverted document frequency) relevance of a word in a page
 * 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class Page   {
    private String url;
    private String title;
    private ArrayList<String> words;

/**
 * Create a page object based on the paramerts passed to the constructor
 * It creates a map calculating the term-frequency relevance of each word in the page objects
 * @param pageUrl Creates the page URL
 * @param pageTitle Creates the page title
 * @param words Creates a list of words contained in a page
 */
    public Page(String pageUrl, String pageTitle, ArrayList<String> words){
        this.title = pageTitle;
        this.url = pageUrl;
        this.words = words;
    }
       /**
        * @return The pages' URL as a String
        */
        public String getUrl()  {
            return this.url;
        }

        /**
         * @return The page Title as a String
         */
        public String getTitle()    {
            return this.title;
        }

        /**
         * @return The ArayList of words contained in a single page
         */
        public ArrayList<String> getWords() {
            return words;
        }
}