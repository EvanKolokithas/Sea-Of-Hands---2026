package seaOfHands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CampCommand;
import commands.CombatCommand;
import commands.CommandManager;
import commands.DiscardCommand;
import commands.EndTurnCommand;
import commands.EquipCommand;
import commands.ForageCommand;
import commands.HelpCommand;
import commands.InfoCommand;
import commands.InventoryCommand;
import commands.LootCommand;
import commands.MoveCommand;
import commands.StatusCommand;
import commands.UseCommand;
import dataBase.DatabaseManager;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Main Class for the game
 * Singleton Pattern:
 * 
 * Only one instance of the game will be created as well as for world and worldState so
 * 
 * @author Evan
 */

public class Game {
	
	private static Game instance;
	
	//vars
	private static World world = new World();
	private static WorldState worldState = new WorldState();
	private static CommandManager manager = new CommandManager();
	
	private static boolean running = true;
	

	
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	//getters
	public static World getWorld() {
		return world;
	}
	
	public static WorldState getWorldState() {
		return worldState;
	}
	
	//logging
	final static Logger log = 
			LogManager.getLogger("Example");
	
	
	//main class
	public static void main(String[] args) {
		
		//register all commands
		manager.register(new EndTurnCommand());
		manager.register(new HelpCommand(manager));
		manager.register(new MoveCommand());
		manager.register(new StatusCommand());
		manager.register(new InventoryCommand());
		manager.register(new DiscardCommand());
		manager.register(new LootCommand());
		manager.register(new ForageCommand());
		manager.register(new CampCommand());
		manager.register(new InfoCommand());
		manager.register(new EquipCommand());
		manager.register(new CombatCommand(new Scanner(System.in)));
		manager.register(new UseCommand());
		
		
		//try with resources
		try (Scanner scanner = new Scanner(System.in)){
			
			
			// initialize database
			DatabaseManager databaseManager = new DatabaseManager();

			// populate table
			databaseManager.populateGameData();

			
			//initial game loop
			
			startGame(databaseManager);

			//Player created with default values
			//TODO default constructor
			
			// Health | Energy | Sanity
			Player player = new Player(10, 5, 1);
			UI.printStatus(player, world, worldState);	
			manager.executeCommand("help", player, world, worldState);
			
			while(running) {
				/*
				 * Proccess
				 * 
				 * Take turn - while not end turn - player actions
				 * 		perform actions - need combat loop to check for health
				 * 
				 * End turn - advance sea - check flooded condition
				 * 
				 * Reset:
				 * 		Energy based on current tile campable
				 * 		Sea speed based on tiles traveled
				 * 		
				 */
				
				System.out.print("What would you like to do?> ");
				String input = scanner.nextLine();
				manager.executeCommand(input, player, world, worldState);
				
			}
			
			//end of game
			endGame(databaseManager, player);
			
			
		}
		catch(IllegalStateException e) {
			//unsure what information to log for now
			log.debug("Game failed to start: " + e);
		}
		finally{
			log.debug("Game start attempt complete");
		}
		
		
	}
	
	private static void startGame(DatabaseManager databaseManager) {
		
		// retrieve intro message
		String introMessage = databaseManager.retrieveMessage("intro");

		// print intro message
		System.out.println(introMessage);
	}
	
	private static boolean healthCheck(Player player) {
		boolean running = true;
			
		//out of health
		if(player.getHealth() < 0)
			running = false;
		
		return running;
	}

	public static void endTurn(Player player) {
				
		//Energy 
		
		if(world.getPlayerLocation().getHasCamp()) {
			//overloaded constructor
			player.resetEnergy(6);
		}
		else {
			//default constructor
			player.resetEnergy();
		}
		
		//Sea speed
		
		if(Arrays.stream(worldState.getSeaSpeedTurns()).anyMatch(n -> n == worldState.getTurn())) {
			worldState.incSeaSpeed();
		}
		
		//Sanity
		
		if(Arrays.stream(worldState.getSanityTurns()).anyMatch(n -> n == worldState.getTurn())) {
			player.incSanity();
		}
		
		//Turn
		
		worldState.advanceTurn();
		
		running = floodCheck() && healthCheck(player);
		
	}
	
	private static boolean floodCheck() {
		boolean running = true;
		
		if(worldState.getTilesTraveled() <= worldState.getSeaLevel())
			running = false;
		
		
		return running;
	}
	
	private static void endGame(DatabaseManager databaseManager, Player player) {
		// retrieve outro message
				String outroMessage = databaseManager.retrieveMessage("outro");

				// print outro message
				System.out.println(outroMessage);
				
				//print stats
				UI.printStatus(player, world, worldState);
	}
	
	public static void printChoices(List<POI> choices) {
		System.out.println("There are " + choices.size() + " paths ahead of you:");
		for(int i = 0; i < choices.size(); i++) {
			System.out.println("\tPath " + (i + 1) + " leads to a " + choices.get(i).getName() + ".");
		}
	}
	
	
	
	
	public static boolean isRunning() {
		return running;
	}
	
	public static void stopGame() {
		running = false;
	}
	
}

