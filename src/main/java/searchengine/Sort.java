package searchengine;
import java.util.*;

public class Sort{

    public Sort(ArrayList<Page> matchesAllWords, String [] words){
        
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

}
