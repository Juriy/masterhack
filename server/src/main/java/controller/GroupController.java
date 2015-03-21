package controller;

import db.Database;
import db.DatabaseFactory;
import model.Group;
import model.Item;
import model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Juriy on 3/21/2015.
 */
@RestController()
public class GroupController {
    private static int groupCounter = 1;
    private Database database = DatabaseFactory.getDatabase();

    @RequestMapping(value = "/groups",method = RequestMethod.POST)

    public @ResponseBody String createGroup() {
        System.out.println("Create new group");
        Group group = new Group();
        String groupId = "group" + groupCounter++;
        group.setGroupId(groupId);
        database.saveGroup(group);
        return groupId;
    }

    @RequestMapping(value = "/groups/{groupId}",method = RequestMethod.POST, consumes="application/json")
    public void addUsersToGroup(@PathVariable String groupId, @RequestBody Collection<String> users) {
        System.out.println("add users to group");
        Group group = database.getGroup(groupId);
        group.addUser(users);
        database.saveGroup(group);
    }

    @RequestMapping(value = "/groups/{groupId}",method = RequestMethod.GET)
    public @ResponseBody Collection<User> getGroupUsers(@PathVariable String groupId) {
        System.out.println("add users to group");
        Group group = database.getGroup(groupId);
        List<User> users = new ArrayList<>();
        for(String userId:group.getUsers()){
            users.add(database.getUser(userId));
        }
        return users;
    }

    @RequestMapping(value = "/groups/{groupId}/items",method = RequestMethod.POST)
    public void addItemToCart(@PathVariable String groupId, @RequestBody Item item) {
        System.out.println("add item to group's cart");
        Group group = database.getGroup(groupId);
        group.addItemInCart(item);
    }
}
