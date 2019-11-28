package searchengine;

import java.util.ArrayList;

/**
 * indexList
 */
public class IndexList implements Index    {
    private ArrayList<Page> allPages;

    public IndexList(ArrayList<Page> allPages)  {
        this.allPages = allPages;
        
    }
    public ArrayList<Page> matchingPages(String input) {
        ArrayList<Page> matchingPages = new ArrayList<Page>();
        for (Page page: allPages){
            if(page.getWords().contains(input))
                matchingPages.add(page);
            }   
        return matchingPages;
    }

    public ArrayList<Page> getAllPages()    {
        return allPages;
    }
    
    public ArrayList<Page> getPage()
{
    return allPages;
}
    
}