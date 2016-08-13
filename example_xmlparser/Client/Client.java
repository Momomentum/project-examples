package Client;



import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by regrau on 28.06.16.
 * This class is a simple client which connects to a server
 * on localhost, port 6789.
 * Afterwards it can send an XML formatted String to this server.
 */
public class Client {
    /**
     * The socket the client uses for communication with the server.
     */
    private Socket clientSocket;

    /**
     * Constructor for a client that creates a socket on localhost and port 6789.
     * @throws IOException
     */
    public Client() throws IOException {
        clientSocket = new Socket("localhost", 6789);
    }

    /**
     * Sends xml data to the server.
     * @param xmlAsString       The XML structure that has previously been parsed to a string.
     * @throws IOException
     */
    public void sendXml(String xmlAsString) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(xmlAsString);
        clientSocket.close();
    }
}
