package searchengine;
import java.util.*;

/**
 *
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
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
       // System.out.println(words);
    }
    /**
     * 
     * @return, Page URL
     */
    public String getUrl(){
        return this.url;
    }
    /**
     * 
     * @return, Page Title
     */
    public String getTitle(){
        return this.title;
    }
    /**
     * 
     * @return Page Relevance
     */
    public int getRelevance(){
    return this.relevance;
    }
    /**
     * 
     * @return an ArrayList of words
     */
    public ArrayList<String> getWords()  {
        return words;
    }

        /* USE FOR LATER
    
    public boolean check(String input){
        if (words.contains(input)){
            this.relevance++;
            return true;   
        }else return false;
       }*/

}