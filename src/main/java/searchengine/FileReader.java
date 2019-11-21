package searchengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Library
 */
public class FileReader {

    private ArrayList<Page> library;

    public FileReader()
    {
        library = new ArrayList<>();
    }

    public void readInInternet(String locationOfInternet)
    {
        try
        {
            File file = new File(locationOfInternet);
            Scanner input = new Scanner(file);

            Pattern p1 = Pattern.compile("\\*PAGE:.*");
    
            while(input.hasNextLine())
            {
                String linia = input.nextLine();
                String title = input.nextLine();
                Matcher m1 = p1.matcher(linia);
                boolean b1 = m1.matches();
                //System.out.println(b1);

                if(b1)
                {

                  ArrayList<String> pageWords = new ArrayList<>();
                  pageWords.add(input.nextLine());
                  Page page = new Page(linia, title, pageWords);
                  library.add(page);
                }
            }
        }
          catch(Exception e)
          {  
              System.out.println("sth is wrong");
         }
      
        
    }

    public ArrayList<Page> getArrrayOfPages()
    {
      return library;
    }

    public void printArray()
    {
        System.out.println(library);
    }

    // public void read(String filename) throws IOException
    // {
    // try {
    //     List<String> lines = Files.readAllLines(Paths.get(filename));
    //     var lastIndex = lines.size();
    //     for (var i = lines.size() - 1; i >= 0; --i) {
    //       if (lines.get(i).startsWith("*PAGE")) {
    //         String webname = lines.get(i);
    //         // i'm not sure about the one below, it looks like it takes only first page but on the other 
    //         //hand the search engine works well so idk what's going on here
    //         List<String> words = lines.subList(i-1, lastIndex);
    //         Page page = new Page(webname, words);
    //         library.add(page);
    //         lastIndex = i;
    //       }
    //     }
    //   } catch (FileNotFoundException e) {
    //     e.printStackTrace();
    //   }
    // }
    
}