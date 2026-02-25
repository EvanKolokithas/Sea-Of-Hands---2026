package seaOfHands;

import java.util.ArrayList;
import java.util.List;

public class World {
	
	//Class for player position
	
	//vars
	
	private POI playerLocation;
	
	private List<Structure> allStructures;
	private List<Envoirment> allEnvoirments;
	private List<Item> allItems;
	private List<Enemy> allEnemies;
	
	
	//constructor
	public World() {
		// initialize collections
		allStructures = new ArrayList<>();
		allEnvoirments = new ArrayList<>();
        allItems = new ArrayList<>();
        allEnemies = new ArrayList<>();
        
		// populate collections with hardcoded values
        initializePOIs();
        initializeItems();
        initializeEnemies();
        
		playerLocation = allStructures.getFirst();
	}
	
    private void initializePOIs() {
    	//structures (12) raidable and campable auto set to false and randomized when initialized later unless always true
    	
    	//sanity 1
    	allStructures.add(new Structure("Beach Cabin", "A small cabin with salted floorboards and messy rooms. You have lived here for as long as you remember; it's the closest thing you have to a home.", true, 1, true));
    	allStructures.add(new Structure("Firewatch Tower", "A tall and creaky tower traditionally used for spotting fires, clearly abandoned years ago.", false, 1, false));
    	allStructures.add(new Structure("Abandoned House", "	A modest house with swollen doors and warped floorboards. Dust covers its walls, and plants sprout between holes in the floor.", false, 1, false));
    	allStructures.add(new Structure("Campsite", "A cozy campsite with sleeping bags still set up around a small fire pit.", true, 1, false));
        
        //sanity 2
    	allStructures.add(new Structure("Cliffside Church", "The church rests on the edge of the cliff as if it's going to fall at any moment. Despite seeming abandoned, its candles remain burning.", false, 2, false));
    	allStructures.add(new Structure("Ruined Tower", "A large stone tower broken at the top. You don't remember seeing any stone buildings near the coast.", false, 2, false));
    	allStructures.add(new Structure("Burned Down Trading Post", "The shelves are bare, and debris is scattered across the floor, scattered. Ash fills the air, making it hard to adjust your breathing.", false, 2, false));
    	allStructures.add(new Structure("Sunken Inn", "An old, almost antique-looking inn, half of it buried in the ground as if the ground fell below it before swallowing it up.", true, 2, false));
        
        //sanity 3
    	allStructures.add(new Structure("Deserted Castle Hall ", "A great stone hall that stretches for what seems forever. Everything about it is pristine, marble floors, stained glass windows, and torches lining the walls.", false, 3, false));
    	allStructures.add(new Structure("Inland Lighthouse", "A lonely lighthouse, standing proudly in solitude as it waits to guide ships that will never come. You worry some could come soon.", false, 3, false));
    	allStructures.add(new Structure("Familiar Cabin", "A small cabin with salted floorboards and messy rooms. The sight of it feels strangely familiar, but more than anything, you recognize the smell.", true, 3, true));
    	allStructures.add(new Structure("Quarantined House", "Windows sealed, and doors boarded from the inside. A bell hangs outside the house, its rope cut.", false, 3, false));
        
        
        //enviorments
    	//TODO add all enviorments
        
        
    }

    private void initializeItems() {
        //TODO add all Items
    }

    private void initializeEnemies() {
        //TODO add all Enemies
    }
	
    //TODO make random functions 
    //random POI (based on Envoirment and Structure and sanity)
    //random Item (based on sanity)
    //random Enemy (based on sanity)
    
    
    //getters
	public POI getPlayerLocation() {
		return playerLocation;
	}
	
	public List<Structure> getAllStructures() {
		return allStructures; 
	}
	public List<Envoirment> getAllEnvoirments() {
		return allEnvoirments; 
	}
	
	
    public List<Item> getAllItems() {
    	return allItems; 
    }
    
    public List<Enemy> getAllEnemies() {
    	return allEnemies; 
    }

	
	public void move(Direction direction) {
		//TODO develop move method inside World and POI
	}

}
