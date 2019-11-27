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
    private String url;
    private String title;
    private ArrayList<String> words;

    public Page(String pageUrl, String pageTitle){

        this.title = pageTitle;
        this.url = pageUrl;
    }
    public void replaceWords(List list) {
        words.addAll(list);
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

}