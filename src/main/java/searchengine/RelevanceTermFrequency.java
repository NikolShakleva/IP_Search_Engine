package searchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * RelevanceTermFrequency
 */
public class RelevanceTermFrequency implements Relevance    {
    private HashMap<String, HashMap<Page, Double>> wordsrelevanceMap;//map of all of the words (as keywords)
    private HashMap<Page, Double> sort;

    public RelevanceTermFrequency(HashMap<String, ArrayList<Page>> indexWordsToPages)   {
        wordsrelevanceMap = new HashMap<>();
        sort = new HashMap<>();
        makeRelevanceMap(indexWordsToPages);
    }
        /**
         * 
         */
	    public void calculatingRelevance(ArrayList<Page> matchesAllWords, String[] words) {
            HashMap<Page, Double> currentSearchString = new HashMap<>();
            for(String word : words) {  
                HashMap <Page, Double> currentwords = wordsrelevanceMap.get(word);
                for(Page page : matchesAllWords){
                    if(!currentSearchString.containsKey(page))  {
                        Double rlv = currentwords.get(page);
                        currentSearchString.put(page, rlv);
                    } else   {
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
                } else {
                    sort.put(page,relevanceCurrent);
                } 
            }	
	    }   
        /**
         * 
         */
	    public void makeRelevanceMap(HashMap<String, ArrayList<Page>> mapFromIndex) {
            for(String word : mapFromIndex.keySet())    {
                HashMap<Page, Double> hashmapRelevanceValue = new HashMap<>();
                for(Page page : mapFromIndex.get(word)) {
                    ArrayList<String> words = page.getWords();
                    double wordRelevence = Collections.frequency(words, word);
                    double termFrequency = (wordRelevence / words.size()) * 100000;
                    hashmapRelevanceValue.put(page, termFrequency);
                }
                wordsrelevanceMap.put(word, hashmapRelevanceValue);
            }
	    }

	    public HashMap<Page, Double> getMapOfRelevance() {
		    return sort;
        }

        public void clearMap()  {
            sort.clear();
        } 
}