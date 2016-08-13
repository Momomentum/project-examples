package GUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * Created by momomentum on 04.07.16.
 * The Clients main frame used to input data and send it by pushing either of the two send buttons, that are equally in the
 * student panel and in the professor pane.
 */
public class MainFrame extends JFrame {
    /**
     * The Panels that contain the input fields for student and professor
     */
    private JPanel studPane, profPane;

    /**
     * Labels used as dividers
     */
    private JLabel studentHeader, profHeader;

    /**
     * Constructor. Calling this constructor opens a new window using the miglayout for swing.
     * Location and size are set and panels are added to it.
     * @param windowName    Desired name for that window.
     */
    public MainFrame(String windowName) {
        super(windowName);
        setLayout(new MigLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocation(400, 20);
        addPanels();
        setVisible(true);
    }

    /**
     * Add the two panels to the frame container. Making use of the benefits of the miglayout.
     *
     */
    private void addPanels() {
        studPane = new StudentPanel();
        profPane = new ProfPanel();
        studentHeader = new JLabel("Student");
        profHeader = new JLabel("Professor");
        this.add(studentHeader, "wrap");
        this.add(studPane, "wrap");
        this.add(profHeader, "wrap");
        this.add(profPane);
    }

    /**
     * Getter for the student panel.
     * @return      The panel containing the student input information.
     */
    public StudentPanel getStudentPanel() {
        return (StudentPanel) this.studPane;
    }

    /**
     * Getter for the professor panel.
     * @return      The panel containing the professor input information.
     */
    public ProfPanel getProfPanel() {
        return (ProfPanel) this.profPane;
    }

    /**
     * Opens a warning message displaying the fields that havent been filled out.
     * @param missingFields
     * @param where
     */
    public void missingFields(String missingFields, String where){
        JOptionPane.showMessageDialog(this, "Missing Fields:" + missingFields + " in " + where, "Missing Fields" , JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Opens an error message when not being able to send to the server.
     */
    public void errorWhileSending() {
        JOptionPane.showMessageDialog(this, "Error while Sending...", "Error" , JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Opens an error message when not being able to transform input data to xml
     */
    public void errorWhileTransformingToXml() {
        JOptionPane.showMessageDialog(this, "Error while trying to transform into XML", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Opens an error message when not being able to parse XML to String
     */
     public void errorWhileParsingXmlToString() {
        JOptionPane.showMessageDialog(this, "Error while trying to parse XML into String", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Opens a message dialog informing the user that the data has been sent to the server.
     */
    public void sentToServer() {
        JOptionPane.showMessageDialog(this, "Successfully sent data to Server!", "Succes", JOptionPane.INFORMATION_MESSAGE);
    }
}
