package searchengine;
//iterates accross library
//has a tostring method that completes the respond portion of the web server
import java.util.*;
public class Responder{
    private ArrayList<Page> webOutPut;

    public Responder(ArrayList<Page> correctPages){
        webOutPut = new ArrayList<Page>(correctPages);
       // this.webOutPut = correctPages;
       
    }
    
    
    public ArrayList<String> getPageNames(){
        ArrayList<String> pageNames= new ArrayList<String>(); 
        for (Page page: webOutPut){
            String theName = page.getName();
            pageNames.add(theName);
        }
        
        return pageNames;

    }



}