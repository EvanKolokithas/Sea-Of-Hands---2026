package commands;

import seaOfHands.*;
import java.util.Scanner;

public class LootCommand implements Command {

    private final DiscardCommand discardCommand = new DiscardCommand();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "loot";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {
        POI location = world.getPlayerLocation();

        // Check if the POI is lootable
        if (!(location instanceof Structure)) {
            System.out.println("You can only loot structures.");
            return;
        }

        Structure structure = (Structure) location;

        // Check if already looted
        if (structure.isUsed()) {
            System.out.println("You have already looted this structure.");
            return;
        }

        // Check if player has energy
        if (player.getEnergy() < 1) {
            System.out.println("You don't have enough energy to loot.");
            return;
        }

        // Attempt to find an item 
        Item foundItem = structure.getRandomLoot();

        if (foundItem == null) {
            System.out.println("You search the " + structure.getName() + " but find nothing.");
        } 
        else {
            Inventory inventory = player.getInventory();
            
            
            if (inventory.isFull()) {
                System.out.println("Your inventory is full. You found a " + foundItem.getName() + ".");
                System.out.print("Do you want to discard an item to pick it up? (yes/no): ");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("yes")) {
                    boolean discarded = discardCommand.interactiveDiscard(player);
                    if (discarded && (!inventory.isFull())) {
                        inventory.add(foundItem);
                        System.out.println("You picked up " + foundItem.getName() + ".");
                    } else {
                        System.out.println("You leave the " + foundItem.getName() + " behind.");
                    }
                } else {
                    System.out.println("You leave the " + foundItem.getName() + " behind.");
                }
            } else {
                inventory.add(foundItem);
                System.out.println("You picked up " + foundItem.getName() + ".");
            }
        }

        // Consume 1 energy
        player.useEnergy();

        // Mark POI as looted
        structure.makeUsed();
    }

    @Override
    public String getDescription() {
        return "Loot a structure to find items. Costs 1 energy. Can only loot each structure once.";
    }
}