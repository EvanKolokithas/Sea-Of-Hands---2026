package commands;

import seaOfHands.*;
import java.util.Arrays;

public class UseCommand implements Command {

    @Override
    public String getName() {
        return "use";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {
        Inventory inventory = player.getInventory();

        if (inventory.getItems().size() <= 0) {
            System.out.println("Your inventory is empty.");
            return;
        }

        if (args.length < 2) {
            System.out.println("Usage: use <item name>");
            return;
        }

        // Join args into item name
        String itemName = String.join(" ", Arrays.copyOfRange(args, 1, args.length)).trim();
        
        if (itemName == "cancel") {
        	System.out.println("Use Command Canceled");
        	return;
        }
        
        // Find the item in inventory
        Item item = inventory.getItems().stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);

        if (item == null) {
            System.out.println("You don't have an item called '" + itemName + "'.");
            return;
        }
        
        

        if (worldState.inCombat()) {
            Enemy enemy = worldState.getEnemy();

            if (item instanceof Consumable) {
                Consumable consumable = (Consumable) item;
                // Apply effects
                player.setHealth(player.getHealth() + consumable.getHealthRestore());
                player.setEnergy(player.getEnergy() + consumable.getEnergyRestore());

                System.out.println("You used " + consumable.getName() + " in combat!");
                inventory.remove(item);

                // End player turn in combat
                System.out.println("Using an item ends your turn.");
                Combat.enemyTurn(player, enemy);

            } else if (item instanceof Weapon) {
                Weapon weapon = (Weapon) item;

                int damage = weapon.getDamage();
                enemy.takeDamage(damage);

                System.out.println("You strike with " + weapon.getName() + " for " + damage + " damage.");

                // End player turn in combat
                Combat.enemyTurn(player, enemy);

            } else {
                System.out.println("You can't use that item in combat.");
            }

        } 
        
        else {
            if (item instanceof Consumable) {
                Consumable consumable = (Consumable) item;
                player.setHealth(player.getHealth() + consumable.getHealthRestore());
                player.setEnergy(player.getEnergy() + consumable.getEnergyRestore());

                System.out.println("You used " + consumable.getName() + ".");
                inventory.remove(item);

            } else if (item instanceof Weapon) {
                System.out.println("You can only use weapons in combat.");
            } else {
                System.out.println("You can't use that item.");
            }
        }
    }

    @Override
    public String getDescription() {
        return "Use a consumable or weapon. Consumables can be used anywhere; weapons can only attack in combat without equipping.";
    }
}