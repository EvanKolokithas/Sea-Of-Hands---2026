package seaOfHands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//for date time project
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;




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
	private World world;
	private WorldState worldState;
	
	
	//constructor
	private Game() {
		//temporary starting location
		world = new World(new Envoirment("temp","temp",true,null));
		worldState = new WorldState();
	}
	
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
			log.debug("Hello World");
			log.info("Hello World Again");
			//Example Function
			//startGame();
			
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
	public static void startGame() {
		throw new IllegalStateException("Missing Configuration Files");
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

