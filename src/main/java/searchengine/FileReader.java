package searchengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class FileReader - read a  text file. While reading the file, initatites page objects.
 *
 * This class is part of the "WebServer" application.  
 * 
 * @author  Emelie, Philip, Ewa, Nikol
 * @version 2019.11.20
 */
public class FileReader {

    private ArrayList<Page> allPages;

    /**
     * Initaiates arrayList of pages
     * Read the text file
     * It creates page objects while reading the file.
     * Stores the page objects into the ArrayList of pages
     * @param filename The name of the text file, coming from the WebServer class.
     */
    public FileReader(String filename)  {
        allPages = new ArrayList<>();
        readFile(filename);
    }
        /**
        * Reading the file given from the webserver and creates pages that are added to an arrayList of pages
        * 
        * @param filename, filename of text file with websites containing url, title and words
        */
        public void readFile(String filename)   {     
            try(Scanner sc = new Scanner(new File(filename));) {
                String line = sc.nextLine();
                while (sc.hasNextLine()) {// check if the file contains more words
                    ArrayList<String> words = new ArrayList<>(); // initialises empthy arrayList of words for each page

                    if(line.startsWith("*PAGE"))   {         // checks if the line contains URL 
                        String pageUrl = line.replace("*PAGE:", ""); //assignes a URL to the page object
                        String title = sc.nextLine();      // assigns a title to the page object 
                        while (sc.hasNextLine())   {   // checks if the page contains any words 
                            String word = sc.nextLine(); 

                            if(!word.startsWith("*PAGE"))  {
                                words.add(word);    // fills the arrayList of words for each page object
                            } else    {                    // when a page contains no more words, stop adding words to the arraylist of words
                                line = word;
                                break;
                            }
                        }
                        if (!words.isEmpty()) {     // if the arrayList of words contains words a page object is created
                            Page pt = new Page(pageUrl,title, words);
                            allPages.add(pt); 
                        }
                    } else   {     // if the line doesn't start with a *PAGE
                        line = "Error";           // the reading of the file is terminated
                    }
                }     
            } catch (FileNotFoundException e) {
                System.out.println("File could not be found, check you spelling and try again");
                e.printStackTrace();   
            }  
        }
        /**
         * Return the arraylist of pages - allPages, after the file has been read
         * @return, the full ArrayList of page objects
         */
        public ArrayList<Page> getAllPages() {
            return allPages;
        }
    }