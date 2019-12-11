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
    private HashMap<Page, Double> correctPages;
    LinkedHashMap<Page, Double> sortedMap = new LinkedHashMap<>();

    public Responder(HashMap<Page, Double> correctPages){
        this.correctPages = correctPages;
        
    }
    /**
     * 
     * @return, an ArrayList with pages formatted with URL and Title for the server to display
     */
    public ArrayList<String> getPageNames(){
         ArrayList<String> response = new ArrayList<>(); 

         correctPages.entrySet()
         .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
        .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
      /*  correctPages
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue())
        .collect(
            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));
        */
        
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