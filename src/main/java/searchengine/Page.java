package searchengine;
import java.util.*;

/**
 * Page
 * 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class Page{
    private String url;
    private String title;
    private ArrayList<String> words;
    private double relevance;
    private HashMap<String, Double> tf;


    public Page(String pageUrl, String pageTitle, ArrayList<String> words){
        this.title = pageTitle;
        this.url = pageUrl;
        this.words = words;
        relevance = 0;
        makeTF();

    }
    public void replaceWords(ArrayList<String> list) {
        words.addAll(list);
    }
       /**
        * 
        * @return, the pages' URL as a String
        */
        public String getUrl()  {
            return this.url;
        }
        /**
         * 
         * @return, the page Title as a String
         */
        public String getTitle()    {
            return this.title;
        }

        public ArrayList<String> getWords() {
            return words;
        }


        public void increaseRelevance(String x)
        {
            relevance += tf.get(x);
            // int occurences = Collections.frequency(words, x);
            // this.relevance+=occurences;
        }

        public void resetRelevance()
        {
            this.relevance = 0;
        }

        public HashMap<String, Double> makeTF()
        {
            tf = new HashMap<>();
            for(String x : words)
            {
            int occurences = Collections.frequency(words, x);
            double tf1 = occurences/words.size();
            tf.put(x, tf1);
            }

            return tf;
        }

        public void finalCalculation(HashMap<String , Double> param)
        {
            for(String x : tf.keySet())
            {
                double currentWordRelevance = tf.get(x) * param.get(x);
                tf.put(x, currentWordRelevance);
            }

        }

        public double getRelevance()
        {
            //double relevance = tf.get(searchword);
            return relevance;
        }

        
}