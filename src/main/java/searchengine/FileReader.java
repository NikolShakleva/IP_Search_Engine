package searchengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileReader Class
 * 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class FileReader {

    private ArrayList<Page> allPages;

    public FileReader(String filename)  {
        allPages = new ArrayList<>();
        readFile(filename);
    }
    /**
     * Reading the file given from the webserver and creates pages that are added to an arraylist
     * 
     * @param filename, filename of text file with websites containing url, title and words
     */
    public void readFile(String filename)   {     
        try(Scanner sc = new Scanner(new File(filename));) {
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
                                Page pt = new Page(pageUrl,title, words);
                                allPages.add(pt); 
                            }
                    } else {
                        line = "Error";
                    }
                }     
    } catch (FileNotFoundException e) {
            e.printStackTrace();   
    }  }
        /**
         * 
         * 
         * @return, ArrayList with page objects
         */
        public ArrayList<Page> getAllPages() {
            return allPages;
        }
    }