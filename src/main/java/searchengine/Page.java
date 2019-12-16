package searchengine;
import java.util.*;

/**
 * Class Page - creates Page objects. A page object is a website which contains URL, title and ArrayList of words
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class Page   {
    private String url;
    private String title;
    private ArrayList<String> words;

/**
 * Create a Page object based on the paramerts passed to the constructor
 * @param pageUrl Creates the Page URL as a String
 * @param pageTitle Creates the Page title as a String
 * @param words Creates a list of words contained in a Page as ArrayList of String
 */
    public Page(String pageUrl, String pageTitle, ArrayList<String> words){
        this.title = pageTitle;
        this.url = pageUrl;
        this.words = words;
    }
       /** 
        * @return The Pages' URL as a String
        */
        public String getUrl()  {
            return this.url;
        }

        /**
         * @return The Page Title as a String
         */
        public String getTitle()    {
            return this.title;
        }

        /** 
         * @return The words contained in a single Page as ArrayList of Strings
         */
        public ArrayList<String> getWords() {
            return words;
        }
}