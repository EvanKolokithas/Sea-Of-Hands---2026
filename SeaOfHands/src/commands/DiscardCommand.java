package commands;

import seaOfHands.Player;
import seaOfHands.World;
import seaOfHands.WorldState;

import java.util.Scanner;

import seaOfHands.Inventory;
import seaOfHands.Item;

public class DiscardCommand implements Command {

    @Override
    public String getName() {
        return "discard";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {
        if (args.length < 2) {
            System.out.println("Usage: discard <item name>");
            return;
        }

        String itemName = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
        Item toRemove = null;

        for (Item item : player.getInventory().getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                toRemove = item;
                break;
            }
        }

        if (toRemove != null) {
            player.getInventory().remove(toRemove);
            System.out.println("You discarded " + toRemove.getName() + ".");
        } else {
            System.out.println("Item not found in inventory: " + itemName);
        }
        
        
    }
    
    //number instead of item name for looting functions
    public boolean interactiveDiscard(Player player) {
        Inventory inventory = player.getInventory();
        if (inventory.getItems().isEmpty()) {
            System.out.println("Your inventory is empty. Nothing to discard.");
            return false;
        }

        System.out.println("Choose an item to discard:");
        for (int i = 0; i < inventory.getItems().size(); i++) {
            Item item = inventory.getItems().get(i);
            System.out.println((i + 1) + ". " + item.getName());
        }

        try (Scanner scanner = new Scanner(System.in)) {
			int choice = -1;

			while (choice < 1 || choice > inventory.getItems().size()) {
			    System.out.print("Enter the number of the item to discard: ");
			    if (scanner.hasNextInt()) {
			        choice = scanner.nextInt();
			        scanner.nextLine(); // consume newline
			    } else {
			        scanner.nextLine(); // consume invalid input
			    }
			}

			Item removed = inventory.getItems().get(choice - 1);
			inventory.remove(removed);
			System.out.println("You discarded " + removed.getName() + ".");
		}
        return true;
    }
    
    

	@Override
	public String getDescription() {
		return "Discard an item using its name: discard <item name>";
	}
}