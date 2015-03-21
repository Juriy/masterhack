package db;

import model.Group;
import model.User;

import java.util.*;

/**
 * Created by Juriy on 3/21/2015.
 */
public class Database {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Group> groups = new HashMap<>();



    @Deprecated
    Database() {
    }

    public synchronized void saveUser(User user){
        users.put(user.getUserId(), user);
    }

    public User getUser(String userId){
        return users.get(userId);
    }

    public List<User> getUsersFriends(String userId){
        List<User> friends = new ArrayList<User>();
        //all users in db are friends
        for(User u:users.values()){
            if(!u.getUserId().equals(userId)){
                friends.add(u);
            }
        }
        return friends;
    }

    public void saveGroup(Group group){
        groups.put(group.getGroupId(), group);
    }

    public Group getGroup(String groupId){
        return groups.get(groupId);
    }
}
