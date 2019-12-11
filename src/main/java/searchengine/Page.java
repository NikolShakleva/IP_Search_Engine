package searchengine;
import java.util.*;

/**
 * Class Page - is the blueprint of a page object.
 * It describes all companents f a page object
 * It calculates the Simple count score relevance of a word in a page
 * It calculates the TF (term frequency) relevance of a word in a page
 * It calculates the TFIDF (term frequency - inverted document frequency) relevance of a word in a page
 * 
 * @author Emelie, Ewa, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class Page{
    private String url;
    private String title;
    private ArrayList<String> words;
    private HashMap<String, Double> tf;
    private HashMap<String, Double> tfIdf;


/**
 * Create a page object based on the paramerts passed to the constructor
 * It creates a map calculating the term-frequency relevance of each word in the page objects
 * @param pageUrl Creates the page URL
 * @param pageTitle Creates the page title
 * @param words Creates a list of words contained in a page
 */
    public Page(String pageUrl, String pageTitle, ArrayList<String> words){
        this.title = pageTitle;
        this.url = pageUrl;
        this.words = words;
        makeTF();

    }
       /**
        * @return The pages' URL as a String
        */
        public String getUrl()  {
            return this.url;
        }

        /**
         * @return The page Title as a String
         */
        public String getTitle()    {
            return this.title;
        }

        /**
         * @return The ArayList of words contained in a single page
         */
        public ArrayList<String> getWords() {
            return words;
        }

        /**
         * Create a map which calculates the term-frequenct ratio (TFR) of each words in the page object
         * The word in each page is the key of the tf map. 
         * The term-frequenct ratio (TFR) is the value of the tf map
         * @return tf as a HashMap of String and Double
         */
        public HashMap<String, Double> makeTF()
        {
            tf = new HashMap<>();
            for(String singleWord : words) // iterates over each word into the list of words in every page object
            {
            double occurences = Collections.frequency(words, singleWord); // calculates the number of times a words exist in a page object
            double allWords = words.size();
            double tfRatio = occurences / allWords; // calculate the term frequency ratio (TFR) of each word in a page object
            tf.put(singleWord, tfRatio);
            }

            return tf;
        }

        /**
         * Calculates the TFIDF relevance of each word in a page object and stores the result in a TFIDF HashMap <String, Double>
         * wordKey is the key
         * current relevance is the value
         * @param idfFromIndex This is a HashMap<String, Double> which holds the inverse document frequency (IDF) of each word in all page objects 
         */
        public void TFIDFCalculation(HashMap<String , Double> idfFromIndex)    {
            tfIdf = new HashMap<>(); // map which stores the term frequency-inerted document frequency (TFIDF) for each word in a page object is initaiated
            for(String wordKey : tf.keySet())
            {
                double currentWordRelevance = tf.get(wordKey) * idfFromIndex.get(wordKey); //calculate the currentRelevance be the formula: TF*IDF
                tfIdf.put(wordKey, currentWordRelevance);
            }
        }

        /** The method returns the TFIDF relevance score of a given word from the Query class
        * @param word The words which relevance score is obtained from the TFIDF map
        * @return The TFIDF score relevance of of a given word in double
        */
        public double getRelevanceTFIDF(String word) {
            double wordRelevence = tfIdf.get(word);
            if(title.contains(word))  {  // if the words is a title of the page, the relevance score is doubled
                wordRelevence += tfIdf.get(word);
            }
            return wordRelevence;
        }

        /** The method returns the simple count relevance score of a given word from the Query class
        * @param word The words which relevance score is obtained based on count frequency 
        * @return The simple score relevance of of a given word in double
        */
        public double getRelevanceSimple(String word) {
            double wordRelevence = Collections.frequency(words, word);
            if(title.contains(word))  {  // if the words is a title of the page, the relevance score is doubled
                wordRelevence += tfIdf.get(word);
            }
            return wordRelevence;
        }

         /** The method returns the TF relevance score of a given word from the Query class
        * @param word The words which relevance score is obtained from the TF map
        * @return The TF score relevance of of a given word in double
        */
        public double getRelevanceTF(String word) {
            double wordRelevence = tf.get(word);
            if(title.contains(word))  {   // if the words is a title of the page, the relevance score is doubled
                wordRelevence += tfIdf.get(word);
            }
            return wordRelevence;
            
        }
}