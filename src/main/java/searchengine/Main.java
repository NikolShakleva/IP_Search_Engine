package searchengine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Main
 */
public class Main {

    private static final int PORT = 0;

    public static void main(String[] args) throws IOException {
        var filename = Files.readString(Paths.get("config.txt")).strip();
        new WebServer(PORT, filename);
    }
}