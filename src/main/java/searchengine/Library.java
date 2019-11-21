package searchengine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Library {
    private ArrayList<Page> library;

    public Library(ArrayList<Page> library)
    {
        this. library = library;
    }

    public void add(Page page)
    {
        library.add(page);
    }
    
}