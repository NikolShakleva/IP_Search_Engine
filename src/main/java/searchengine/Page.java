package searchengine;
import java.util.*;

/**
 * Page
 * 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class Page{
    private int relevance;
    private ArrayList<String> words;
    private String url;
    private String title;

    public Page(String pageUrl, String pageTitle, ArrayList<String> pageWords){
        this.words = pageWords;
        this.title = pageTitle;
        this.url = pageUrl;
        relevance = 0;
    }
    /**
     * 
     * 
     */
    public boolean check(String input){
        if (words.contains(input)){
            this.relevance++;
            return true;   
        } else return false;
       }
       /**
        * 
        * @return, an ArrayList with words contained in the page
        */
        public ArrayList<String> getWords()  {
           return words;
       }
       /**
        * 
        * @return, the pages' URL as a String
        */
        public String getUrl()  {
            return this.url;
        }
        /**
         * 
         * @return, the page Title as a String
         */
        public String getTitle()    {
            return this.title;
        }
        /**
         * 
         * @return, the pages' relevance as an int
         */
        public int getRelevance()   {
            return this.relevance;
        }
}