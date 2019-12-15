
package searchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.IOException;
import java.net.BindException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


@TestInstance(Lifecycle.PER_CLASS)
class WebServerTest {
    WebServer server = null;
 

    @AfterEach
    void tearDown() {
        server.server.stop(0);
        server = null;
    }

    @Test
    void lookupWebServerWithSimple() {

        var rnd = new Random();
        while (server == null) {
            server = new WebServer(rnd.nextInt(60000) + 1024, "data/test-file.txt", "hash", "simple");
        }
    
        String baseURL = String.format("http://localhost:%d/search?q=", server.server.getAddress().getPort());
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"word1\", \"relevance\": \"4.0\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}, {\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"1.0\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word1"));
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"word1\", \"relevance\": \"1.0\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}]",
            httpGet(baseURL + "word2"));
        assertEquals("[{\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"2.0\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word3"));
        assertEquals("[]", 
            httpGet(baseURL + "word4"));
    }

    @Test
    void lookupWebServerWithTf() {
        var rnd = new Random();
        while (server == null) {
            server = new WebServer(rnd.nextInt(60000) + 1024, "data/test-file.txt", "hash", "tf");
        }
    
        String baseURL = String.format("http://localhost:%d/search?q=", server.server.getAddress().getPort());
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"word1\", \"relevance\": \"1.3333333333333333\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}, {\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"0.3333333333333333\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word1"));
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"word1\", \"relevance\": \"0.3333333333333333\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}]",
            httpGet(baseURL + "word2"));
        assertEquals("[{\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"0.6666666666666666\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word3"));
        assertEquals("[]", 
            httpGet(baseURL + "word4"));
    }

    @Test
    void lookupWebServerWithTFIDF() {
        var rnd = new Random();
        while (server == null) {
            server = new WebServer(rnd.nextInt(60000) + 1024, "data/test-file.txt", "hash", "tfidf");
        }
    
        String baseURL = String.format("http://localhost:%d/search?q=", server.server.getAddress().getPort());
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"word1\", \"relevance\": \"0.5406201441442191\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}, {\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"0.13515503603605478\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word1"));
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"word1\", \"relevance\": \"0.3662040962227032\", \"totalWords\": \"3\", \"words\": \"[word1, word2, word1]\"}]",
            httpGet(baseURL + "word2"));
        assertEquals("[{\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"0.7324081924454064\", \"totalWords\": \"3\", \"words\": \"[word1, word3, word3]\"}]", 
            httpGet(baseURL + "word3"));
        assertEquals("[]", 
            httpGet(baseURL + "word4"));
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
