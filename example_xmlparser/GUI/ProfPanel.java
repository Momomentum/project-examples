package GUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * Created by momomentum on 04.07.16.
 */
public class ProfPanel extends JPanel {

    /**
     * Text fields that the user puts data into.
     */
    private JTextField surnameF, givenF, birthF, streetF, streetNoF, zipF, cityF, profIdF, facultyF;

    /**
     * Labels so the user knows what he has to enter.
     */
    private JLabel surnameL, givenL, birthL, streetL, streetNoL, zipL, cityL, profIdL, facultyL;

    /**
     * Button that is used to send data to server.
     */
    private JButton sendB;

    /**
     * Creates a panel for creation of a new professor xml file.
     */
    public ProfPanel() {
        setLayout(new MigLayout());
        addFields();
        addButtons();
    }

    /**
     * Adds all the necessary fields and labels to the panel.
     */
    private void addFields() {
        surnameL = new JLabel("Name");
        givenL = new JLabel("Vorname");
        birthL = new JLabel("Geburtsdatum");
        streetL = new JLabel("Straße");
        streetNoL = new JLabel("Hausnummer");
        zipL = new JLabel("PLZ");
        cityL = new JLabel("Stadt");
        profIdL = new JLabel("Personalnummer");
        facultyL = new JLabel("Fakultät");
        surnameF = new JTextField(20);
        givenF = new JTextField(20);
        birthF = new JTextField(20);
        streetF = new JTextField(20);
        streetNoF = new JTextField(20);
        zipF = new JTextField(20);
        cityF = new JTextField(20);
        profIdF = new JTextField(20);
        facultyF = new JTextField(20);
        this.add(surnameL);
        this.add(surnameF, "wrap");
        this.add(birthL);
        this.add(birthF, "wrap");
        this.add(streetL);
        this.add(streetF, "wrap");
        this.add(streetNoL);
        this.add(streetNoF, "wrap");
        this.add(zipL);
        this.add(zipF, "wrap");
        this.add(cityL);
        this.add(cityF, "wrap");
        this.add(profIdL);
        this.add(profIdF, "wrap");
        this.add(facultyL);
        this.add(facultyF, "wrap");
    }

    /**
     * Adds all the buttons to the panel.
     */
    private void addButtons() {
        sendB = new JButton("Zum Server senden");
        this.add(sendB);
    }

    /**
     * Getter method for the send button.
     * @return      The send button.
     */
    public JButton getSendButton() {
        return this.sendB;
    }

    /**
     * Getter method for the surname textfield.
     * @return      Surname textfield.
     */
    public JTextField getSurnameF() {
        return surnameF;
    }

    /**
     * Getter method for the given name textfield.
     * @return      Given name textfield.
     */
    public JTextField getGivenF() {
        return givenF;
    }

    /**
     * Getter method for the birth textfield.
     * @return      Birth textfield.
     */
    public JTextField getBirthF() {
        return birthF;
    }

    /**
     * Getter method for the street textfield
     * @return      Street textfield.
     */
    public JTextField getStreetF() {
        return streetF;
    }

    /**
     * Getter method for the street number textfield.
     * @return      Street number textfield.
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
     * Getter method for the professor id textfield.
     * @return      Professor id textfield.
     */
    public JTextField getProfIdF() {
        return profIdF;
    }

    /**
     * Getter method for the faculty textfield.
     * @return      Faculty texfield.
     */
    public JTextField getFacultyF() {
        return facultyF;
    }
}
