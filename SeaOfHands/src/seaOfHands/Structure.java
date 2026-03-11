package seaOfHands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Structure extends POI {

    private final List<Item> possibleLoot;

    public Structure(String name, String desc, boolean camp, boolean lootable, int sanityLevel, List<Consumable> consumables, List<Weapon> weapons) {
        super(name, desc, camp, lootable, sanityLevel);
        possibleLoot = new ArrayList<>();
        possibleLoot.addAll(consumables);
        possibleLoot.addAll(weapons);
        
    }

    @Override
    public void onArrival() {
        if (!lootable) {
        	// 70% chance to become lootable
        	lootable = Math.random() <= 0.7;
        }
            
        if (!canCamp) {
        	// 20% chance to become campable
        	canCamp = Math.random() <= 0.7;
        }

        used = false;
        
    }

    public Item getRandomLoot() {
        if (!isLootable() || possibleLoot.isEmpty()) {
        	return null;
        }

        List<Item> validItems = possibleLoot.stream()
                .filter(item -> item.getSanity() <= sanityLevel && item.getSanity() >= sanityLevel - 1)
                .collect(Collectors.toList());

        if (validItems.isEmpty()) return null;

        int index = (int) (Math.random() * validItems.size());
        return validItems.get(index);
    }

    @Override
    public String getInfo() {
        return name + " : " + desc + " | Campable: " + canCamp + " | Has Camp " + hasCamp + " | Lootable: " + lootable;
    }
}