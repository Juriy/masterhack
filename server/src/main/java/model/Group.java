package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Juriy on 3/21/2015.
 */
public class Group {

    private Collection<User> users;
    private String groupId;

    public Group() {
        users = new ArrayList<>();
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
