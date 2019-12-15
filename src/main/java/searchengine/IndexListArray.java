package searchengine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class IndexArrayList - Stores all page objects from the file in an ArrayList<Page>
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class IndexListArray implements Index    {
    private HashMap<String, ArrayList<Page>> wordsToPages;
    private ArrayList<Page> allPages;


    /**
     * Stores all page object in an ArrayList<Page>
     * @param allPages The ArrayList<Page> of allPages passed from the FileReader class
     */
    public IndexListArray(ArrayList<Page> allPages)  {
        this.allPages = allPages;
    }

        /**
        *@return The ArrayList<Page> of all page objects coming from the FileReader class
        */      
     
        public ArrayList<Page> getAllPages()    {
            return allPages;
        }

        /**
         * @return The ArrayList<Page> of all page objects coming from the FileReader class
         */
        public HashMap<String, ArrayList<Page>> getwordsToPages()   {
            return wordsToPages;
        }

        public ArrayList<Page> matchingPages(String input) {
            ArrayList<Page> matchingPage = new ArrayList<>();
            for(Page page : allPages){
              ArrayList<String> words =  page.getWords();
              for(String word : words){
                  if(word.equals(input)){
                      if(!matchingPage.contains(page))
                      matchingPage.add(page);
                  }
              }
            }
            return matchingPage ;
        }
}