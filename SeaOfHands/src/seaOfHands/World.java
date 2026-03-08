package seaOfHands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;





public class World {
	
	//Class for player position
	
	//vars
	
	private POI playerLocation;
	private final POI startingLocation;
	
	private List<Structure> allStructures;
	private List<Environment> allEnviorments;
	private List<Enemy> allEnemies;
	private List<Consumable> allConsumables;
	private List<Tool> allTools;
	private List <Weapon> allWeapons;

	
	
	
	//constructor
	public World() {
		// initialize collections
		allStructures = new ArrayList<>();
		allEnviorments = new ArrayList<>();
        allWeapons = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allTools = new ArrayList<>();
        
		// populate collections with values from javaIO files
        initializePOIs();
        initializeItems();
        initializeEnemies();
        
		playerLocation = allStructures.getFirst();
		startingLocation = allStructures.getFirst();
	}
	
    private void initializePOIs() {
    	//structures (12) raidable and campable auto set to false and randomized when initialized later unless always true
    	
    	//name | desc | camp | sanity| raid ; where camp and raid are only set to true if always true
    	
    	allStructures = initializeStructures();
        
        //enviorments
    	//TODO add all enviorments
    	
    	allEnviorments = initailizeEnviorments();
        
        
        
    }
    
    public static List<Structure> initializeStructures() {

        List<Structure> structures = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(".\\src\\JavaIOFiles\\Structures.txt"))) {

            String line;
            
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] data = line.split("\\|");
                	
                
                String name = data[0].trim();
                String description = data[1].trim();
                boolean camp = Boolean.parseBoolean(data[2].trim());
                int sanityTier = Integer.parseInt(data[3].trim());
                boolean raid = Boolean.parseBoolean(data[4].trim());

                structures.add(new Structure(name, description, camp, sanityTier, raid));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return structures;
    }

    public static List<Environment> initailizeEnviorments() {

        List<Environment> enviorments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(".\\src\\JavaIOFiles\\Enviorments.txt"))) {

            String line;
            
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] data = line.split("\\|");
                	
                
                String name = data[0].trim();
                String description = data[1].trim();
                boolean camp = Boolean.parseBoolean(data[2].trim());
                int sanityTier = Integer.parseInt(data[3].trim());
                boolean forage = Boolean.parseBoolean(data[4].trim());

                enviorments.add(new Environment(name, description, camp, sanityTier, forage));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return enviorments;
    }
    
    private void initializeItems() {
        //TODO add all Items
    	
    	//Consumables #
    	
    	//sanity 1
    	
    	//sanity 2
    	
    	//sanity 3
    	
    	
    	//Tools #
    	
    	//sanity 1
    	
    	//sanity 2
    	
    	//sanity 3
    	
    	//Weapons #
    	
    	//sanity 1
    	
    	//sanity 2
    	
    	//sanity 3
    	
    }

    private void initializeEnemies() {
        //TODO add all Enemies
    }
	
    //TODO make random functions 
  
    	
    //Predicate functional interface and Supplier 
    	
    public <T extends POI> T getRandomPOI(List<T> allPOIs) {

    	if (allPOIs.isEmpty()) {
    		System.out.println("allPOIs is empty");
            return null;
            
        }
    	
        int index = (int) (Math.random() * allPOIs.size());
        return allPOIs.get(index);
    }
    
    /**
     * Gets a list of n random POIs with the max of 1 structure
     * 
     * @param n
     * @param playerSanity
     * @param current
     * 
     * @return
     */
    public List<POI> getRandomPOIs(int n, int playerSanity) {

        List<POI> choices = new ArrayList<>();
        boolean hasStructure = playerLocation instanceof Structure;
        
        List<Environment> availableEnvs = allEnviorments.stream()
                .filter(e -> e != playerLocation && e != startingLocation)
                .filter(e -> e.getSanityLevel() <= playerSanity && e.getSanityLevel() >= playerSanity - 1)
                .collect(Collectors.toList());

        List<Structure> availableStructures = allStructures.stream()
                .filter(s -> s != playerLocation && s != startingLocation)
                .filter(s -> s.getSanityLevel() <= playerSanity && s.getSanityLevel() >= playerSanity - 1)
                .collect(Collectors.toList());
        
        
        for (int i = 0; i < n; i++) {

            boolean chooseStructure = false;

            if (!hasStructure && !availableStructures.isEmpty()) {
                chooseStructure = Math.random() < 0.2;
            }

            if (chooseStructure && !availableStructures.isEmpty()) {
                Structure st = getRandomPOI(availableStructures);
                choices.add(st);
                availableStructures.remove(st);
                hasStructure = true;
            } else if (!availableEnvs.isEmpty()) {
                Environment env = getRandomPOI(availableEnvs);
                choices.add(env);
                availableEnvs.remove(env);
            } else {
                // no more valid POIs, stop early
                break;
            }
        }

        return choices;
    }
  
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
	public List<Environment> getAllEnvoirments() {
		return allEnviorments; 
	}
	
	
    public List<Weapon> getAllWeapons() {
    	return allWeapons; 
    }
    public List<Consumable> getAllConsumables() {
    	return allConsumables; 
    }
    public List<Tool> getAllTools() {
    	return allTools; 
    }
    
    public List<Enemy> getAllEnemies() {
    	return allEnemies; 
    }

	
	public void move(Direction direction) {
		//TODO develop move method inside World and POI
	}

}
