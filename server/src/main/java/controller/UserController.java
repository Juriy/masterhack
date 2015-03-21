package controller;

import com.google.gson.Gson;
import db.Database;
import db.DatabaseFactory;
import model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Juriy on 3/21/2015.
 */
@RestController()
public class UserController {

    private static int userCounter = 10;
    private Database database = DatabaseFactory.getDatabase();
    private Gson gson = new Gson();

    @RequestMapping(value = "/users")
    public @ResponseBody String login() {
        System.out.println("Create new user");
        String userId = "user" + userCounter++;
        String userName = userId + "_name";
        String userSurname = userId + "_surname";
        String userPicture = userId + "_pic";
        User user = new User();
        user.setUserId(userId);
        user.setSecondName(userSurname);
        user.setFirstName(userName);
        database.saveUser(user);
        return gson.toJson(userId);
    }

//    [{"firstName":"firstName","secondName":"surname","pictureUrl":"picUrl","userId":"userId"}]
    @RequestMapping(value = "/users/upload", method = RequestMethod.POST)
    public void upload(@RequestBody Collection<User> users) {
        System.out.println("Uploading new users in db");
        for(User u:users){
            database.saveUser(u);
        }
    }

    @RequestMapping(value = "/users/{userId}/friends", method = RequestMethod.GET)
    public @ResponseBody Collection<User> getFriends(@PathVariable String userId) {
        System.out.println("querying for friend");
        return database.getUsersFriends(userId);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public @ResponseBody User getProfile(@PathVariable String userId) {
        return database.getUser(userId);
    }




}
