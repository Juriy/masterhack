package db;

import model.User;

import java.util.Map;

/**
 * Created by Juriy on 3/21/2015.
 */
public class Database {
    Map<String, User> users;

    public synchronized void saveUser(User user){
        users.put(user.getUserId(), user);
    }

    public User getUser(String userId){
        return users.get(userId);
    }
}
