package Server;

import java.io.IOException;

/**
 * Created by regrau on 28.06.16.
 * Starts a server that is listening on port 6789.
 * Server constantly waits for incoming packages that are parsed to xml and saved as a serialized object.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.receive();
        } catch (IOException e) {
            System.err.println("Port is already in use. Most likely Server is already running!");
        }
    }
}
