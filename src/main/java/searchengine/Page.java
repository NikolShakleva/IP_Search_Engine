package searchengine;
import java.util.*;

public class Page{
    private int relevance;
    private ArrayList<String> words;
    private String name;
    private String title;

    public Page(String pageName, String pageTitle, ArrayList<String> pageWords){
        this.words = pageWords;
        this.title = pageTitle;
        this.name = pageName;
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
    public String getName(){
        return this.name;
    }
    public String getTitle(){
        return this.title;
    }
    public int getRelevance(){
    return this.relevance;
    }
}