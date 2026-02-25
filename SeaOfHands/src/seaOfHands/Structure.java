package seaOfHands;

import java.util.Comparator;


public class Structure extends POI implements Lootable, Comparable<Structure>{

	//Structures are POIs that are often raidable, not explorable. They dont have enimeis and are often already campable;
	
	//vars
	private Inventory items;
	private boolean raid;
	
	//sort by sanity level ascending
    public static Comparator<Structure> SanityComparator = 
    	    (s1, s2) -> Integer.compare(s1.getSanityLevel(), s2.getSanityLevel());
	
	//constructor
	public Structure(String name, String desc, boolean camp, int sanityLevel, boolean raid) {
		super(name, desc, camp, sanityLevel);
		
		this.raid = raid;
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
	
	//alphabetical
    @Override
    public int compareTo(Structure other) {
        return this.name.compareTo(other.name);
    }
    
    
    
    
}
