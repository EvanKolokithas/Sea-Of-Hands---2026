package commands;

import seaOfHands.Enemy;
import seaOfHands.Environment;
import seaOfHands.Game;
import seaOfHands.World;
import seaOfHands.WorldState;
import seaOfHands.Player;
import seaOfHands.UI;
import seaOfHands.POI;

import java.util.List;
import java.util.Scanner;

public class MoveCommand implements Command {

    @Override
    public String getName() {
        return "move";
    }

    @Override
    public void execute(String[] args, Player player, World World, WorldState worldState) {

        Game game = Game.getInstance();
        World world = game.getWorld();
        if(worldState.inCombat() || worldState.getEnemy() != null) {
            System.out.println("An enemy blocks your path!");
            return;
        }
        
        if(player.getEnergy() <= 0){
        	System.out.println("You do not have enough energy to move");
        	return;
        }
        // generate 2–3 random locations
        int count = (int)(Math.random() * 2) + 2;
        List<POI> choices = world.getRandomPOIs(count, player.getSanity());
        
        // print choices
        Game.getInstance();
		UI.printChoices(choices);

        // ask player for choice
        System.out.print("Choose a path: ");
        Scanner scanner = new Scanner(System.in);
		int selection = scanner.nextInt();

		// validate choice
		if(selection < 1 || selection > choices.size()) {
			   System.out.println("Invalid path.");
			   return;
		}

		POI destination = choices.get(selection - 1);
			
		// inc player data
		player.useEnergy();
		worldState.incTilesTraveled();

		// set up loot and campability
		destination.onArrival();
			
		// move player
		world.setPlayerLocation(destination, player);
		
        //spawn enemy
        if(destination instanceof Environment && Math.random() < 0.35) {
        	
            Enemy enemy = world.generateEnemy(player.getSanity());

            worldState.setEnemy(enemy);

            System.out.println("Something emerges from the tide...");
            System.out.println("\nEnenmy:\n " + enemy.getName());
            System.out.println();

        }
        
        
    }

	@Override
	public String getDescription() {
		return "Gives the player 2 to 3 paths to choose from. Consumes 1 energy";
	}
}