package seaOfHands;

import java.util.List;
import java.util.ArrayList;


public class Inventory {
	
	//vars
	final private List<Item> items;
	
	//constructor
	public Inventory() {
		items = new ArrayList<>();
	}
	
	//Methods
	public void add(Item item) {
		items.add(item);
	}
	
	public void remove(Item item) {
		items.remove(item);

	}
}
