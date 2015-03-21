package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juriy on 3/21/2015.
 */
public class Cart {

    private List<Item> itemList = new ArrayList<Item>();
    private String cartId;

    public void addItem(Item item){
        System.out.println("Add item " + item+" to cart " + cartId);
        itemList.add(item);
    }

    public List<Item> getItems(){
        return itemList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId='" + cartId + '\'' +
                '}';
    }
}
