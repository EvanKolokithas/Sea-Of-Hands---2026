package seaOfHands;

public class WorldState {
	
	//class for current information about the world
	
	//vars
	
	private int turn;
	private int tilesTraveled;
	private int seaLevel;
	private int seaSpeed;
	
	//controls what tiles can appear
	private int sanity;
	
	
	//constructor
	
	public WorldState() {
		turn = 0;
		tilesTraveled = 0;
		seaLevel = 0;
		seaSpeed = 1;
		sanity = 1;
	}
	
	public void advanceTurn() {
		turn ++;
		//TODO implement tiles traveled counter
		seaLevel += seaSpeed;
	}
	
	public int getSeaLevel() {
		return seaLevel;
	}
	
	public int getSanity() {
		return sanity;
	}
	
	public void increaseSeaSpeed() {
		seaSpeed++;
	}
	
	public void increaseSanity() {
		//sanity capped at 3
		if(sanity != 3) {
			sanity++;
		}
	}

}
