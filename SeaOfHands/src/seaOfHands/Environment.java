package seaOfHands;

import java.util.List;
import java.util.ArrayList;

public class Environment extends POI implements Lootable{

	//Envoirments are POIs that are not lootable but explorable and have enemies.
	
	//vars
	private boolean foragable;
	private List<Enemy> enemies = new ArrayList<>();
	
	//constructor
	public Environment(String name, String desc, boolean camp, int sanityLevel, boolean foragable) {
		super(name, desc, camp, sanityLevel);
		
		this.foragable = foragable;
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
