package searchengine;
import java.util.*;

/**
 * Class Page - is the blueprint of a page object.
 * It creates  page objects.
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
 * @param pageUrl Creates the page URL as a String
 * @param pageTitle Creates the page title as a String
 * @param words Creates a list of words contained in a page as ArrayList<String>
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
         * @return The words contained in a single page as ArrayList of Strings
         */
        public ArrayList<String> getWords() {
            return words;
        }
}