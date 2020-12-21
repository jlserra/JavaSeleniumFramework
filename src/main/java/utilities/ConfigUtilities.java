package utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ConfigUtilities {

    public String platform;

    public ConfigUtilities() {

        try {
            File inputFile = new File("pom.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("properties");
            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                setPlatform(eElement
                        .getElementsByTagName("platform")
                        .item(0)
                        .getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
