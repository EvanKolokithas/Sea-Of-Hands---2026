package seaOfHands;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Inventory {

    //vars
    private final List<Item> items;
    private final int maxItems;

    //constructor
    public Inventory() {
        items = new ArrayList<>();
        maxItems = 10;
    }

    //Add item if space allows
    public boolean add(Item item) {
        if (items.size() >= maxItems) {
            System.out.println("Your inventory is full. Cannot add " + item.getName());
            return false;
        }
        items.add(item);
        System.out.println(item.getName() + " added to your inventory.");
        return true;
    }

    public void remove(Item item) {
        if (items.remove(item)) {
            System.out.println(item.getName() + " removed from your inventory.");
        }
    }

    public boolean isFull() {
        return items.size() >= maxItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public void printInventory() {
        UI.printInventory(items);
    }
}