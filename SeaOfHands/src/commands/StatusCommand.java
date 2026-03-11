package commands;

import seaOfHands.Game;
import seaOfHands.Player;
import seaOfHands.World;
import seaOfHands.WorldState;
import seaOfHands.POI;

public class StatusCommand implements Command {

    @Override
    public String getName() {
        return "status";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {

        Game game = Game.getInstance();
        POI location = world.getPlayerLocation();

        System.out.println("----- STATUS -----");

        System.out.println("Health: " + player.getHealth());
        System.out.println("Energy: " + player.getEnergy());
        System.out.println("Sanity: " + player.getSanity());

        System.out.println();

        System.out.println("Location: " + location.getName());
        System.out.println("Campable: " + location.canCamp());

        System.out.println();

        System.out.println("Turn: " + worldState.getTurn());
        System.out.println("Tiles Traveled: " + worldState.getTilesTraveled());
        System.out.println("Sea Level: " + worldState.getSeaLevel());

        System.out.println("------------------");
    }


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}