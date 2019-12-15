package searchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Class RelevanceTermFrequency - Calculates the Term Frequency relevance for each of the Page object
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.12.10
 * 
 */

public class RelevanceSimple implements Relevance {
    private Map<String, HashMap<Page, Double>> wordsrelevanceMap;//map of all of the words (as keywords)
    private Map<Page, Double> sort;

    /** 
     * Creates a HashMap that stores words and pages with Relevance Simple values associated to each page
     * @param indexWordsToPages The HashMap consisting of all of the words and pages that contand each word 
     */
    public RelevanceSimple(Map<String, ArrayList<Page>> indexWordsToPages)  {   //webserver sends this map after retrieving this form the index
        wordsrelevanceMap = new HashMap<>();
        sort = new HashMap<>();
        makeRelevanceMap(indexWordsToPages); 
    }

        /** 
         * Calculates the TFIDF value for each page for each word. Fills up the wordsrelevanceMap
         * @param mapFromIndex The HashMap consisting of all of the words and pages that contain each word
         */
        public void makeRelevanceMap(Map<String, ArrayList<Page>> mapFromIndex) {   //calculates the simple relevance for each word for each page
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
         * Calculates the relevance for each page that is being send to the responder
         * @param matchesAllWords ArrayList<Page> that is being send to the Responder
         * @param words String[], Array of key words
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
                } 
                else {
                    sort.put(page,relevanceCurrent);
                } 
            }	
	    }
        
        /** 
         * @return Map<Page, Double>, a Map of Page objects and its relevance values
         */
        public Map<Page, Double> getMapOfRelevance()    {
            return sort;
        }
        
        public void clearMap()  {
            sort.clear();
        }
}