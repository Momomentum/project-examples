package Server;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by regrau on 28.06.16.
 * A very simple TCP server which waits for a connection on
 * port 6789 to parse received XML data.
 * It uses the XmlParser class for parsing.
 */
public class Server {
    ServerSocket socket;
    XmlParser parser;

    public Server() throws IOException {
        socket = new ServerSocket(6789);
        parser = new XmlParser();
    }

    public void receive() throws IOException {
       while (true){
           Socket connection = socket.accept();
           BufferedReader inFromClient =
                   new BufferedReader(new InputStreamReader(connection.getInputStream()));
           try {
               parser.transformStringToXML(inFromClient);
           } catch (SAXException e) {
               e.printStackTrace();
           } catch (ParserConfigurationException e) {
               e.printStackTrace();
           }
           parser.validateXML();
           parser.saveXMLtoFile();
           //parser.printXMLToConsole();
       }
    }
}
