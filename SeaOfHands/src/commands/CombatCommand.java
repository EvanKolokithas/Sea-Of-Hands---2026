package commands;

import java.util.Scanner;

import seaOfHands.*;

public class CombatCommand implements Command {
	
	private Scanner scanner;
	
	public CombatCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "combat";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {
        if (worldState.inCombat()) {
            System.out.println("You are already in combat!");
            return;
        }
        
        if(worldState.getEnemy() == null) {
        	System.out.println("There are no enemies");
        	return;
        }
        if(player.getEnergy() <= 0) {
        	System.out.println("You dont have enough energy to enter combat");
        	return;
        }
        
        
        worldState.enterCombat();
                
        System.out.println("You are now in combat with " + worldState.getEnemy().getName() + ".");

        // Start the combat loop
        Combat.startCombat(player, worldState.getEnemy(), scanner);
        
        
        player.useEnergy();

    }

    @Override
    public String getDescription() {
        return "Enter combat with the current enemy. This will start the combat loop.";
    }
}