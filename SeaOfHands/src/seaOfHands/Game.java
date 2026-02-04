package seaOfHands;

/**
 * Main Class for the game
 * 
 * @author Evan
 */
public class Game {

	
	public static void main(String[] args) {
		
		try {
			//Example Function
			startGame();
			
			//edit for revert demo
			System.out.println("This will be reverted");
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

