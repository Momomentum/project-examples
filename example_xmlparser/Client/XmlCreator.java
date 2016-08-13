package Client;


import Data.Person;
import Data.Professor;
import Data.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by regrau on 28.06.16.
 *
 * This class creates XML file from a given object.
 * It takes one of two plain old java objects(POJOs)
 * of type Professor or Student and puts their
 * attributes into a XML structure. Each node
 * represents one attribute of the POJO.
 * It can transform the XML document into a String, which
 * than can be send over e.g. TCP.
 */
public class XmlCreator {
    DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder icBuilder;
    Document doc;

    /**
     * Method to create a XML document with a professors data.
     * @param student Object of which to create a XML
     */
    public void createStudentXml(Student student) throws ParserConfigurationException {
        icBuilder = icFactory.newDocumentBuilder();
        doc = icBuilder.newDocument();
        Element mainRootElement = doc.createElement("PersonalData");
        doc.appendChild(mainRootElement);
        mainRootElement.appendChild(getStudentNode(doc, student));
    }

    /**
     * Method to create a XML document with a professors data.
     * @param prof Object of which to create a XML.
     */
    public void createProfXml(Professor prof) throws ParserConfigurationException {
        icBuilder = icFactory.newDocumentBuilder();
        doc = icBuilder.newDocument();
        Element mainRootElement = doc.createElement("PersonalData");
        doc.appendChild(mainRootElement);
        mainRootElement.appendChild(getProfNode(doc, prof));
    }

    /**
     * Method to get the complete student node for the XML document.
     * @param doc Document to which to add the node.
     * @param student Specific type of person.
     * @return Whole student node with common and specific data.
     */
    private static Node getStudentNode(Document doc, Student student){
        Element studentNode = doc.createElement("Student");
        studentNode.appendChild(getCommonDataNode(doc, student));
        studentNode.appendChild(getSpecificDataNode(doc, student));
        return studentNode;
    }

    /**
     * Method to get the complete professor node for the XML document.
     * @param doc Document to which to add the node.
     * @param prof Specific type of person.
     * @return Whole professor node with common and specific data.
     */
    private static Node getProfNode (Document doc, Professor prof) {
        Element profNode = doc.createElement("Professor");
        profNode.appendChild(getCommonDataNode(doc, prof));
        profNode.appendChild(getSpecificDataNode(doc, prof));
        return profNode;
    }

    /**
     * Wrapper Method to add common personal data to the document.
     * @param doc Document which to which to add the node.
     * @param person Person supertype.
     * @return Node which contains the common data.
     */
    private static Node getCommonDataNode(Document doc, Person person){
        Element persNode = doc.createElement("CommonData");
        persNode.appendChild(getNodeElements(doc, "Name", person.getName()));
        persNode.appendChild(getNodeElements(doc, "SurName", person.getSurname()));
        persNode.appendChild(getNodeElements(doc, "Address", person.getAddress()));
        persNode.appendChild(getNodeElements(doc, "ZipCode", person.getZipcode()));
        persNode.appendChild(getNodeElements(doc, "City", person.getCity()));
        return persNode;
    }

    /**
     * Polymorphic wrapper method to crate a Node which contains person
     * specific Data.
     * @param doc Document to which to add the Node.
     * @param prof Specific type of person.
     * @return Node which contains the specific Data.
     */
    private static Node getSpecificDataNode(Document doc, Professor prof){
        Element specificDataNode = doc.createElement("SpecificData");
        specificDataNode.appendChild(getNodeElements(doc, "Birthday", prof.getBirthday()));
        specificDataNode.appendChild(getNodeElements(doc, "PersonalId", prof.getPersonalId()));
        specificDataNode.appendChild(getNodeElements(doc, "Subject", prof.getSubject()));
        return specificDataNode;
    }

    /**
     * Polymorphic helper method to crate a Node which contains person
     * specific Data.
     * @param doc Document to which to add the Nodes.
     * @param student Specific type of person.
     * @return Node which contains the specific Data.
     */
    private static Node getSpecificDataNode(Document doc, Student student){
        Element specificDataNode = doc.createElement("SpecificData");
        specificDataNode.appendChild(getNodeElements(doc, "Subject", student.getSubject()));
        specificDataNode.appendChild(getNodeElements(doc, "CurrentSemester", student.getCurrentSemester()));
        return specificDataNode;
    }

    /**
     * Helper Function to create one XML Tag.
     * @param doc Document to with the XML structure.
     * @param name Tag name.
     * @param value Value which to put into the brackets.
     * @return done node with Tags and value.
     */
    private static Node getNodeElements(Document doc,String name, String value){
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    /**
     * Method which transforms the prior created document into a String
     * @return A String representation of the XML.
     */
    public String transformXMLToString() throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult xmlAsString = new StreamResult(writer);
        transformer.transform(source, xmlAsString);
        return writer.toString();
    }
}
