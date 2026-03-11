package commands;

import seaOfHands.*;

public class InfoCommand implements Command {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {

        if (args.length < 2) {
            System.out.println("Usage: info <name>");
            return;
        }

        String targetName = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));

        // 1. Check inventory items
        for (Item item : player.getInventory().getItems()) {
            if (item.getName().equalsIgnoreCase(targetName)) {
                System.out.println(item.getInfo());
                return;
            }
        }

        // 2. Check current POI
        POI location = world.getPlayerLocation();
        if (location.getName().equalsIgnoreCase(targetName)) {
            System.out.println(location.getInfo());
            return;
        }

        System.out.println("You don't see that here.");
    }

    @Override
    public String getDescription() {
        return "Get information about an item, or location.";
    }
}