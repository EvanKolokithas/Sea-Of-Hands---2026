package seaOfHands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main Class for the game
 * 
 * @author Evan
 */
public class Game {
	
	
	final static Logger log = 
			LogManager.getLogger("Example");
	
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

