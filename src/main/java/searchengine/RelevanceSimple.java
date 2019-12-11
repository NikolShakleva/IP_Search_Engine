package searchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RelevanceSimple implements Relevance {

    private HashMap<String, HashMap<Page, Double>> wordsrelevanceMap;//map of all of the words (as keywords)
    HashMap<Page, Double> sort = new HashMap<>();

    public RelevanceSimple(HashMap<String, ArrayList<Page>> param)//webserver sends this map after retrieving this form the index
    {
        wordsrelevanceMap = new HashMap<>();
        makeRelevanceMap(param);
        
    }
    public void makeRelevanceMap(HashMap<String, ArrayList<Page>> map) //calculates the simple relevance for each word for each page
    {
        for(String word : map.keySet())
        {
            HashMap<Page, Double> hashmapRelevanceValue = new HashMap<>();
            for(Page page : map.get(word))
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
        for(String word : words)
        {
            HashMap <Page, Double> currentwords = wordsrelevanceMap.get(word);
            for(Page page : matchesAllWords)
            {
                Double relevance = currentwords.get(page);
                if(sort.containsKey(page))
                {
                relevance = relevance + sort.get(page);
                }
                sort.put(page, relevance); 
            }
        }
    }
    public HashMap<Page, Double> getMapOfRelevance()
    {
        return sort;
    }

}