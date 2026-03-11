package seaOfHands;

public class WorldState {
	
	//class for current information about the world
	
	//vars
	
	private int turn;
	private int tilesTraveled;
	private int seaLevel;
	private int seaSpeed;
	
	private Enemy currentEnemy;
	private boolean inCombat = false;
	
	//Turns at which sea speed and sanity increases.
	final int[] seaSpeedTurns = {2, 6, 9,11,12,13,14};
	final int[] sanityTurns = {2, 6};

	
	
	//constructor
	
	public WorldState() {
		turn = 0;
		tilesTraveled = 0;
		seaLevel = 0;
		seaSpeed = 1;
	}
	
	public void advanceTurn() {
		turn ++;
		//TODO implement tiles traveled counter
		seaLevel += seaSpeed;
	}
	
	public void incSeaSpeed() {
		seaSpeed ++;
	}
	
	public void incTilesTraveled() {
		tilesTraveled ++;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public int[] getSeaSpeedTurns() {
		return seaSpeedTurns;
	}
	
	public int[] getSanityTurns() {
		return sanityTurns;
	}
	
	
	//for ending conditions
	
	public int getSeaLevel() {
		return seaLevel;
	}
	
	public int getTilesTraveled() {
		return tilesTraveled;
	}

	public void setEnemy(Enemy enemy) {
		currentEnemy = enemy;
	}
	
	public void enterCombat() {
		inCombat = true;
	}

	public boolean inCombat() {
		return inCombat;
	}
	
	public Enemy getEnemy() {
	    return currentEnemy;
	}

	public void endCombat() {
	    currentEnemy = null;
	    inCombat = false;
	}
	
	

}
