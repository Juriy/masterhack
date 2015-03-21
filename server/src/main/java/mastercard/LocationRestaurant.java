package mastercard;

import model.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juriy on 3/22/2015.
 */
public class LocationRestaurant {


    public static List<Restaurant> getRecommendedRestaurants(double latitude, double longitude){
        String queryString = "http://dmartin.org:8021/restaurants/v1/restaurant?Format=XML&PageOffset=0&PageLength=3&Latitude="+latitude+"&Longitude="+longitude+"&Radius=10";
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(queryString, String.class);
            String xml = response.getBody();
            return parseXml(xml);
        }catch(Exception e){
            e.printStackTrace();
            Restaurant rest = new Restaurant();
            rest.setName("Restaurant 23647");
            rest.setDistance(Double.valueOf("0.9320591049747101"));
            ArrayList<Restaurant> result = new ArrayList<>();
            result.add(rest);
            return result;
        }
    }

    private static List<Restaurant> parseXml(String xml) {
        int startName = xml.indexOf("<Name>");
        int endName = xml.indexOf("</Name>");
        String name = xml.substring(startName + 6, endName);
        int startDistance = xml.indexOf("<Distance>");
        int endDistance = xml.indexOf("</Distance>");
        String distance = xml.substring(startDistance + 10, endDistance);

        Restaurant rest = new Restaurant();
        rest.setName(name);
        rest.setDistance(Double.valueOf(distance));

        ArrayList<Restaurant> result = new ArrayList<>();
        result.add(rest);
        return result;

    }


}
