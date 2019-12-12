package searchengine;

import java.util.*;

/**
 * Responder
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 * 
 */

public class Responder {
    private Map<Page, Double> correctPages;
    private LinkedHashMap<Page, Double> sortedMap = new LinkedHashMap<>();
    private ArrayList<String> response;

    public Responder(Map<Page, Double> correctPages){
        this.correctPages = correctPages;
        
    }
        /**
        * 
        * @return, an ArrayList with pages formatted with URL and Title for the server to display
        */
        public ArrayList<String> getPageNames() {
            response = new ArrayList<>(); 
            correctPages.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
            .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

            for (Page page: sortedMap.keySet()){
                String url = page.getUrl();
                String title = page.getTitle();
                int totalWords = page.getWords().size();
                String words = page.getWords().toString();
                double relevance = sortedMap.get(page);
                response.add(String.format("{\"url\": \"%s\", \"title\": \"%s\", \"relevance\": \"%s\", \"totalWords\": \"%s\", \"words\": \"%s\"}",
                                 url, title, relevance, totalWords, words)); 
            } return response;
        }
}