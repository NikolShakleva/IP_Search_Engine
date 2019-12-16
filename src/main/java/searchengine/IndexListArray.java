package searchengine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class IndexArrayList - Stores all Page objects from the file in an ArrayList of Page objects
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class IndexListArray implements Index    {
    private HashMap<String, ArrayList<Page>> wordsToPages;
    private ArrayList<Page> allPages;


    /**
     * It stores all Page object in an ArrayList
     * @param allPages The ArrayList of allPages passed from the FileReader class
     */
    public IndexListArray(ArrayList<Page> allPages)  {
        this.allPages = allPages;
    }

        /**
        *@return The ArrayList of all Page objects coming from the FileReader class
        */      
        public ArrayList<Page> getAllPages()    {
            return allPages;
        }

        /**
         * @return The ArrayList of all Page objects coming from the FileReader class
         */
        public HashMap<String, ArrayList<Page>> getwordsToPages()   {
            return wordsToPages;
        }
        /**
         * Check if the input String coming from the file Query class is matching the words in any of the Page object
         * @param input The user input coming from the Query class
         * @return An arrayList of websites that contain the user input
         */
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