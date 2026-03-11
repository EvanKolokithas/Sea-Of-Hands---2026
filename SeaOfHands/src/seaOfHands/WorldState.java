package seaOfHands;

/***
 * Class for keeping track of incremental game data
 * 
 *  turn for players turn
 *  tiles traveled for comparing to sea level
 *  sea level for checking flooded condition
 *  seaSpeed for inc sea level
 *  
 *  current enemy for combat
 *  inCombat flag for combat and avoid moving during combat
 *  
 *  seaSpeedTurns and sanityTurns for inc at certain turns
 */
public class WorldState {
	
	//vars
	
	private int turn;
	private int tilesTraveled;
	private int seaLevel;
	private int seaSpeed;
	
	private Enemy currentEnemy;
	private boolean inCombat = false;
	
	//Turns at which sea speed and sanity increases.
	final int[] seaSpeedTurns = {2, 6, 9,11,12,13,14};
	final int[] sanityTurns = {2, 6, 9};
	
	//constructor
	
	/***
	 * Constructor for world state, initializes values at start of game
	 * 
	 */
	public WorldState() {
		turn = 0;
		tilesTraveled = 0;
		seaLevel = 0;
		seaSpeed = 1;
	}
	
	//incremental
	
	/***
	 * Inc for turn and seaLevel
	 * 
	 */
	public void advanceTurn() {
		turn ++;
		seaLevel += seaSpeed;
	}
	
	/**
	 * Inc for sea speed
	 * 
	 */
	public void incSeaSpeed() {
		seaSpeed ++;
	}
	
	/**
	 * Inc for tiles traveled
	 * 
	 */
	public void incTilesTraveled() {
		tilesTraveled ++;
	}
	
	//getters
	
	/**
	 * Getter for turn
	 * 
	 * @return turn
	 */
	public int getTurn() {
		return turn;
	}
	
	/***
	 * Getter for sea speed turns
	 * 
	 * @return array of all sea speed turns 
	 */
	public int[] getSeaSpeedTurns() {
		return seaSpeedTurns;
	}
	
	/***
	 * Getter for sanity turns
	 * 
	 * @return array of all sanity turns
	 */
	public int[] getSanityTurns() {
		return sanityTurns;
	}
	
	/***
	 * Getter for sea level
	 * 
	 * @return sea level
	 */
	public int getSeaLevel() {
		return seaLevel;
	}
	
	/***
	 * Getter for tiles traveled
	 * 
	 * @return tiles traveled
	 */
	public int getTilesTraveled() {
		return tilesTraveled;
	}

	/***
	 * Getter for enemy
	 * 
	 * @return enemy
	 */
	public Enemy getEnemy() {
	    return currentEnemy;
	}
	
	/***
	 *Getter for inCombat
	 *
	 *@return in combat flag
	 */	
	public boolean inCombat() {
		return inCombat;
	}

	//setters
	
	/***
	 * Setter for enemy
	 * 
	 * @param enemy: sets current enemy
	 */
	public void setEnemy(Enemy enemy) {
		currentEnemy = enemy;
	}
	
	/***
	 * Setter for in combat flag
	 * 
	 */
	public void enterCombat() {
		inCombat = true;
	}

	/***
	 * Setter for in combat and current enemy
	 * Resets the values
	 * 
	 */
	public void endCombat() {
	    currentEnemy = null;
	    inCombat = false;
	}
	
}
