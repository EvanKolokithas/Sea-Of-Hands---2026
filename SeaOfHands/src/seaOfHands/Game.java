package seaOfHands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}

