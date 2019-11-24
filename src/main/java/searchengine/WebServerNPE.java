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
 * WebServer2
 */
public class WebServerNPE {
    static final int PORT = 8080;
    static final int BACKLOG = 0;
    static final Charset CHARSET = StandardCharsets.UTF_8;
  
    HttpServer server;
    Library library;
    FileReader fileReader;

       WebServerNPE (int port, String filename) throws IOException {
       fileReader = new FileReader(filename);                          //sends file to FileReader class
       library = new Library(fileReader.getAllPagesList());                    //gets a ArrayList<Pages> and creates a library


      server = HttpServer.create(new InetSocketAddress(port), BACKLOG); //Creates the server
      server.createContext("/", io -> display(io, 200, "text/html", getFile("web/index.html")));
      server.createContext("/search", io -> search("searchTerm"));
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
       * @param filename
       * @return
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
     * @param io
     */
    public void search(String searchTerm) {  
       // var searchTerm = io.getRequestURI().getRawQuery().split("=")[1];  //transforms search input to string
        var query = new Query(searchTerm, library);                       //creates a query class and sends searchword
        var correctPages = query.getCorrectPages();                       //returns list of matching pages

        respond(correctPages);                                        //sends info to response
      }
      /**
       * cretes a response for search input
       * 
       * @param io
       * @param matchingList
       */
      void respond(ArrayList<Page> correctPages) { 
        var responder = new Responder(correctPages);                         //creates a responder class with list from query
        List<String> response = new ArrayList<>(responder.getPageNames());   //returns respons from responder
        var bytes = response.toString().getBytes(CHARSET);                   //translates response to bytes
        
      //  display(io, 200, "application/json", bytes);                         //sends respons to display
      }
      /**
       * Displays response
       * 
       * @param io
       * @param code
       * @param mime
       * @param response
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
      /**
       * adds pages to library
       */
      void addPage()  {

      }
      public static void main(String[] args) throws IOException {
        var filename = Files.readString(Paths.get("config.txt")).strip();
        var webserver = new WebServer(PORT, filename);
        
       
      }
}
    
