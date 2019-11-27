package searchengine;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

/**
 * WebServer
 * 
 * @author Ewa, Emelie, Nikol, Philip
 * @version 2019.11.24
 * 
 */
public class WebServer {
    static final int PORT = 8080;
    static final int BACKLOG = 0;
    static final Charset CHARSET = StandardCharsets.UTF_8;
  
    HttpServer server;
    Index index;
    FileReader fileReader;

       WebServer(int port, String filename) throws IOException {
       fileReader = new FileReader(filename);                             
       index = new Index(fileReader.getAllPages());                   


      server = HttpServer.create(new InetSocketAddress(port), BACKLOG); //Creates the server
      server.createContext("/", io -> display(io, 200, "text/html", getFile("web/index.html")));
      server.createContext("/search", io -> search(io));
      server.createContext(
            "/favicon.ico", io -> display(io, 200, "image/x-icon", getFile("web/favicon.ico")));
      server.createContext(
            "/code.js", io -> display(io, 200, "application/javascript", getFile("web/code.js")));
      server.createContext(
            "/style.css", io -> display(io, 200, "text/css", getFile("web/style.css")));
      server.start();
      String msg = " WebServer running on http://localhost:" + port + " ";
      System.out.println("╭"+"─".repeat(msg.length())+"╮");
      System.out.println("│"+msg+"│");
      System.out.println("╰"+"─".repeat(msg.length())+"╯");
    }

      /**
       * creates readable content of file for server in bytes
       * 
       * @param filename, name of file to be translated into bytes
       * @return file translated to bytes
       */
      byte[] getFile(String filename) {
        try {
          return Files.readAllBytes(Paths.get(filename));
        } catch (IOException e) {
          e.printStackTrace();
          return new byte[0];
        }
      }

    /**
     * Searches for search input in library
     * 
     * @param io, HttpExchange
     */
    public void search(HttpExchange io) {  
        var searchTerm = io.getRequestURI().getRawQuery().split("=")[1]; 
        var query = new Query(searchTerm, index);                       
        var correctPages = query.getCorrectPages();  
                                                                           

        respond(io, correctPages);                                        
      }

      /**
       * cretes a response for search input
       * 
       * @param io, HttpExchange
       * @param correctPages, ArrayList of results matching the SearchWord
       */
      void respond(HttpExchange io, ArrayList<Page> correctPages) { 
        var responder = new Responder(correctPages);                        
        List<String> response = new ArrayList<>(responder.getPageNames());   
        var bytes = response.toString().getBytes(CHARSET);                  
        
        display(io, 200, "application/json", bytes);                       
      }

      /**
       * Displays the response on the server
       * 
       * @param io, HttpExchange
       * @param code, integer
       * @param mime, String(application/json)
       * @param response, byte[]
       */   
      void display(HttpExchange io, int code, String mime, byte[] response)  {
        try {
          io.getResponseHeaders()
              .set("Content-Type", String.format("%s; charset=%s", mime, CHARSET.name()));
          io.sendResponseHeaders(200, response.length);
          io.getResponseBody().write(response);
        } catch (Exception e) {
        } finally {
          io.close();
        }
      }

      public static void main(String[] args) throws IOException {
        var filename = Files.readString(Paths.get("config.txt")).strip();
        new WebServer(PORT, filename);
      
       
      }
}
    
