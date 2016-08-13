package GUI;

import Client.*;
import Data.Professor;
import Data.Student;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by momomentum on 05.07.16.
 * Controller for the Client GUI. Receives data from the Users input in the frame and sends it via TCP to a Server.
 */
public class Controller {

    /**
     * Reference to the frame that the application is using.
     */
    private MainFrame frame;

    /**
     * Reference to the professor panel that the frame contains.
     */
    private ProfPanel profPanel;

    /**
     * Reference to the student panel that the frame contains.
     */
    private StudentPanel studPanel;

    /**
     * Instance of an XmlCreator in order to transform data into xml and prepare xml for sending as bytes.
     */
    private XmlCreator xmlCreator;

    /**
     * Constructor. On initialisation, sets all the necessary references and instances, then adds listeners
     * on the buttons in the panels.
     * @param frame     The frame that the application is using.
     */
    public Controller(MainFrame frame) {
        this.frame = frame;
        this.profPanel = frame.getProfPanel();
        this.studPanel = frame.getStudentPanel();
        this.xmlCreator = new XmlCreator();
        frame.getProfPanel().getSendButton().addActionListener(new ProfListener());
        frame.getStudentPanel().getSendButton().addActionListener(new StudentListener());
    }

    /**
     * Send Data to the Server.
     * @param dataPackage       Data, that is supposed to be sent to the server.
     */
     private void sendPackage(String dataPackage) {
        try {
            Client client = new Client();
            client.sendXml(dataPackage);
        } catch (IOException e) {
            frame.errorWhileSending();
        }
    }


    /**
     * Listener for Button that triggers sending data from the professor panel to the Server
     */
    class ProfListener implements ActionListener {


        /**
         * When button is pressed, all the fields from the professor panel are evaluated and if valid
         * send to the server. The user is informed if data is missing.
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Professor prof;
            String given = profPanel.getGivenF().getText();
            String surname = profPanel.getSurnameF().getText();
            String birth = profPanel.getBirthF().getText();
            String street = profPanel.getStreetF().getText();
            String streetNo = profPanel.getStreetNoF().getText();
            String zip = profPanel.getZipF().getText();
            String city = profPanel.getCityF().getText();
            String profId = profPanel.getProfIdF().getText();
            String faculty = profPanel.getFacultyF().getText();
            String missing = "";
            if(given.equals("")){
                if(missing.equals("")){
                    missing = "Vorname";
                } else {
                    missing += ", Vorname";
                }
            }
            if(surname.equals("")){
                if(missing.equals("")){
                    missing = "Nachname";
                } else {
                    missing += ", Nachname";
                }
            }
            if(birth.equals("")){
                if(missing.equals("")){
                    missing = "Geburtsdatum";
                } else {
                    missing += ", Geburtsdatum";
                }
            }
            if(street.equals("")){
                if(missing.equals("")){
                    missing = "Straße";
                } else {
                    missing += ", Straße";
                }
            }
            if(streetNo.equals("")){
                if(missing.equals("")){
                    missing = "Hausnummer";
                } else {
                    missing += ", Hausnummer";
                }
            }
            if(zip.equals("")){
                if(missing.equals("")){
                    missing = "PLZ";
                } else {
                    missing += ", PLZ";
                }
            }
            if(city.equals("")){
                if(missing.equals("")){
                    missing = "Stadt";
                } else {
                    missing += ", Stadt";
                }
            }
            if(profId.equals("")){
                if(missing.equals("")){
                    missing = "Personalnummer";
                } else {
                    missing += ", Personalnummer";
                }
            }
            if(faculty.equals("")){
                if(missing.equals("")){
                    missing = "Fachbereich";
                } else {
                    missing += ", Fachbereich";
                }
            }

            if(missing.equals("")) {
                prof = new Professor(given, surname, birth, street + ", " +  streetNo, zip, profId, city, faculty);
                try {
                    xmlCreator.createProfXml(prof);
                    String profPackage = xmlCreator.transformXMLToString();
                    sendPackage(profPackage);
                    frame.sentToServer();
                } catch (ParserConfigurationException e1) {
                    frame.errorWhileTransformingToXml();
                } catch (TransformerException e1) {
                    frame.errorWhileParsingXmlToString();
                }
            } else {
                missing += " ";
                frame.missingFields(missing, "Professor");
            }

        }
    }

    /**
     * Listener for Button that triggers sending data from the student panel to the Server
     */
    class StudentListener implements ActionListener {

        /**
         * When button is pressed, all the fields from the professor panel are evaluated and if valid
         * send to the server. The user is informed if data is missing.
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Student student;
            String given = studPanel.getGivenF().getText();
            String surname = studPanel.getSurnameF().getText();
            String street = studPanel.getStreetF().getText();
            String streetNo = studPanel.getStreetNoF().getText();
            String zip = studPanel.getZipF().getText();
            String city = studPanel.getCityF().getText();
            String studId = studPanel.getStudIdF().getText();
            String subject = studPanel.getSubjectF().getText();
            String semester = studPanel.getSemesterF().getText();
            String missing = "";
            if(given.equals("")){
                if(missing.equals("")){
                    missing = "Vorname";
                } else {
                    missing = missing + ", Vorname";
                }
            }
            if(surname.equals("")){
                if(missing.equals("")){
                    missing = "Nachname";
                } else {
                    missing = missing + ", Nachname";
                }
            }
            if(street.equals("")){
                if(missing.equals("")){
                    missing = "Straße";
                } else {
                    missing = missing + ", Straße";
                }
            }
            if(streetNo.equals("")){
                if(missing.equals("")){
                    missing = "Hausnummer";
                } else {
                    missing = missing + ", Hausnummer";
                }
            }
            if(zip.equals("")){
                if(missing.equals("")){
                    missing = "PLZ";
                } else {
                    missing = missing + ", PLZ";
                }
            }
            if(city.equals("")){
                if(missing.equals("")){
                    missing = "Stadt";
                } else {
                    missing = missing + ", Stadt";
                }
            }
            if(studId.equals("")){
                if(missing.equals("")){
                    missing = "Matrikelnummer";
                } else {
                    missing = missing + ", Matrikelnummer";
                }
            }
            if(subject.equals("")){
                if(missing.equals("")){
                    missing = "Fachrichtung";
                } else {
                    missing = missing + ", Fachrichtung";
                }
            }
            if(semester.equals("")){
                if(missing.equals("")){
                    missing = "Fachsemester";
                } else {
                    missing = missing +  ", Fachsemester";
                }
            }
            if(missing.equals("")) {
                student = new Student(given, surname, street + ", " + streetNo, zip, city, subject, semester);
                try {
                    xmlCreator.createStudentXml(student);
                    String studentPackage = xmlCreator.transformXMLToString();
                    sendPackage(studentPackage);
                    frame.sentToServer();
                } catch (ParserConfigurationException e1) {
                    frame.errorWhileTransformingToXml();
                } catch (TransformerException e1) {
                    frame.errorWhileParsingXmlToString();
                }
            } else {
                missing = missing + " ";
                frame.missingFields(missing, "Student");
            }
        }
    }
}
