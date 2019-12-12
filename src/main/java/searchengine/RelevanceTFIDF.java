package searchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



/**
 * RelevanceTFIDF
 */
public class RelevanceTFIDF implements Relevance {
    private Map<String, HashMap<Page, Double>> wordsrelevanceMap;//map of all of the words (as keywords)
    private Map<Page, Double> sort;
    private Map<String, Double> idf;
    private Index index;

    public RelevanceTFIDF(Map<String, ArrayList<Page>> indexWordsToPages, Index index) {
        wordsrelevanceMap = new HashMap<>();
        sort = new HashMap<>();
        this.index = index;
        makeIDF(indexWordsToPages);
        makeRelevanceMap(indexWordsToPages);
    }
    
        /**
         * 
         * @param indexWordsToPages
         */
        public void makeIDF(Map<String, ArrayList<Page>> indexWordsToPages){
            idf = new HashMap<>();
            for (String word : indexWordsToPages.keySet())  {
                double numberOfPages = indexWordsToPages.get(word).size();
                Double idf1 = Math.log(index.getAllPages().size()/numberOfPages);
                idf.put(word, idf1);
            }
        }
        /**
         * 
         */
	    public void makeRelevanceMap(Map<String, ArrayList<Page>> mapFromIndex) {
		    for(String word : mapFromIndex.keySet())    {
                HashMap<Page, Double> hashmapRelevanceValue = new HashMap<>();
                for(Page page : mapFromIndex.get(word)) {
                    ArrayList<String> words = page.getWords();
                    double wordRelevence = Collections.frequency(words, word);
                    double termFrequency = (wordRelevence / words.size()) * idf.get(word);
                    hashmapRelevanceValue.put(page, termFrequency);
                }
                wordsrelevanceMap.put(word, hashmapRelevanceValue);
            }
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
                        double currentWord = wordsrelevanceMap.get(word).get(page);
                        double rlvWithTitle = currentSearchString.get(page) + currentWord;
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
        
	    public Map<Page, Double> getMapOfRelevance() {
		    return sort;
	    }

	    public void clearMap() {
		    sort.clear();	
	    }
}