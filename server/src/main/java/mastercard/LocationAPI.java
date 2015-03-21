package mastercard;

import mastercard.connection.Connector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Created by Juriy on 3/21/2015.
 */
public class LocationAPI {

    private Connector connector = new Connector();

    public void getATMsByCity() {
        final String pageOffset = "0";
        final String pageLength = "10";
        final String city = "Hongkong";
        final String countrySubdivision = "IL";
        final String country = "HK";

        try {
            String endPoint = "https://sandbox.api.mastercard.com/atms/v1/atm?Format=XML" +
                    "&PageOffset=" + URLEncoder.encode(pageOffset, "UTF-8") +
                    "&PageLength=" + URLEncoder.encode(pageLength, "UTF-8") +
                    "&City=" + URLEncoder.encode(city, "UTF-8") +
                    "&CountrySubdivision=" + URLEncoder.encode(countrySubdivision, "UTF-8") +
                    "&Country=" + URLEncoder.encode(country, "UTF-8");

            HttpURLConnection conn = connector.createOpenAPIConnection(endPoint, null);

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(conn.getInputStream());

            if (doc.getFirstChild().getNodeName().equals("Atms")) {

                NodeList atms = doc.getElementsByTagName("Atm");
                System.out.println("Number of ATMs By City is: " + atms.getLength());

                for (int i = 0; i < atms.getLength(); i++) {
                    Node atmNode = atms.item(i);
                    System.out.println("ATM: " + atmNode.getTextContent());
                }

            } else {
                if (doc.getFirstChild().getNodeName().equals("Errors")) {
                    System.out.println("Error: " + doc.getElementsByTagName("Description").item(0).getTextContent());
                } else {
                    System.out.println("Unknown Error");
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
