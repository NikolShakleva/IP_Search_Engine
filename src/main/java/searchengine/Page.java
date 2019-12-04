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
    private int relevance;


    public Page(String pageUrl, String pageTitle, ArrayList<String> words){
        this.title = pageTitle;
        this.url = pageUrl;
        this.words = words;
        relevance = 0;

    }
    public void replaceWords(ArrayList<String> list) {
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

        public ArrayList<String> getWords() {
            return words;
        }


        public void increaseRelevance(String x)
        {
            int occurences = Collections.frequency(words, x);
            this.relevance+=occurences;
        }

        public int getRelevance()
        {
            return relevance;
        }

        public void resetRelevance()
        {
            this.relevance = 0;
        }




}