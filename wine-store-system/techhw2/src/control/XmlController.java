package control;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import entity.Manufacturer;
import entity.WineBottle;

import java.util.ArrayList;
import java.util.List;

public class XmlController {

    public static ArrayList<Manufacturer> parseManufacturers(String filePath) {
    	ArrayList<Manufacturer> manufacturers = new ArrayList<>();

        try {
            // Initialize XML parser
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);

            // Normalize XML structure
            document.getDocumentElement().normalize();

            // Get all Manufacturer elements
            NodeList manufacturerNodes = document.getElementsByTagName("Manufacturer");

            for (int i = 0; i < manufacturerNodes.getLength(); i++) {
                Node node = manufacturerNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Create Manufacturer object and populate fields
                    Manufacturer manufacturer = new Manufacturer();
                    manufacturer.setName(element.getElementsByTagName("Name").item(0).getTextContent());
                    manufacturer.setPhone(element.getElementsByTagName("Phone").item(0).getTextContent());
                    manufacturer.setAddress(element.getElementsByTagName("Address").item(0).getTextContent());
                    manufacturer.setEmail(element.getElementsByTagName("Email").item(0).getTextContent());

                    // Add to list
                    manufacturers.add(manufacturer);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return manufacturers;
    }

    public static ArrayList<WineBottle> parseWines(String filePath) {
    	ArrayList<WineBottle> wines = new ArrayList<>();

        try {
            // Initialize XML parser
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);

            // Normalize XML structure
            document.getDocumentElement().normalize();

            // Get all Wine elements
            NodeList wineNodes = document.getElementsByTagName("Wine");

            for (int i = 0; i < wineNodes.getLength(); i++) {
                Node node = wineNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Create WineBottle object and populate fields
                    WineBottle wine = new WineBottle();
                    wine.setCatalogNumber(element.getElementsByTagName("CatalogNumber").item(0).getTextContent());
                    wine.setManufacturerID(Integer.parseInt(element.getElementsByTagName("ManufacturerID").item(0).getTextContent()));
                    wine.setName(element.getElementsByTagName("Name").item(0).getTextContent());
                    wine.setDescription(element.getElementsByTagName("Description").item(0).getTextContent());
                    wine.setProductionYear(Integer.parseInt(element.getElementsByTagName("ProductionYear").item(0).getTextContent()));
                    wine.setPricePerBottle(Double.parseDouble(element.getElementsByTagName("PricePerBottle").item(0).getTextContent()));
                    wine.setSweetnessLevel(element.getElementsByTagName("SweetnessLevel").item(0).getTextContent());
                    wine.setProductImage(element.getElementsByTagName("ProductImage").item(0).getTextContent());
                    wine.setWineSerialNum(wine.getManufacturerID() + "-" + wine.getCatalogNumber());
                    // Add to list
                    wines.add(wine);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return wines;
    }

}
