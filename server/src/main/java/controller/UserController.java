package controller;

import db.Database;
import model.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Juriy on 3/21/2015.
 */
@RestController()
public class UserController {

    private static int userCounter = 1;
    private Database database = new Database();

    @RequestMapping(value = "/users")
    public String login() {
        System.out.println("Create new user");
        String userId = "user" + userCounter++;
        String userName = userId + "_name";
        String userSurname = userId + "_surname";
        User user = new User();
        user.setUserId(userId);
        user.setSecondName(userSurname);
        user.setFirstName(userName);
        database.saveUser(user);
        return userId;
    }

    @RequestMapping(value = "/users/{userId}/friends", method = RequestMethod.GET)
    public User getFriends(@PathVariable String userId) {
        System.out.println("querying for friend");
        return database.getUser(userId);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User getProfile(@PathVariable String userId) {
        return database.getUser(userId);
    }




}
