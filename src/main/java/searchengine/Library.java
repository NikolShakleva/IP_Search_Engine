package searchengine;
import java.util.*;

/**
 * Library
 */
public class Library {
    private ArrayList<Page> allPages;

    public Library(ArrayList<Page> allPages){
        this.allPages = new ArrayList<Page>(allPages);
        //this.pages = fromReader;
    }
    /**
     * 
     * 
     * @param input
     * @return
     */
    public ArrayList<Page> matchingPages(String input){
        ArrayList<Page> matchingPages = new ArrayList<Page>();
        for (Page page: allPages){
            if(page.getWords().contains(input))
                matchingPages.add(page);
            }
            
        return matchingPages;
    }

    public void addPage(Page x){
        allPages.add(x);
    }
    /**
     * 
     * @return
     */
    public ArrayList<Page> getRelevantPages(){
        return this.allPages;
        
    }


   
}