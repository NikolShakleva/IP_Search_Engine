package searchengine;
import java.util.*;

/**
 * Query
 * 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 */

public class Query {
    //private ArrayList<Page> relevantPages;
    private Index index;
    private HashMap<Page, Double> sort = new HashMap<>();

    public Query(String input, Index index){
        this.index = index;
        splitSearch(input.toLowerCase());
       
    }
 
    public HashMap<Page, Double> getCorrectPages(){
        return sort;
    }
    public void splitSearch(String searchTerm)  {
        var searchTermsGrouped = new ArrayList<String[]>();
        String[] arrayOfOR = searchTerm.split("%20OR%20");
        for(String array : arrayOfOR) {
            String[] arrayPairingWords = array.split("%20");
            searchTermsGrouped.add(arrayPairingWords); 
        }
        getKeyPages(searchTermsGrouped);
     }

     public HashMap<Page, Double> getKeyPages(ArrayList<String[]> searchTermsGrouped) {
        
        ArrayList<Page> matchingPagesfromIndex = new ArrayList<>();
        ArrayList<Page> matchesAllWords = new ArrayList<>();

        for(String[] words : searchTermsGrouped)    {
            for(String word : words) {
            matchingPagesfromIndex.addAll(index.matchingPages(word));
            }
             for(Page page : matchingPagesfromIndex) {
                int occurences = Collections.frequency(matchingPagesfromIndex, page);
                if(occurences==words.length&&!matchesAllWords.contains(page))   {  
                    matchesAllWords.add(page);
                }          
            } sortPages(matchesAllWords, words);  
      } 
        return sort;
     }


     public void sortPages(ArrayList<Page> matchesAllWords,String[] words )    {
     
        for(Page page : matchesAllWords)  {
            if(sort.containsKey(page))  {
                double currentRlv = sort.get(page);
                double possibleRlv = 0;
                for(String word : words)    {
                    double rlv = page.getRelevanceTF(word);
                    possibleRlv += rlv;
                    }
                    if (currentRlv < possibleRlv)  {
                        sort.put(page, possibleRlv);
                        }
            } else {
                double currentRlv = 0;
                for(String word : words)    {
                    double rlv = page.getRelevanceTF(word);
                    currentRlv += rlv;
                    sort.put(page, currentRlv);
                }
            }
        }
    }

     








     /*public ArrayList<Page> getKeyPages(String input)   {
         ArrayList<Page> pagesToReturn = new ArrayList<>();
         String[] arrayOfOR = input.split("%20OR%20");
         for(String line : arrayOfOR)
         {
           ArrayList<Page> matchesAllWords = getKeyPagesHelper(line);
                    for(Page page : matchesAllWords)   {
                        if(!pagesToReturn.contains(page))   {  
                            pagesToReturn.add(page);
                        }
                    }
         }  return pagesToReturn;  
     }*/ 
}