package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Juriy on 3/21/2015.
 */
public class Group {

    private Collection<String> usersId;
    private Map<String, Double> usersBill;
    private String groupId;

    public Group() {
        usersId = new ArrayList<>();
    }

    public Collection<String> getUsers() {
        return usersId;
    }

    public void addUser(String user){
        usersId.add(user);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void addUser(Collection<String> newUsers) {
        usersId.addAll(newUsers);
    }
}
