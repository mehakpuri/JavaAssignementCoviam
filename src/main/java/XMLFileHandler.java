import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLFileHandler implements MyFileHandler {
    private int MAX_SIZE=100;
    private int index=0;
    File inputFile;
    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    Document doc;
    NodeList nList;

    public XMLFileHandler(String fileName) {
        inputFile = new File(fileName);
        try {
            dBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            doc = dBuilder.parse(inputFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();
        nList = doc.getElementsByTagName("employee");
    }

    @Override
    public Employee read() {
        Employee employee=new Employee();
        try {

            Node nNode = nList.item(this.index);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                employee.setFirstName(eElement
                        .getElementsByTagName("firstName")
                        .item(0)
                        .getTextContent());
                employee.setLastName(eElement
                        .getElementsByTagName("lastName")
                        .item(0)
                        .getTextContent());

                double experience=Double.parseDouble(eElement
                        .getElementsByTagName("experience")
                        .item(0)
                        .getTextContent());

                Date date=new SimpleDateFormat("dd/MM/yyyy").parse(eElement
                        .getElementsByTagName("dateOfBirth")
                        .item(0)
                        .getTextContent());
                employee.setExperience(experience);
                employee.setDateOfBirth(date);
            }
            this.index++;
            if(this.index==100){
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return employee;
    }


    @Override
    public void write(Employee employee) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory
                    .newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder.parse("employee2.xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Element root = document.getDocumentElement();
        Element rootElement = document.getDocumentElement();

        Collection<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee);

        for (Employee i : employeeList) {
            Element employee1 = document.createElement("employee");
            rootElement.appendChild(employee1);

            Element firstNname = document.createElement("firstName");
            firstNname.appendChild(document.createTextNode(i.getFirstName()));
            employee1.appendChild(firstNname);

            Element lastName = document.createElement("lastName");
            lastName.appendChild(document.createTextNode(i.getLastName()));
            employee1.appendChild(lastName);

            Element date = document.createElement("dateOfBirth");
            date.appendChild(document.createTextNode(i.getDateOfBirth().toString()));
            employee1.appendChild(date);

            Element experience = document.createElement("experience");
            experience.appendChild(document.createTextNode(Double.toString(i.getExperience())));
            employee1.appendChild(experience);

            rootElement.appendChild(employee1);
        }

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        StreamResult result = new StreamResult("employee2.xml");
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

}
