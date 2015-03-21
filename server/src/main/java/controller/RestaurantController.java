package controller;

import mastercard.LocationRestaurant;
import model.Group;
import model.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Juriy on 3/22/2015.
 */
@RestController()
public class RestaurantController {

    @RequestMapping(value = "/restaurants",method = RequestMethod.GET)
    public @ResponseBody
    Restaurant getClosestRestaurant(@RequestParam String latitude, @RequestParam String longitude) {
        System.out.println("Checking for restaurants");
        List<Restaurant> rests = LocationRestaurant.getRecommendedRestaurants(Double.valueOf(latitude), Double.valueOf(longitude));
        return rests.get(0);
    }
}
