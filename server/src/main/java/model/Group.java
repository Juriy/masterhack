package model;

import java.util.*;

/**
 * Created by Juriy on 3/21/2015.
 */
public class Group {

    private Collection<String> usersId;
    private Map<String, Double> amountPayedByUsers = new HashMap<>();
    private Map<String, Collection<Item>> itemsPayedByUser = new HashMap<>();
    private Map<String, Boolean> usersLeft = new HashMap<>();
    private Cart groupCart;
    private String groupId;


    public Group() {
        usersId = new ArrayList<>();
        groupCart = new Cart();
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
        for(String userId:newUsers){
            amountPayedByUsers.put(userId, 0.0);
        }
    }

    public void addItemInCart(Item item){
        groupCart.addItem(item);
    }

    public Cart getCart(){
        return groupCart;
    }

    public void resetCart(){
        groupCart = new Cart();
    }

    public Double getTotalBill(){
        return groupCart.getTotalBill();
    }

    public void userLeft(String userId){
        usersLeft.put(userId, true);
    }

    public void userPaysSplit(String userId, Double amount){
        amountPayedByUsers.put(userId, amount);
    }

    public void userPaysItems(String userId, Collection<String> itemIds){
        double totalPayed = 0.0;
        Collection<Item> items = new ArrayList<Item>();
        for(String i:itemIds){
            Item item = groupCart.getItemInfo(i);
            items.add(item);
            totalPayed = totalPayed + item.getItemPrice();
            groupCart.setItemPayed(i);
            System.out.println("User "+userId+" payed for item " + i);
        }
        if(itemsPayedByUser.get(userId)==null){
            itemsPayedByUser.put(userId, items);
        }else{
            itemsPayedByUser.get(userId).addAll(items);
        }
    }

    public double getRemainBill(){
        double remain = getTotalBill();
        for(Double d:amountPayedByUsers.values()){
            remain = remain - d;
        }
        for(Collection<Item> items:itemsPayedByUser.values()){
            for(Item i:items){
                remain = remain-i.getItemPrice();
            }
        }
        return remain;
    }

    public int numberOfActiveUsers(){
        int number =0;
        for(Boolean left:usersLeft.values()){
            if(!left){
                number++;
            }
        }
        return number;
    }
}
