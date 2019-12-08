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
    private HashMap<String, Double> tfIdf;



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

        public HashMap<String, Double> makeTF()
        {
            tf = new HashMap<>();
            for(String x : words)
            {
            double occurences = Collections.frequency(words, x);
            double allWords = words.size();
            double tf1 = occurences / allWords;
            tf.put(x, tf1);
            }

            return tf;
        }

        public void finalCalculation(HashMap<String , Double> idfFromIndex)    {
            tfIdf = new HashMap<>();
            for(String x : tf.keySet())
            {
                double currentWordRelevance = tf.get(x) * idfFromIndex.get(x);
                tfIdf.put(x, currentWordRelevance);
            }
        }

        public double getRelevanceIDF(String word) {
            double wordRelevence = tfIdf.get(word);
            if(title.contains(word))  {
                wordRelevence += tfIdf.get(word);
            }
            return wordRelevence;
        }
        public double getRelevanceSimple(String word) {
            double wordRelevence = Collections.frequency(words, word);
            if(title.contains(word))  {
                wordRelevence += tfIdf.get(word);
            }
            return wordRelevence;
        }
        public double getRelevanceTF(String word) {
            double wordRelevence = tf.get(word);
            if(title.contains(word))  {
                wordRelevence += tfIdf.get(word);
            }return wordRelevence;
            
        }
}