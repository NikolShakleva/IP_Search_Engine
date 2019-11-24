package searchengine;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;

import java.util.Scanner;

/**
 * Library Class
 * Stores all the pages 
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class FileReader {

    private ArrayList<Page> allPages;

    public FileReader(String filename)
    {
        allPages = new ArrayList<Page>();
        readFile(filename);
    }
    /**
     * Reading the file and creates pages that are added to an arraylist
     * 
     * @param filename
     */
    public void readFile(String filename)   {        
        try {
            Scanner sc = new Scanner(new File(filename));
            String pageURL = "";
            String title = "";
            String pageUrl = "";
            String line = sc.nextLine();
            while (sc.hasNextLine()){
                ArrayList<String> words = new ArrayList<>();
                if(line.startsWith("*Page")) {
                    pageUrl = line;

                } else {
                    pageUrl = pageURL;
                }
                    if (sc.hasNextLine()) {
                        line = sc.nextLine();
                        title = line;
                    }
                    
                while (sc.hasNextLine()){
                    line = sc.nextLine();
                    if (line.startsWith("*")) {
                        pageURL = line;
                        break;
                        } else {
                    words.add(line);
                        }
                } 
                Page pt = new Page(pageUrl,title, words);
                if(words.size() >= 1) {
                allPages.add(pt);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }     
 }      
 /**
  * Getting a List with all Pages Stored
  * 
  * @return an ArrayList with all Pages Stored
  */
        public ArrayList<Page> getAllPagesList()    {
            return allPages;
        }
    }