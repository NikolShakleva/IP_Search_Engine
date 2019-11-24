package searchengine;
import java.util.*;

/**
 * 
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 * 
 */

public class Responder{
    private ArrayList<Page> correctPages;

    public Responder(ArrayList<Page> correctPages){
        this.correctPages = new ArrayList<Page>(correctPages);
       // this.webOutPut = correctPages;
       
    }
    /**
     * 
     * 
     * @return, an ArrayList with pages formatted with URL and Title for the server to display
     */
    public ArrayList<String> getPageNames(){
        ArrayList<String> response = new ArrayList<>(); 
        for (Page page: correctPages){
            String url = page.getUrl();
            String title = page.getTitle();
            response.add(String.format("{\"url\": \"%s\", \"title\": \"%s\"}",
                                 url.substring(6), title));


        }
        
        return response;

    }



}