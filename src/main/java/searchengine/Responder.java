package searchengine;
//iterates accross library
//has a tostring method that completes the respond portion of the web server
import java.util.*;
public class Responder{
    private ArrayList<Page> correctPages;

    public Responder(ArrayList<Page> correctPages){
        this.correctPages = new ArrayList<Page>(correctPages);
       // this.webOutPut = correctPages;
       
    }
    
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