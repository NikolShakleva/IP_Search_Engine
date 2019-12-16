package searchengine;

import java.util.*;

/**
 * RandomStringGenerator
 * 
 * This class helps to generate random strings to our test cases.
 * To use this method we will need a random 
 * 
 */
public class RandomGenerator {

    public static final String alpha = "abcdefghijklmnopqrstuvwxyz";
    private Random rnd = new Random();

    /**
     * Generates a random string with the lenght of 2-10 characters
     * 
     * @return, a random generated string with lowercase letters.
     */
    public String generateString() {
        int length = rnd.nextInt(10) + 2;
        char[] word = new char[length];
        for(int i = 0 ; i < length ; i++)   {
            word[i] = alpha.charAt(rnd.nextInt(alpha.length()));
        }
        return new String(word);
    }
    /**
     * Generates a random integer between the span of the int in the parameters .
     * 
     * @param from, int
     * @param to, int
     * @return a random generated in between to and from
     */
    public int generateInt(int from, int to)    {
        int randomInt = rnd.nextInt(to) + from;
        return randomInt;
    }
    
}
