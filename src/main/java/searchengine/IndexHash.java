package searchengine;

import java.util.*;

/**
 * IndexHash
 */
public class IndexHash implements Index {
    private HashMap<String, ArrayList<Page>> wordsToPages;
    private ArrayList<Page> allPages;
    private HashMap<String, Double> idf;

    public IndexHash(ArrayList<Page> allPages) {
        this.allPages = allPages;
        wordsToPages = new HashMap<>();
        makeHashMap();
        makeIDF();
        tfidfToPages();
    }

    public HashMap<String, ArrayList<Page>> makeHashMap() {
        for (Page page : allPages) {
            var words = page.getWords();
            for (String word : words) {
                if (!wordsToPages.containsKey(word)) {
                    var list = new ArrayList<Page>();
                    list.add(page);
                    wordsToPages.put(word, list);
                } else if (wordsToPages.containsKey(word)) {
                    var list = wordsToPages.get(word);
                    if (!list.contains(page)) {
                        list.add(page);
                    }
                }
            }
        }
        return wordsToPages;
    }

    public HashMap<String, Double> makeIDF() {
        idf = new HashMap<>();
        for (String word : wordsToPages.keySet())
        {
            double numberOfPages = wordsToPages.get(word).size();
            Double idf1 = Math.log10(allPages.size()/numberOfPages);
            idf.put( word, idf1);
        }
        return idf;
    }

    public ArrayList<Page> matchingPages(String input)   {
        ArrayList<Page> matchingPages = new ArrayList<Page>();
       if(wordsToPages.containsKey(input))  {
         matchingPages = wordsToPages.get(input);      
    }  
       return matchingPages;

    }
    public ArrayList<Page> getAllPages()  {
        return allPages;
    }

    public void tfidfToPages()    {
        for(Page page : allPages)
        {
            page.TFIDFCalculation(idf);
        }
    }

}