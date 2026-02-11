package seaOfHands;

import java.util.List;
import java.util.ArrayList;

public class Envoirment extends POI implements Lootable{

	//Envoirments are POIs that are not lootable but explorable and have enemies.
	
	//vars
	private Inventory foragables;
	private List<Enemy> enemies = new ArrayList<>();
	
	//constructor
	public Envoirment(String name, String desc, boolean camp, Inventory foragables) {
		super(name, desc, camp);
		
		this.foragables = foragables;
	}
	
	//methods
	
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}
	
	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
	}

	
	@Override
	public Inventory loot() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
