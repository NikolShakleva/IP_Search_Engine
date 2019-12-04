package searchengine;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class IndexBenchmark {
    FileReader fr;
    ArrayList<Page> library;
    private String[] randomWords = {"denmark", "sweden", "usa", "poland", "ocean", 
                                    "sand", "word", "bumblebee", "honeybee", "and", 
                                    "or", "random", "asian", "done", "with", 
                                    "task", "four", "conceive", "network", "mess" };

    @Param({"data/enwiki-tiny.txt", "data/enwiki-small.txt", "data/enwiki-medium.txt", "data/enwiki-large.txt"})
    String filename;

    @Setup
    public void setup()     {
       
        fr = new FileReader(filename);
        library = new ArrayList<>(fr.getAllPages());
    }

    @Benchmark
    public void measureAvgTimeHash()    {
        var hash = new IndexHash(library);
        hash.matchingPages(randomWords[(int) (Math.random() * randomWords.length)]);

    }
    @Benchmark
    public void measureAvgTimeTree()    {
        var tree = new IndexTree(library);
        tree.matchingPages(randomWords[(int) (Math.random() * randomWords.length)]);
    }
    @Benchmark
    public void measureAvgTimeList()    {
        var list = new IndexListArray(library);
        list.matchingPages(randomWords[(int) (Math.random() * randomWords.length)]);
    }

}