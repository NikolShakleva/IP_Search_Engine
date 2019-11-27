package searchengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * FileReader Class
 * 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class FileReader {

    private Map<String, ArrayList<Page>> wordsToMap;
    private List<Page> allPages;

    public FileReader(String filename)  {
        wordsToMap = new HashMap<>();
        allPages = new ArrayList<>();
        readFile(filename);
    }
    /**
     * Reading the file given from the webserver and creates pages that are added to an Map with the key(word) to an ArrayList with pages containing that word.
     * 
     * @param filename, filename of text file with websites containing url and title
     */
    public void readFile(String filename)   {     
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
            String line = sc.nextLine();
            while (sc.hasNextLine())    {
                if(line.startsWith("*PAGE"))    {
                        String pageUrl = line;
                        String title = sc.nextLine();
                        Page pt = new Page(pageUrl,title);

                        while (sc.hasNextLine()){
                             String word = sc.nextLine();
                             if(!word.startsWith("*PAGE") && !wordsToMap.containsKey(word)){
                                 var list = new ArrayList<Page>();
                                 list.add(pt);
                                    wordsToMap.put(word, list);
                             }
                             else if(!word.startsWith("*PAGE") && wordsToMap.containsKey(word)) {
                                var list = wordsToMap.get(word);
                                list.add(pt);
                             } 
                             else {
                                 line = word;
                                 break;
                             }
                        }
                } else {
                    line = "Error";
                    }
                }    
    } catch (FileNotFoundException e) {
            e.printStackTrace();
        }    finally {
            sc.close();
      }       
    }  
        /**
     * Reading the file given from the webserver and creates pages that are added to an arraylist
     * 
     * @param filename, filename of text file with websites containing url, title and words
     */
    public void readFileL(String filename)   {     
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
            String line = sc.nextLine();
            while (sc.hasNextLine()){
                    ArrayList<String> words = new ArrayList<>();
                    if(line.startsWith("*PAGE")) {
                        String pageUrl = line;
                        String title = sc.nextLine();
                        while (sc.hasNextLine()){
                             String word = sc.nextLine();
                             if(!word.startsWith("*PAGE")){
                                    words.add(word);
                             } else {
                                 line = word;
                                 break;
                             }
                            }
                            if (!words.isEmpty()) {
                                Page pt = new Page(pageUrl,title);
                                pt.replaceWords(words);
                                allPages.add(pt); 
                            }
                    } else {
                        line = "Error";
                    }
                }     
    } catch (FileNotFoundException e) {
            e.printStackTrace();
        }    finally {
            sc.close();
      }       
    }  
 
        /**
         * 
         * @return, map that holds keywords words to list og pages containging keyword
         */
        public Map<String, ArrayList<Page>> getAllPages()    {
            return wordsToMap;
        }
        /**
         * 
         * 
         * @return, ArrayList with page objects
         */
        public List<Page> getAllPagesList() {
            return allPages;
        }
    }