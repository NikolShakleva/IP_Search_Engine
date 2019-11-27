package searchengine;

import java.util.*;

/**
 * IndexTree
 */
public class IndexTree implements Index {

    private TreeMap<String, ArrayList<Page>> wordsToPages;
    private ArrayList<Page> allPages;


    public IndexTree(ArrayList<Page> allPages)  {
        this.allPages = allPages;
        wordsToPages = new TreeMap<>();
        makeHashMap();
    }

    public TreeMap<String, ArrayList<Page>> makeHashMap() {
        for(Page page : allPages) {
            var words = page.getWords();
            for(String word : words)    {
                if(!wordsToPages.containsKey(word)){
                    var list = new ArrayList<Page>();
                    list.add(page);
                       wordsToPages.put(word, list);
                }
                else if(wordsToPages.containsKey(word)) {
                   var list = wordsToPages.get(word);
                   if(!list.contains(page))   {
                       list.add(page);
                   }                              
            }
        }
    }
        return wordsToPages;
    }

    public ArrayList<Page> matchingPages(String input)   {
        ArrayList<Page> matchingPages = new ArrayList<Page>();
       if(wordsToPages.containsKey(input))  {
        matchingPages = wordsToPages.get(input);   {
        }     
    }   return matchingPages;
    }
    public ArrayList<Page> getAllPages()  {
        return allPages;
    }
}
