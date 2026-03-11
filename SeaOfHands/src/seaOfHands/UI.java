package seaOfHands;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UI {

	public static void printStatus(Player player, World world, WorldState worldState) {
		POI location = world.getPlayerLocation();
		
		System.out.println("===== STATUS =====");

        System.out.println("Health: " + player.getHealth() +
        		"   Energy: " + player.getEnergy() + 
        		"   Sanity: " + player.getSanity()
        		);

        System.out.println();

        System.out.println("Location: " + location.getName());
        if(location.canCamp())
        	System.out.println("Camp Available: Yes");
        else
        	System.out.println("Camp Available: No");
        
        if(player.getEquippedWeapon() != null)
            System.out.println("Weapon: " + player.getEquippedWeapon().getName());
        else
            System.out.println("Weapon: None");
        
        System.out.println();
        
        System.out.println("Turn: " + worldState.getTurn());
        System.out.println("Tiles Traveled: " + worldState.getTilesTraveled());
        System.out.println("Sea Level: " + worldState.getSeaLevel());

        System.out.println("==================");
	}
	
	public static void printCombat(Enemy enemy, Player player) {
		System.out.println("\n===== COMBAT =====");
        System.out.println(enemy.getName());
        System.out.println("HP: " + enemy.getHealth()
        + "| Your HP: " + player.getHealth()
        		);
        System.out.println("==================");

        System.out.println("\nChoose action:");
        System.out.println("1 - Attack");
        System.out.println("2 - Use Item");
        System.out.println("3 - Run");
        System.out.println();
        System.out.println("Other commands: help, inventory, status");
	}
	
	public static void printInventory(List<Item> items) {
		Map<String, Integer> counts = new LinkedHashMap<>();

        for (Item item : items) {
            String name = item.getName();
            counts.put(name, counts.getOrDefault(name, 0) + 1);
        }

        System.out.println("===== Inventory (" + items.size() + "/10) =====");

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + " x" + entry.getValue());
        }

        System.out.println("=====================");
	}
}
