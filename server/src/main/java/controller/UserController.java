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
    private Database database;

    @RequestMapping(value = "/users")
    public String login(User userInfo) {
//        userInfo.setUserId("user" + userCounter++);
//        database.saveUser(userInfo);
//        return userInfo.getUserId();
        return "user" + userCounter++;
    }

    @RequestMapping("/users/{userId}")
    public User getProfile(@PathVariable String userId) {
        return database.getUser(userId);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.POST)
    public User addFriends(@PathVariable String userId) {
        return database.getUser(userId);
    }


}
