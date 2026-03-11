package commands;

import seaOfHands.POI;
import seaOfHands.Player;
import seaOfHands.World;
import seaOfHands.WorldState;

public class CampCommand implements Command {

    @Override
    public String getName() {
        return "camp";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {

        POI location = world.getPlayerLocation();

        if (!location.canCamp()) {
            System.out.println("You cannot set up camp here.");
            return;
        }

        System.out.println("You set up camp at the " + location.getName() + ".");
        
        player.useEnergy();
        
        }

    @Override
    public String getDescription() {
        return "Set up camp to restore energy if the location allows it.";
    }
}