package searchengine;
import java.util.Comparator;
import java.util.*;

/**
 * Responder
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 * 
 */

public class Responder{
    private ArrayList<Page> correctPages;

    public Responder(ArrayList<Page> correctPages){
        this.correctPages = new ArrayList<Page>(correctPages);
    }
    /**
     * 
     * @return, an ArrayList with pages formatted with URL and Title for the server to display
     */
    public ArrayList<String> getPageNames(){
        ArrayList<String> response = new ArrayList<>(); 
        correctPages.sort(Comparator
        .comparing(Page::getRelevance));

        //

        
        for (Page page: correctPages){
            String url = page.getUrl();
            String title = page.getTitle();
            double relevance = page.getRelevance();
            response.add(String.format("{\"url\": \"%s\", \"title\": \"%s\", \"relevance\": \"%s\"}",
                                 url, title, relevance)); 
            page.resetRelevance();
            

         
        } return response;
    }
}