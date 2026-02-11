package seaOfHands;

public class Structure extends POI implements Lootable{

	//Structures are POIs that are often raidable, not explorable. They dont have enimeis and are often already campable;
	
	//vars
	private Inventory items;
	private boolean raid;
	
	//constructor
	public Structure(String name, String desc, boolean camp, boolean raid) {
		super(name, desc, camp);
		
		raid = this.raid;
	}
	
	//Methods
	
	public boolean isRaidable() {
		return raid;
	}
	@Override
	public Inventory loot() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
