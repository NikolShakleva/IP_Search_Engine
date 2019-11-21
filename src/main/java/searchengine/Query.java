package searchengine;

import java.util.List;

/**
 * Query
 */
 class Query {
     String searchTerm;
     List<Page> matchingList;

     Query(String searchTerm)  {
       this.searchTerm = searchTerm;


    }
List<Page> getMatchingList()  {
    return matchingList;
}
    
}