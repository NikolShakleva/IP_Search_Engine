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
    //the method below is the helper one for the getKP method
     public ArrayList<Page> getKeyPages(String input)
     {
        
        ArrayList<Page> arrayOfDesiredPages = new ArrayList<>();
        ArrayList<Page> result = new ArrayList<>();
        String[] array = input.split("%20");

        for(String x : array)
        {
            for(Page arrayOfPages : index.matchingPages(x))
            {
            arrayOfDesiredPages.add(arrayOfPages);
            }
        }
        
        for(Page x :arrayOfDesiredPages)
        {
            int occurences = Collections.frequency(arrayOfDesiredPages, x);
            if(occurences==array.length&&!result.contains(x))
            {
                result.add(x);
            }
        } 
        return result;
     }

     public ArrayList<Page> getKP(String input)
     {
         ArrayList<Page> arrayOfDesiredPages = new ArrayList<>();
         String[] arrayOfOR = input.split("%20OR%20");
         for(String line : arrayOfOR)
         {
           ArrayList<Page> arrayOfPages = getKeyPages(line);
                    for(Page x : arrayOfPages)
                    {
                        if(!arrayOfDesiredPages.contains(x))
                        {
                        arrayOfDesiredPages.add(x);
                        }
                    }
         }
         return arrayOfDesiredPages;
         
     }


    
}