package seaOfHands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataBase.DatabaseManager;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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
 * singleton pattern works well
 * 
 * @author Evan
 */

public class Game {
	
	private static Game instance;
	
	//vars
	private static World world = new World();
	private static WorldState worldState = new WorldState();
	

	
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	//getters
	public World getWorld() {
		return world;
	}
	
	public WorldState getWorldState() {
		return worldState;
	}
	
	//logging
	final static Logger log = 
			LogManager.getLogger("Example");
	
	
	//main class
	public static void main(String[] args) {
		
		try {
			
			
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
			
			//Turns at which sea speed increases.
			final int[] seaSpeedTurns = {10, 20, 30};
			final int[] sanityTurns = {3, 7};
			
			
			boolean running = true;
			
			boolean endTurn = false;
			
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
				
				
				
				
				
				while(!endTurn) {
					//TODO perform actions
					
					//gets list of choices (should be in travel command function
					List<POI> choices = world.getRandomPOIs((int)(Math.random() * 2) + 2, player.getSanity());
					printChoices(choices);
					
					System.out.println();
					
					endTurn = true;
					
				}
				
				
				endTurn(player, seaSpeedTurns, sanityTurns);
				//running = floodCheck();
				running = false;
				endTurn = false;
			}
			
			//end of game
			endGame(databaseManager);
			
			
		}
		catch(IllegalStateException e) {
			//unsure what information to log for now
			log.debug("Game failed to start: " + e);
			System.out.println("Game failed to start: " + e);
		}
		finally{
			log.debug("Game start attempt complete");
			System.out.println("Game start attempt complete");
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

	private static void endTurn(Player player, int[] seaSpeedTurn, int[] sanityTurns) {
		
		
		//Energy 
		
		if(world.getPlayerLocation().canCamp()) {
			//overloaded constructor
			player.resetEnergy(6);
		}
		else {
			//default constructor
			player.resetEnergy();
		}
		
		//Sea speed
		
		if(Arrays.stream(seaSpeedTurn).anyMatch(n -> n == worldState.getTurn())) {
			worldState.incSeaSpeed();
		}
		
		//Sanity
		
		if(Arrays.stream(sanityTurns).anyMatch(n -> n == worldState.getTurn())) {
			player.incSanity();
		}
		
		//Turn
		
		worldState.incTurn();
		
	}
	
	private static boolean floodCheck() {
		boolean running = true;
		
		if(worldState.getTilesTraveled() <= worldState.getSeaLevel())
			running = false;
		
		
		return running;
	}
	
	private static void endGame(DatabaseManager databaseManager) {
		// retrieve outro message
				String outroMessage = databaseManager.retrieveMessage("outro");

				// print outro message
				System.out.println(outroMessage);
				
				//print stats
				System.out.println("Turns: " + worldState.getTurn());
				System.out.println("Tiles: " + worldState.getTilesTraveled());
				System.out.println("Sea Level " + worldState.getSeaLevel());
	}
	
	
	private static void testDateTime(LocalTime time, Locale locale) {
		
		//create a resourceBundle based on property files for language
		ResourceBundle bundle = ResourceBundle.getBundle("SeaOfHands.messages", locale);
		
		//gets time
		int hour = time.getHour();
		
		//gets a string for the bundle
		String messageKey = switch(hour) {
			case 0, 1, 2, 3, 4, 5, 6 -> "early";
			case 7, 8, 9, 10, 11 -> "morning";
			case 12 -> "noon";
			case 13, 14, 15, 16, 17, 18, 19, 20 -> "afternoon";
			default  -> "evening";
		};
		
		//prints time and message
		System.out.println("Current Time: " + time);
		System.out.println(bundle.getString(messageKey));
		
	}
	
	private static void testUnit5() {
		//tests for Unit 5
		
		 // Sort alphabetically (natural order)
		List<Structure> allStructures = world.getAllStructures();
		
		// Sort alphabetically using Comparable (natural order)
		Collections.sort(allStructures);
		System.out.println("Structures sorted alphabetically:");
		for (Structure s : allStructures) {
		    System.out.println(s.getName() + " (Sanity " + s.getSanityLevel() + ")");
		}

		// Sort by sanity using Comparator
		allStructures.sort(Structure.SanityComparator);
		System.out.println("\nStructures sorted by sanity:");
		for (Structure s : allStructures) {
		    System.out.println(s.getName() + " (Sanity " + s.getSanityLevel() + ")");
		}
		
		
		//store all structures in a treeset for demonstration
		Set<String> structureNames = new TreeSet<>();
		for (Structure s : allStructures) {
		    structureNames.add(s.getName());
		}
		
		System.out.println("\nDemonstrating a Set (tree set):");
		for (String name : structureNames) {
		    System.out.println(name);
		}
		
		//store all structures along with their sanities in a treemap
		Map<Integer, List<Structure>> structuresBySanity = new TreeMap<>();

		for (Structure s : allStructures) {
		    //get sanity
			int sanity = s.getSanityLevel();
		    
			//creates a new array list for each sanity level
		    structuresBySanity.putIfAbsent(sanity, new ArrayList<>());
		    structuresBySanity.get(sanity).add(s);
		}

		
		System.out.println("\nDemonstrating a Map (structures grouped by sanity):");
		for (Map.Entry<Integer, List<Structure>> entry : structuresBySanity.entrySet()) {
		    System.out.println("Sanity " + entry.getKey() + ":");
		    for (Structure s : entry.getValue()) {
		        System.out.println(" - " + s.getName());
		    }
		}
		
		System.out.println();
		

	}
	
	private static void testTime() {
		//gets the current time
		LocalTime currentTime = LocalTime.now();
		Locale locale = Locale.getDefault();
		
		testDateTime(currentTime, locale);
		
		testDateTime(LocalTime.of(6, 0), new Locale("en"));
		
		testDateTime(LocalTime.of(16, 0), new Locale("es"));
	}
	
	private static void printChoices(List<POI> choices) {
		System.out.println("There are " + choices.size() + " paths ahead of you:");
		for(int i = 0; i < choices.size(); i++) {
			System.out.println("\tPath " + (i + 1) + " leads to a " + choices.get(i).getName() + ".");
		}
	}
}

