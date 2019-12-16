package searchengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class FileReader - read a  text file. While reading the file, it initatites page objects.
 *
 * This class is part of the "WebServer" application.  
 * 
 * @author  Emelie, Philip, Ewa, Nikol
 * @version 2019.11.20
 */
public class FileReader {

    private ArrayList<Page> allPages;

    /**
     * Initaiates ArrayList of Page objects
     * Read the text file
     * It creates Page objects while reading the file.
     * Stores the Page objects into the ArrayList of Page objects
     * @param filename The name of the text file, coming from the WebServer class.
     */
    public FileReader(String filename)  {
        allPages = new ArrayList<>();
        readFile(filename);
    }
        /**
        * Reading the file given from the Webserver and creates Page objects that are added to an ArrayList of Page objects 
        * @param filename, filename of text file with websites containing url, title and words
        */
        public void readFile(String filename)   {     
            try(Scanner sc = new Scanner(new File(filename));) {
                String line = sc.nextLine();
                while (sc.hasNextLine()) {
                    ArrayList<String> words = new ArrayList<>(); 

                    if(line.startsWith("*PAGE"))   {         
                        String pageUrl = line.replace("*PAGE:", ""); 
                        String title = sc.nextLine();      
                        while (sc.hasNextLine())   {   
                            String word = sc.nextLine(); 

                            if(!word.startsWith("*PAGE"))  {
                                words.add(word);   
                            } else    {                   
                                line = word;
                                break;
                            }
                        }
                        if (!words.isEmpty()) {     
                            Page pt = new Page(pageUrl,title, words);
                            allPages.add(pt); 
                        }
                    } else   {    
                        line = "Error";         
                    }
                }     
            } catch (FileNotFoundException e) {
                System.out.println("File could not be found, check you spelling and try again");
                e.printStackTrace();   
            }  
        }
        /**
         * Return the ArrayList of Page objects allPages, after the file has been read
         * @return, the full ArrayList of Page objects
         */
        public ArrayList<Page> getAllPages() {
            return allPages;
        }
    }