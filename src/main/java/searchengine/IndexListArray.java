package searchengine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * indexList
 */
public class IndexListArray implements Index    {
    private HashMap<String, ArrayList<Page>> wordsToPages;
    private ArrayList<Page> allPages;

    public IndexListArray(ArrayList<Page> allPages)  {
        this.allPages = allPages;
        makeHashMap();
    }

        public ArrayList<Page> matchingPages(String input) {
            ArrayList<Page> matchingPages = new ArrayList<Page>();
            for (Page page: allPages){
                if(page.getWords().contains(input)) {
                    matchingPages.add(page);
            }   }  
            return matchingPages;
        }

        public ArrayList<Page> getAllPages()    {
            return allPages;
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

        public HashMap<String, ArrayList<Page>> getwordsToPages()   {
            return wordsToPages;
        }
   
    
}