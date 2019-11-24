package searchengine;
import java.util.*;

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

    public boolean check(String input){
        if (words.contains(input)){
            this.relevance++;
            return true;   
        }else return false;
       }
       public ArrayList<String> getWords()  {
           return words;
       }
    public String getUrl(){
        return this.url;
    }
    public String getTitle(){
        return this.title;
    }
    public int getRelevance(){
    return this.relevance;
    }
}