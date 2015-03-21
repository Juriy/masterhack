package db;

import model.Group;
import model.Item;
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
        users.put("user1", new User("Andrew", "Scotch", "https://github.com/fluidicon.png", "user1"));
        users.put("user2", new User("Bobby", "Williams", "https://github.com/fluidicon.png", "user2"));
        users.put("user3", new User("Charly", "Chark", "https://github.com/fluidicon.png", "user3"));
        users.put("user4", new User("Danial", "Koh", "https://github.com/fluidicon.png", "user4"));
        users.put("user5", new User("Eagle", "Bold", "https://github.com/fluidicon.png", "user5"));

        Item beer = new Item("item1", 10.0, "Beer");
        Item meal = new Item("item2", 20.0, "Meal");
        Item desert = new Item("item3", 30.0, "Desert");
        Group group = new Group();
        group.setGroupId("group1");
        group.setGroupName("Restaurant");
        group.addItemInCart(beer);
        group.addItemInCart(meal);
        group.addItemInCart(desert);

        group.addUser("user1");
        group.addUser("user2");
        group.addUser("user3");
        group.addUser("user4");

        Group group2 = new Group();
        group2.setGroupId("group2");
        group2.setGroupName("Restaurant2");
        group2.addItemInCart(beer);
        group2.addItemInCart(meal);
        group2.addItemInCart(desert);

        group2.addUser("user1");
        group2.addUser("user2");
        group2.addUser("user3");
        group2.addUser("user4");

        groups.put("group1", group);
        groups.put("group2", group2);

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
