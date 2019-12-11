package searchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RelevanceSimple implements Relevance {

    private HashMap<String, HashMap<Page, Double>> wordsrelevanceMap;//map of all of the words (as keywords)
    private HashMap<Page, Double> sort;

    public RelevanceSimple(HashMap<String, ArrayList<Page>> param)//webserver sends this map after retrieving this form the index
    {
        wordsrelevanceMap = new HashMap<>();
        sort = new HashMap<>();
        makeRelevanceMap(param);
        
    }
    public void makeRelevanceMap(HashMap<String, ArrayList<Page>> mapFromIndex) //calculates the simple relevance for each word for each page
    {
        for(String word : mapFromIndex.keySet())
        {
            HashMap<Page, Double> hashmapRelevanceValue = new HashMap<>();
            for(Page page : mapFromIndex.get(word))
            {
                ArrayList<String> words = page.getWords();
                double wordRelevence = Collections.frequency(words, word);
                hashmapRelevanceValue.put(page, wordRelevence);
            }
            wordsrelevanceMap.put(word, hashmapRelevanceValue);
        }
    }

    public void calculatingRelevance(ArrayList<Page> matchesAllWords,String[] words )
    {
     // we loop over the words and then we retrieve the relevance of the word and we put it to the map
       HashMap<Page, Double> currentSearchString = new HashMap<>();
       for(String word : words) {  
            HashMap <Page, Double> currentwords = wordsrelevanceMap.get(word);
            for(Page page : matchesAllWords)
            if(!currentSearchString.containsKey(page))  {
                Double rlv = currentwords.get(page);
                currentSearchString.put(page, rlv);
            }else   {
                if(currentSearchString.containsKey(page)) {
                    double rlv = currentSearchString.get(page);
                    rlv = rlv + currentSearchString.get(page);
                    currentSearchString.put(page, rlv); 
            }

               
                }
            }
         for(var page : currentSearchString.keySet())  {
            double relevanceCurrent = currentSearchString.get(page);
            if(sort.containsKey(page))  {
                double relevanceSort = sort.get(page);
                if(relevanceCurrent > relevanceSort)  {
                    sort.put(page, relevanceCurrent);
                }
            } else {
                sort.put(page,relevanceCurrent);
        } 
    }
}
                
            

    public HashMap<Page, Double> getMapOfRelevance()    {
        return sort;
    }

    public void clearMap()  {
        sort.clear();
    }
}