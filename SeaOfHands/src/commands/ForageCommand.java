package commands;

import java.util.Scanner;

import seaOfHands.Environment;
import seaOfHands.Inventory;
import seaOfHands.Item;
import seaOfHands.POI;
import seaOfHands.Player;
import seaOfHands.World;
import seaOfHands.WorldState;

public class ForageCommand implements Command {



    private final DiscardCommand discardCommand = new DiscardCommand();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "forage";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {
        POI location = world.getPlayerLocation();

        // Check if the POI is lootable
        if (!(location instanceof Environment)) {
            System.out.println("You can only forage in environments.");
            return;
        }

        Environment environment = (Environment) location;

        // Check if already looted
        if (environment.isUsed()) {
            System.out.println("You have already foraged here.");
            return;
        }

        // Check if player has energy
        if (player.getEnergy() < 1) {
            System.out.println("You don't have enough energy to forage.");
            return;
        }

        // Attempt to find an item 
        Item foundItem = environment.getRandomLoot();

        if (foundItem == null) {
            System.out.println("You forage the " + environment.getName() + " but find nothing.");
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
        environment.makeUsed();
    }

    @Override
    public String getDescription() {
        return "Forage in an environment to find items. Costs 1 energy. Can only Forage once per location.";
    }
}
