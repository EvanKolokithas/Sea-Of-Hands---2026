package seaOfHands;

public class WorldState {
	
	//class for current information about the world
	
	//vars
	
	private int turn;
	private int tilesTraveled;
	private int seaLevel;
	private int seaSpeed;
	
	//Turns at which sea speed and sanity increases.
	final int[] seaSpeedTurns = {10, 20, 30};
	final int[] sanityTurns = {3, 7};

	
	
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
	
	public void incTurn() {
		turn ++;
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

	
	

}
