package seaOfHands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Environment extends POI {

    private final List<Item> possibleLoot;
    
    
    public Environment(String name, String desc, boolean camp, int sanity, boolean loot, List<Consumable> consumables) {
        super(name, desc, camp, loot, sanity);
    
        // Environments only give consumables
        possibleLoot = new ArrayList<>();
        possibleLoot.addAll(consumables);
        
    }

    @Override
    public void onArrival() {

        // Chance to become campable if it wasn't
        if (!canCamp) {
        	canCamp = Math.random() <= 0.2; // 20% chance
        }
        
        if (!lootable) {
        	lootable = Math.random() <= .6; //60% chance
        	
        }
        // Reset used flag for the turn
        used = false;
    }

    public Item getRandomLoot() {
    	
        if (!this.isLootable()|| possibleLoot.isEmpty()) {
        	return null;
        }

        // Only allow items with sanity ≤ current level and ≥ level - 1
        List<Item> validItems = possibleLoot.stream()
                .filter(item -> item.getSanity() <= sanityLevel && item.getSanity() >= sanityLevel - 1)
                .collect(Collectors.toList());

        if (validItems.isEmpty()) return null;

        int index = (int) (Math.random() * validItems.size());
        return validItems.get(index);
    }

    @Override
    public String getInfo() {
        return name + " : " + desc + " | Campable: " + canCamp + " | Has Camp | " + hasCamp + " | Lootable: " + lootable;
    }
}