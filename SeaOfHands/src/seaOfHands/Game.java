package seaOfHands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataBase.DatabaseManager;

import java.time.LocalTime;
import java.util.ArrayList;
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
	
	//gettersS
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

			
			
			//TODO log actual info
			//log.debug("Hello World");
			//log.info("Hello World Again");
			
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
			
			//Messages Implemented
			startGame(databaseManager);
			System.out.println("");
			endGame(databaseManager);

			
			//gets the current time
			LocalTime currentTime = LocalTime.now();
			Locale locale = Locale.getDefault();
			
			testDateTime(currentTime, locale);
			
			testDateTime(LocalTime.of(6, 0), new Locale("en"));
			
			testDateTime(LocalTime.of(16, 0), new Locale("es"));
			
			
			
		}
		catch(IllegalStateException e) {
			System.out.println("Game failed to start: " + e);
		}
		finally{
			System.out.println("Game start attempt complete");
		}
		
		
	}
	
	/**
	 * Throws and exception as an example to implement try catch functionality
	 * 
	 * @throws Illegal State Exception
	 */
	public static void startGame(DatabaseManager databaseManager) {
		
		// retrieve intro message
		String introMessage = databaseManager.retrieveMessage("intro");

		// print intro message
		System.out.println(introMessage);
	}
	
	public static void endGame(DatabaseManager databaseManager) {
		// retrieve outro message
				String outroMessage = databaseManager.retrieveMessage("outro");

				// print outro message
				System.out.println(outroMessage);
	}
	
	//method to test dateTime
	public static void testDateTime(LocalTime time, Locale locale) {
		
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
}

