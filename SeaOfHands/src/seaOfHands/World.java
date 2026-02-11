package seaOfHands;

public class World {
	
	//Class for player position
	
	//vars
	
	private POI playerLocation;
	
	//constructor
	public World(POI startingLocation) {
		// TODO Auto-generated constructor stub
		playerLocation = startingLocation;
	}
	
	public POI getPlayerLocation() {
		return playerLocation;
	}
	
	public void move(Direction direction) {
		//TODO develop move method inside World and POI
	}

}
