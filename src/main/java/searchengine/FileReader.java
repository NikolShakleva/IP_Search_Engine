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
                String url = input.nextLine();
                String title = input.nextLine();
                Matcher m1 = p1.matcher(url);
                boolean b1 = m1.matches();
                //System.out.println(b1);

                if(b1)
                {

                  ArrayList<String> pageWords = new ArrayList<>();
                  pageWords.add(input.nextLine());
                  Page page = new Page(url, title, pageWords);
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
    
}
