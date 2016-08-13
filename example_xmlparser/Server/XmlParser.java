package Server;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by regrau on 28.06.16.
 * A XML parser which transforms a XML from String form to
 * a document type and can save a serialized object to disc
 * as well as print it to some output.
 */
public class XmlParser {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder;
    Document doc;

    /**
     *  Method which gets a XML as string from a input stream
     *  and parses it to a W3C type document.
     * @param xmlAsString A string from an input stream.
     */
    public void transformStringToXML(BufferedReader xmlAsString) throws IOException, SAXException, ParserConfigurationException {
        docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.parse(new InputSource(xmlAsString));
    }

    /**
     * Method which writes a serialized object to disc.
     */
    public void saveXMLtoFile() throws IOException {
        FileOutputStream f_out = new FileOutputStream("myObject.data");
        ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
        obj_out.writeObject(doc);
    }

    /**
     * Method which gets a serialized object from disc.
     */
    public void getXMLFromDisk() throws IOException, ClassNotFoundException {
            FileInputStream f_in = new FileInputStream("myObject.data");
            ObjectInputStream obj_in = new ObjectInputStream(f_in);
            doc = (Document) obj_in.readObject();
    }

    /**
     * Method which transforms a XML document to String and puts on an outputstream,
     * in this case the console.
     */
    public void printXMLToConsole() throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
        System.err.println("Cannot Print Object");
    }

    /**
     * This is just a stub for the purposes of the exercise.
     * As we are using the w3c xml lib we do not have to validate our XML files as
     * all validation is already handled by the library.
     * All potential package loss is handled by TCP and the XML we receive is build
     * by the library in the Client class.
     */
    public void validateXML(){


    }

}
