package searchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Class RelevanceTermFrequency - Calculates the Term Frequency relevance for each of the Page objects
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.12.10
 * 
 */
public class RelevanceTermFrequency implements Relevance    {
    private Map<String, HashMap<Page, Double>> wordsrelevanceMap;
    private Map<Page, Double> sort;
    
    /** 
     * Creates a HashMap that stores words as keys and Pages with Relevance Term Frequency score as values
     * @param indexWordsToPages The HashMap coming from the IndexHash class having word as key and Page objects containing the word as a value
     */
    public RelevanceTermFrequency(Map<String, ArrayList<Page>> indexWordsToPages)   {
        wordsrelevanceMap = new HashMap<>();
        sort = new HashMap<>();
        makeRelevanceMap(indexWordsToPages);
    }
	    
        /** 
         * Calculates the relevance score for each page and stors the result in a HashMap which has Page object as a key and the revelence score calculated in double as a value
         * @param matchesAllWords ArrayList of Page objects that are containing the words coming from the user search in the Query class
         * @param words String[], Array of words representing each individual word searched by the user 
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
	    
        /** 
         * Calculates the Term Frequency score for each word in a Page object. Fills up the wordsrelevanceMap which has a word as a key and a HashMap of Pages containing the map key and their corresponding relevance score as a value
         * @param mapFromIndex indexWordsToPages The HashMap coming from the IndexHash class having word as key and Page objects containing the word as a value
         */
        public void makeRelevanceMap(Map<String, ArrayList<Page>> mapFromIndex) {
            for(String word : mapFromIndex.keySet())    {
                HashMap<Page, Double> hashmapRelevanceValue = new HashMap<>();
                for(Page page : mapFromIndex.get(word)) {
                    ArrayList<String> words = page.getWords();
                    double wordRelevence = Collections.frequency(words, word);
                    double termFrequency = (wordRelevence / words.size());
                    hashmapRelevanceValue.put(page, termFrequency);
                }
                wordsrelevanceMap.put(word, hashmapRelevanceValue);
            }
	    }
        /** 
         * @return a HashMap containing Page objects as keys and Relevance score as values. 
         * This map is sent to the Responder class
         */
        public Map<Page, Double> getMapOfRelevance() {
		    return sort;
        }
        /**
         * Empties the content of the sort containing Page objects as keys and Relevance score as values
         */
        public void clearMap()  {
            sort.clear();
        } 
}