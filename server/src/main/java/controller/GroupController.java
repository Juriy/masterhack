package controller;

import com.google.gson.Gson;
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
    private static int groupCounter = 10;
    private Database database = DatabaseFactory.getDatabase();

    private Gson gson = new Gson();

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
    public @ResponseBody Group getGroupDetails(@PathVariable String groupId) {
        System.out.println("return group info");
        Group group = database.getGroup(groupId);
        return group;
    }


    @RequestMapping(value = "/groups/{groupId}/users",method = RequestMethod.GET)
    public @ResponseBody Collection<User> getGroupUsers(@PathVariable String groupId) {
        System.out.println("get group's users");
        Group group = database.getGroup(groupId);
        List<User> users = new ArrayList<>();
        for(String userId:group.getUsers()){
            users.add(database.getUser(userId));
        }
        return users;
    }

    @RequestMapping(value = "/groups/{groupId}/users/{userId}/{amount}",method = RequestMethod.POST)
    public void payAmount(@PathVariable double amount, @PathVariable String groupId, @PathVariable String userId) {
        System.out.println("User pays amount");
        Group group = database.getGroup(groupId);
        group.userPaysSplit(userId, amount);
        database.saveGroup(group);
    }

    @RequestMapping(value = "/groups/{groupId}/users/{userId}",method = RequestMethod.POST)
    public void payItems(@RequestBody Collection<String> itemIds, @PathVariable String groupId, @PathVariable String userId) {
        System.out.println("User pays amount");
        Group group = database.getGroup(groupId);
        group.userPaysItems(userId, itemIds);
        database.saveGroup(group);
    }

    /**
     * add item in group's cart
     * @param groupId
     * @param item
     */
    @RequestMapping(value = "/groups/{groupId}/items",method = RequestMethod.POST)
    public void addItemToCart(@PathVariable String groupId, @RequestBody Item item) {
        System.out.println("add item to group's cart");
        Group group = database.getGroup(groupId);
        group.addItemInCart(item);
    }

    /**returns list of items to be payed
     *
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/groups/{groupId}/items",method = RequestMethod.GET)
    public @ResponseBody Collection<Item> getItems(@PathVariable String groupId) {
        System.out.println("add item to group's cart");
        Group group = database.getGroup(groupId);
        return group.getCart().getItemsToPay();
    }

    @RequestMapping(value = "/groups/{groupId}/totalBill",method = RequestMethod.GET)
    public @ResponseBody String getTotalBill(@PathVariable String groupId) {
        Group group = database.getGroup(groupId);
        double total = group.getTotalBill();
        return gson.toJson(total);
    }

    @RequestMapping(value = "/groups/{groupId}/remainBill",method = RequestMethod.GET)
    public @ResponseBody String getRemainBill(@PathVariable String groupId) {
        Group group = database.getGroup(groupId);
        double total = group.getRemainBill();
        return gson.toJson(total);
    }
}
