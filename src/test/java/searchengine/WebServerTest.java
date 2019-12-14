
package searchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


class WebServerTest {
    int PORT = 8080;

    @Test
    void lookupWebServerWithSimple() {

          var  server = new WebServer(PORT, "test-file1.txt","hash", "simple");
    
        String baseURL = String.format("http://localhost:%d/search?q=", server.server.getAddress().getPort());
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"title1\", \"relevance\": \"2.0\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}, {\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"1.0\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word1"));
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"title1\", \"relevance\": \"1.0\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}]",
            httpGet(baseURL + "word2"));
        assertEquals("[{\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"2.0\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word3"));
        assertEquals("[]", 
            httpGet(baseURL + "word4"));
            server.server.stop(0);
    }

    @Test
    void lookupWebServerWithTf() {
   
                var server = new WebServer(PORT, "test-file1.txt","hash", "tf");
    
        String baseURL = String.format("http://localhost:%d/search?q=", server.server.getAddress().getPort());
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"title1\", \"relevance\": \"0.6666666666666666\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}, {\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"0.3333333333333333\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word1"));
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"title1\", \"relevance\": \"0.3333333333333333\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}]",
            httpGet(baseURL + "word2"));
        assertEquals("[{\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"0.6666666666666666\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word3"));
        assertEquals("[]", 
            httpGet(baseURL + "word4"));
            server.server.stop(0);
    }

    @Test
    void lookupWebServerWithTFIDF() {
      var server = new WebServer(PORT, "test-file1.txt","hash", "tfidf");
    
        String baseURL = String.format("http://localhost:%d/search?q=", server.server.getAddress().getPort());
        assertEquals("[{\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"0.0\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}, {\"url\": \"http://page1.com\", \"title\": \"title1\", \"relevance\": \"0.0\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}]", 
            httpGet(baseURL + "word1"));
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"title1\", \"relevance\": \"0.23104906018664842\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}]",
            httpGet(baseURL + "word2"));
        assertEquals("[{\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"0.46209812037329684\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word3"));
        assertEquals("[]", 
            httpGet(baseURL + "word4"));
            server.server.stop(0);
    }

    private String httpGet(String url) {
        var uri = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            return client.send(request, BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
