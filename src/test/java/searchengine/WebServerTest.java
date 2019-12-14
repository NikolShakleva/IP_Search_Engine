
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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


@TestInstance(Lifecycle.PER_CLASS)
class WebServerTest {
    WebServer server = null;

    @BeforeAll
    void setUp() {
        var rnd = new Random();
        while (server == null) {
            try {
                server = new WebServer(rnd.nextInt(60000) + 1024, "test-file.txt","hash", "simple");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @AfterAll
    void tearDown() {
        server.server.stop(0);
        server = null;
    }

    @Test
    void lookupWebServer() {
        String baseURL = String.format("http://localhost:%d/search?q=", server.server.getAddress().getPort());
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"title1\", \"relevance\": \"1.0\", \"totalWords\": \"2\", \"words\": \"[word1, word2]\"}, {\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"1.0\", \"totalWords\": \"2\", \"words\": \"[word1, word3]\"}]", 
            httpGet(baseURL + "word1"));
        assertEquals("[{\"url\": \"http://page1.com\", \"title\": \"title1\", \"relevance\": \"1.0\", \"totalWords\": \"2\", \"words\": \"[word1, word2]\"}]",
            httpGet(baseURL + "word2"));
        assertEquals("[{\"url\": \"http://page2.com\", \"title\": \"title2\", \"relevance\": \"1.0\", \"totalWords\": \"2\", \"words\": \"[word1, word3]\"}]", 
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
    }/*
    @Test
    void checkWebserver()   {
       assertEquals("[{\"url\": \"http://bulgari.com", \"title\": \"Sofia\", \"Relevance\": \"2\", \"totalWords\": \"5\", \"words\": \"badminton, university, economics, business, economics\"}]", server.search("economics"));

    }*/
}
