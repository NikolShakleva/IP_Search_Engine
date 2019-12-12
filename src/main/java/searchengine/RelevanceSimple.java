package searchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RelevanceSimple implements Relevance {

    private Map<String, HashMap<Page, Double>> wordsrelevanceMap;//map of all of the words (as keywords)
    private Map<Page, Double> sort;

    public RelevanceSimple(Map<String, ArrayList<Page>> indexWordsToPages)  {//webserver sends this map after retrieving this form the index
        wordsrelevanceMap = new HashMap<>();
        sort = new HashMap<>();
        makeRelevanceMap(indexWordsToPages); 
    }
        /**
         * 
         */
        public void makeRelevanceMap(Map<String, ArrayList<Page>> mapFromIndex) {//calculates the simple relevance for each word for each page
            for(String word : mapFromIndex.keySet())    {
                HashMap<Page, Double> hashmapRelevanceValue = new HashMap<>();
                for(Page page : mapFromIndex.get(word)) {
                    ArrayList<String> words = page.getWords();
                    double wordRelevence = Collections.frequency(words, word);
                    hashmapRelevanceValue.put(page, wordRelevence);
                }
                wordsrelevanceMap.put(word, hashmapRelevanceValue);
            }
        }
        /**
         * 
         */
        public void calculatingRelevance(ArrayList<Page> matchesAllWords,String[] words)    {
            HashMap<Page, Double> currentSearchString = new HashMap<>();
            for(String word : words) {  
                HashMap <Page, Double> currentwords = wordsrelevanceMap.get(word);
                for(Page page : matchesAllWords){
                if(!currentSearchString.containsKey(page))  {
                    Double rlv = currentwords.get(page);
                    currentSearchString.put(page, rlv);
                }
                else   {
                    double rlv = currentSearchString.get(page);
                    rlv = rlv + currentSearchString.get(page);
                    currentSearchString.put(page, rlv); 
                } 
                String pageTitle = page.getTitle().toLowerCase();
                if(pageTitle.contains(word)) {
                    double rlvWithTitle = currentSearchString.get(page) * 2;
                    currentSearchString.put(page, rlvWithTitle);
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
                } 
                else {
                    sort.put(page,relevanceCurrent);
                } 
            }	
	    }
        /**
         * 
         */
        public Map<Page, Double> getMapOfRelevance()    {
            return sort;
        }
        /**
         * 
         */
        public void clearMap()  {
            sort.clear();
        }
}