package Client;

import GUI.Controller;
import GUI.MainFrame;

import javax.swing.*;

/**
 * Main Class for the client. Creates a frame that the user can use to send data to a server.
 * Data input is converted into a xml structure, then parsed to a string, which at last is sent to the server.
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new MainFrame("Send data to server");
        Controller controller = new Controller((MainFrame) frame);
   }

}


