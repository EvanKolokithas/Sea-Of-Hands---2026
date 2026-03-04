package seaOfHands;

public class WorldState {
	
	//class for current information about the world
	
	//vars
	
	private int turn;
	private int tilesTraveled;
	private int seaLevel;
	private int seaSpeed;
	

	
	
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
		seaSpeed++;
	}
	
	
	public int getTurn() {
		return turn;
	}
	
	public void incTurn() {
		turn ++;
	}
	
	
	//for ending conditions
	
	public int getSeaLevel() {
		return seaLevel;
	}
	
	public int getTilesTraveled() {
		return tilesTraveled;
	}

	
	

}
