package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Juriy on 3/21/2015.
 */
public class Cart {

    private Map<String, Item> itemsDB = new HashMap<>();
    private List<Item> itemsToPay = new ArrayList<Item>();
    private List<Item> itemPayed = new ArrayList<>();
    private String cartId;

    public void addItem(Item item){
        System.out.println("Add item " + item+" to cart " + cartId);
        itemsToPay.add(item);
        itemsDB.put(item.getItemId(), item);
    }

    public Item getItemInfo(String itemId){
        return itemsDB.get(itemId);
    }

    public List<Item> getItemsToPay(){
        return itemsToPay;
    }

    public double getTotalBill(){
        double total = 0;
        for(Item i:itemsToPay){
            total = total + i.getItemPrice();
        }
        for(Item i:itemPayed){
            total=total + i.getItemPrice();
        }
        return total;
    }

    public double getRemainAmount(){
        double total = 0;
        for(Item i:itemsToPay) {
            total = total + i.getItemPrice();
        }
            return total;
    }

    public void setItemPayed(String itemId){
        Item item = itemsDB.get(itemId);
        itemPayed.add(item);
        itemsToPay.remove(item);
    }



    public List<Item> getItemPayed() {
        return itemPayed;
    }

    public String getCartId() {
        return cartId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId='" + cartId + '\'' +
                '}';
    }
}
