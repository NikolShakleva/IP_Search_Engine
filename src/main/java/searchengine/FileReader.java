package searchengine;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;

import java.util.Scanner;

/**
 * Library
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
                allPages.add(pt);
    
        }
    
    } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
 } 
        
        
        
        /*
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            var lastIndex = lines.size();
            for (var i = lines.size() - 1; i >= 0; --i) {
            if (lines.get(i).startsWith("*PAGE")) {
             allPages.add(lines.subList(i, lastIndex));
             lastIndex = i;
        }
      }

          /*  File file = new File(filename);
            Scanner input = new Scanner(file);
    
            while(input.hasNextLine()) {
              String url = input.nextLine();
                if(url.contains("*PAGE"))  {
                  String pageTitle = input.nextLine();
                  ArrayList<String> pageWords = new ArrayList<>();
                    while (input.hasNextLine()){
                        if(!input.nextLine().contains("*PAGE")) {
                        pageWords.add(input.nextLine());
                    }
                    Page page = new Page(url, pageTitle, pageWords);
                    allPages.add(page);
                    } 
                }
            }
            input.close();
        }
          catch(Exception e)
          {  
            System.out.println("site is wrong");
         }
      
        }*/
        
        public ArrayList<Page> getAllPagesList()    {
            return allPages;
        }
    }