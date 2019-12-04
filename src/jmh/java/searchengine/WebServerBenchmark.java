package searchengine;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class WebServerBenchmark {
    /*WebServer server;
    List<String> hitSearchTerms;
    List<String> missSearchTerms;*/
    FileReader fr;
    Index index;
    private String[] randomWords = {"denmark", "sweden", "usa", "poland", "ocean", 
                                    "sand", "okrjhsuer", "bumblebee", "honeybee", "and", 
                                    "or", "random", "asian", "done", "with", 
                                    "task", "aaabbbcccc", "conceive", "network", "mess" };
 
    @Param({"data/enwiki-tiny.txt", "data/enwiki-small.txt", "data/enwiki-medium.txt", "data/enwiki-large.txt"})
    String filename;

    @Param({"list", "hash", "map"})
    String ix;

    @Setup(Level.Trial)
    public void setup() {
        fr = new FileReader(filename);
        if(ix.equals("list)"))
       {   
        index = new IndexListArray(fr.getAllPages());                         
       }
       else if(ix.equals("hash"))
       {
        index = new IndexHash(fr.getAllPages());     
       }  
       else if (ix.equals("map"))
       {
        index = new IndexTree(fr.getAllPages());   
       }    
     /*   try {
            var rnd = new Random();
            while (server == null) {
                try {
                    server = new WebServer(rnd.nextInt(60000) + 1024, filename, ix);
                } catch (BindException e) {
                    // port in use. Try again
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Benchmark 
    public ArrayList<Page> measureAvgTime() throws InterruptedException {

        ArrayList<Page> allPages = new ArrayList<>();
        for(String word : randomWords)  {
        allPages.addAll(index.matchingPages(word));
        
        } return allPages;
        //server.getIndex().matchingPages(randomWords[(int) (Math.random() * randomWords.length)]);
        
    }
}
