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
        relevantPages = getKP(input);
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

     public ArrayList<Page> getKeyPages(String input)
     {
        
        ArrayList<Page> arr = new ArrayList<>();
        ArrayList<Page> result = new ArrayList<>();
        String[] array = input.split("%20");

        for(String x : array)
        {
            for(Page xxx : index.matchingPages(x))
            {
            arr.add(xxx);
            }
        }
        
        for(Page x :arr)
        {
            int occurences = Collections.frequency(arr, x);
            if(occurences==array.length&&!result.contains(x))
            {
                result.add(x);
            }
        } 
        return result;
     }

     public ArrayList<Page> getKP(String input)
     {
         ArrayList<Page> arr = new ArrayList<>();
         String[] arrOR = input.split("%20OR%20");
         for(String line : arrOR)
         {
           ArrayList<Page> xxx = getKeyPages(line);
                    for(Page x : xxx)
                    {
                        if(!arr.contains(x))
                        {
                        arr.add(x);
                        }
                    }
         }
         return arr;
         
     }


    
}