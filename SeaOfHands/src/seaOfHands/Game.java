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


/***
 * Main class for the game, singleton pattern
 * 
 */
public class Game {
	
	private static Game instance;
	
	//vars
	private static World world = new World();
	private static WorldState worldState = new WorldState();
	private static CommandManager manager = new CommandManager();
	
	private static boolean running = true;
	
	//logging
		final static Logger log = 
				LogManager.getLogger("Example");
		
	
	/***
	 * Constructor for game, creates a new instance of game, singleton pattern
	 * 
	 * @return instance of game
	 */
	
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	//getters
	/***
	 * Getter for world
	 * 
	 * @return world object
	 */
	public static World getWorld() {
		return world;
	}
	
	/***
	 * Getter for worldState
	 * 
	 * @return worldState object
	 */
	public static WorldState getWorldState() {
		return worldState;
	}
	
	/***
	 * Getter for running
	 * 
	 * @return running boolean
	 */
	public static boolean isRunning() {
		return running;
	}
	
	//setters
	/***
	 * setter for running
	 * 
	 */
	public static void stopGame() {
		running = false;
	}
	
	/***
	 * Main class for game, contains register for all commands, contains main game loop
	 * 
	 * @param args
	 */
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
		
		//register aliases
		manager.registerAlias("end", new EndTurnCommand());
		
		
		//try with resources
		try (Scanner scanner = new Scanner(System.in)){
			
			
			//initialize database
			DatabaseManager databaseManager = new DatabaseManager();

			//populate table
			databaseManager.populateGameData();

			
			//initial game loop
			
			startGame(databaseManager);
			
			// Health | Energy | Sanity
			Player player = new Player(10, 5, 1);
			UI.printStatus(player, world, worldState);	
			manager.executeCommand("help", player, world, worldState);
			
			/***
			 * Main game loop: take turn - end turn - reset
			 * Uses running flag and scanner object
			 * Uses manager to execute the inputed commands
			 * 
			 */
			while(running) {
				
				System.out.print("What would you like to do?> ");
				String input = scanner.nextLine();
				manager.executeCommand(input, player, world, worldState);
				
			}
			
			//end of game
			endGame(databaseManager, player);
			
			
		}
		catch(IllegalStateException e) {
			//debug error info if occurred
			log.debug("Game failed to start: " + e);
		}
		finally{
			//debug game end info
			log.debug("Game start attempt complete");
		}
		
		
	}
	
	//data base
	
	/***
	 * Prints intro message from database
	 * 
	 * @param databaseManager: object for accessing the database
	 */
	private static void startGame(DatabaseManager databaseManager) {
		
		// retrieve intro message
		String introMessage = databaseManager.retrieveMessage("intro");

		// print intro message
		System.out.println(introMessage);
	}
	
	/***
	 * Prints outro message from database and player status to act as a sort of score
	 * 
	 * @param databaseManager: object for accessing data base
	 * @param player: Player object to print stats
	 */
	private static void endGame(DatabaseManager databaseManager, Player player) {
		// retrieve outro message
				String outroMessage = databaseManager.retrieveMessage("outro");

				// print outro message
				System.out.println(outroMessage);
				
				//print stats
				UI.printStatus(player, world, worldState);
	}
	
	//checks
	
	/***
	 * Checks if the player is alive
	 * 
	 * @param player: object to access players health
	 * @return boolean to set running to false if player is not alive
	 */
	private static boolean healthCheck(Player player) {
		boolean running = true;
			
		//out of health
		if(player.getHealth() < 0)
			running = false;
		
		return running;
	}
	
	/***
	 * Checks if the sea reaches the player
	 * 
	 * @return boolean if players location is in the sea
	 */
	private static boolean floodCheck() {
		boolean running = true;
		
		if(worldState.getTilesTraveled() <= worldState.getSeaLevel())
			running = false;
		
		
		return running;
	}
	
	//methods
	
	/***
	 * Increments and resets world and player data at end of turn
	 * 
	 * @param player: object to access and modify player health and energy
	 */
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
	
}

