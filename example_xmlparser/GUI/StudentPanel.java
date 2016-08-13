package GUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * Created by momomentum on 04.07.16.
 */
public class StudentPanel extends JPanel {

    /**
     * Textfields that the user enters student data into.
     */
    private JTextField surnameF, givenF, streetF, streetNoF, zipF, cityF, studIdF, subjectF, semesterF;

    /**
     * Labels so the user knows what he has to enter.
     */
    private JLabel surnameL, givenL, streetL, streetNoL, zipL, cityL, studIdL, subjectL, semesterL;

    /**
     * The button that sends the data to a server.
     */
    private JButton sendB;

    /**
     * Constructor. Creates a student panel and fills it with labels, fields and a send button.
     */
    public StudentPanel() {
        setLayout(new MigLayout());
        addFields();
        addButtons();
    }

    /**
     * Add all the necessary fields and labels to the panel.
     */
    private void addFields() {
        surnameL = new JLabel("Name");
        givenL = new JLabel("Vorname");
        streetL = new JLabel("Straße");
        streetNoL = new JLabel("Hausnummer");
        zipL = new JLabel("PLZ");
        cityL = new JLabel("Stadt");
        studIdL = new JLabel("Matrikel Nummer");
        subjectL = new JLabel("Fachrichtung");
        semesterL = new JLabel("Fachsemester");
        surnameF = new JTextField(20);
        givenF = new JTextField(20);
        streetF = new JTextField(20);
        streetNoF = new JTextField(20);
        zipF = new JTextField(20);
        cityF = new JTextField(20);
        studIdF = new JTextField(20);
        subjectF = new JTextField(20);
        semesterF = new JTextField(20);
        this.add(surnameL);
        this.add(surnameF, "wrap");
        this.add(givenL);
        this.add(givenF, "wrap");
        this.add(streetL);
        this.add(streetF, "wrap");
        this.add(streetNoL);
        this.add(streetNoF, "wrap");
        this.add(zipL);
        this.add(zipF, "wrap");
        this.add(cityL);
        this.add(cityF, "wrap");
        this.add(studIdL);
        this.add(studIdF, "wrap");
        this.add(subjectL);
        this.add(subjectF, "wrap");
        this.add(semesterL);
        this.add(semesterF, "wrap");
    }

    /**
     * Add buttons to the panel.
     */
    private void addButtons() {
        sendB = new JButton("Zum Server senden");
        this.add(sendB);
    }

    /**
     * Getter method for the send button.
     * @return      Send button.
     */
    public JButton getSendButton() {
        return this.sendB;
    }

    /**
     * Getter method for the semester textfield.
     * @return      Semester textfield.
     */
    public JTextField getSemesterF() {
        return semesterF;
    }

    /**
     * Getter method for the surname textfield.
     * @return      Surname textfield.
     */
    public JTextField getSurnameF() {
        return surnameF;
    }

    /**
     * Getter method for the given name textfield
     * @return      Given name texfield.
     */
    public JTextField getGivenF() {
        return givenF;
    }

    /**
     * Getter method for the street texfield
     * @return      Street textfield.
     */
    public JTextField getStreetF() {
        return streetF;
    }

    /**
     * Getter method for the street number textfield.
     * @return
     */
    public JTextField getStreetNoF() {
        return streetNoF;
    }

    /**
     * Getter method for the zip code textfield.
     * @return      Zip code textfield.
     */
    public JTextField getZipF() {
        return zipF;
    }

    /**
     * Getter method for the city textfield.
     * @return      City textfield.
     */
    public JTextField getCityF() {
        return cityF;
    }

    /**
     * Getter method for the student id textfield
     * @return      Student id textfield.
     */
    public JTextField getStudIdF() {
        return studIdF;
    }

    /**
     * Getter method for the subject texfied.
     * @return      Subject textfield.
     */
    public JTextField getSubjectF() {
        return subjectF;
    }
}
