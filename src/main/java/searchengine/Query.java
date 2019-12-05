package searchengine;
import java.util.*;

/**
 * Query
 * 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 */

public class Query {
   // private ArrayList<String> wrongWords;
    private ArrayList<Page> relevantPages;
    private Index index;
     //private String filename;

    public Query(String input, Index index){
        this.index = index;
        relevantPages = getKeyPages(input);
        //this.filename = input;
       
    }
    /**
     *
     * @return, a List with all the matching pages from the searchword returned from the Library
     */
    public ArrayList<Page> getCorrectPages(){
        return this.relevantPages;

    }

    /**
     *
     * @return, a List with all the matching pages that contain all of the words that the string contains
     */
    //the method below is the helper one for the getKeyPages method
     public ArrayList<Page> getKeyPagesHelper(String input)
     {
        
        ArrayList<Page> arrayOfSelectedPages = new ArrayList<>();
        ArrayList<Page> result = new ArrayList<>();
        String[] array = input.split("%20");

        //the loop selects pages that have at least of of the given key word
        for(String x : array)
        {
            for(Page item : index.matchingPages(x))
            {
            //if(arrayOfSelectedPages!=contain(item))
            item.increaseRelevance(x);
            arrayOfSelectedPages.add(item);
            }
        }
        //the loop selects pages that contain each of the given key word
        for(Page x :arrayOfSelectedPages)
        {
            int occurences = Collections.frequency(arrayOfSelectedPages, x);
            if(occurences==array.length&&!result.contains(x))
            {
                
                result.add(x);
            }
            else
            {
                x.resetRelevance();
            }
        } 
        return result;
     }

     public ArrayList<Page> getKeyPages(String input)
     {
         ArrayList<Page> arrayOfSelectedPages1 = new ArrayList<>();
         String[] arrayOfOR = input.split("%20OR%20");
         for(String line : arrayOfOR)
         {
           ArrayList<Page> arrayOfPages = getKeyPagesHelper(line);
                    for(Page x : arrayOfPages)
                    {
                        if(!arrayOfSelectedPages1.contains(x))
                        {
                        
                        arrayOfSelectedPages1.add(x);
                        }
                    }
         }
         return arrayOfSelectedPages1;
         
     }


    
}