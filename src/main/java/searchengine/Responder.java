package searchengine;

import java.util.*;
import static java.util.stream.Collectors.*;

/**
 * Responder
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 * 
 */

public class Responder {
    private HashMap<Page, Double> correctPages;
    private HashMap<Page, Double> sorted;

    public Responder(HashMap<Page, Double> correctPages){
        this.correctPages = new HashMap<Page, Double>(correctPages);
    }
    /**
     * 
     * @return, an ArrayList with pages formatted with URL and Title for the server to display
     */
    public ArrayList<String> getPageNames(){
         ArrayList<String> response = new ArrayList<>(); 

        sorted = correctPages
        .entrySet()
        .stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .collect(
            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));
        //
        
        for (var page: sorted.keySet()){
            String url = page.getUrl();
            String title = page.getTitle();
            int totalWords = page.getWords().size();
            String words = page.getWords().toString();
            double relevance = correctPages.get(page);
            response.add(String.format("{\"url\": \"%s\", \"title\": \"%s\", \"relevance\": \"%s\", \"totalWords\": \"%s\", \"words\": \"%s\"}",
                                 url, title, relevance, totalWords, words)); 
        } return response;
    }
}