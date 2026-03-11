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
	private List<String> allEnemies;
	private static List<Consumable> allConsumables;
	private static List <Weapon> allWeapons;

	
	
	
	//constructor
	public World() {
		// initialize collections
		allStructures = new ArrayList<>();
		allEnviorments = new ArrayList<>();
        allWeapons = new ArrayList<>();
        allConsumables = new ArrayList<>();
        allEnemies = new ArrayList<>();
        
        
		// populate collections with values from javaIO files
        initializeItems();
        allEnemies = initailizeEnemies();
        initializePOIs();
        
        
		playerLocation = allStructures.getFirst();
		startingLocation = allStructures.getFirst();
	}
	
	private void initializeItems() {
    	//consumables (15) 
    	
    	//name | desc | healthRestore | energyRestore | sanity ; 
    	
    	allConsumables = initailizeConsumables();
        
        //weapons (15)
    	
    	//name | desc | damage | breakChance | sanity ;
    	
    	allWeapons = initailizeWeapons();
    }
	
    private void initializePOIs() {
    	//structures (12) raidable and campable auto set to false and randomized when initialized later unless always true
    	
    	//name | desc | camp | sanity | raid ; where camp and raid are only set to true if always true
    	
    	allStructures = initializeStructures();
        
        //enviorments (15)
    	
    	//name | desc | camp | sanity | forage
    	
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
                int sanity = Integer.parseInt(data[3].trim());
                boolean loot = Boolean.parseBoolean(data[4].trim());

                structures.add(new Structure(name, description, camp, loot, sanity, allConsumables, allWeapons));
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

                enviorments.add(new Environment(name, description, camp, sanityTier, forage, allConsumables));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return enviorments;
    }
    
   
    private static List<Consumable> initailizeConsumables() {
    	List<Consumable> consumables = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(".\\src\\JavaIOFiles\\Consumables.txt"))) {

            String line;
            
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] data = line.split("\\|");
                	
                
                String name = data[0].trim();
                String description = data[1].trim();
                int healthRestore = Integer.parseInt(data[2].trim());
                int energyRestore = Integer.parseInt(data[3].trim());
                int sanity = Integer.parseInt(data[4].trim());
                consumables.add(new Consumable(name, description, sanity, healthRestore, energyRestore));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return consumables;
    	
    }
    
    private static List<Weapon> initailizeWeapons(){
    	List<Weapon> weapons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(".\\src\\JavaIOFiles\\Weapons.txt"))) {

            String line;
            
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] data = line.split("\\|");
                	
                
                String name = data[0].trim();
                String description = data[1].trim();
                int damage = Integer.parseInt(data[2].trim());
                double breakChance = Double.parseDouble(data[3].trim());
                int sanity = Integer.parseInt(data[4].trim());
                weapons.add(new Weapon(name, description, sanity, damage, breakChance));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return weapons;
    }

    private static List<String> initailizeEnemies() {
    	List<String> enemies = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(".\\src\\JavaIOFiles\\Tide.txt"))) {

            String line;
            
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) continue;
                	
                
                String name = line.trim();
                enemies.add(name);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return enemies;
    }
  
    	
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
    
    public String getRandomEnemyName() {

        int index = (int)(Math.random() * allEnemies.size());

        return allEnemies.get(index);
    }
    
    public Enemy generateEnemy(int sanityLevel) {

        String name = getRandomEnemyName();

        int health;
        int damage;

        switch(sanityLevel) {

            case 1:
                health = 6 + (int)(Math.random()*5);
                damage = 1 + (int)(Math.random()*2);
                break;

            case 2:
                health = 10 + (int)(Math.random()*7);
                damage = 2 + (int)(Math.random()*3);
                break;

            default:
                health = 16 + (int)(Math.random()*9);
                damage = 3 + (int)(Math.random()*4);
                break;
        }

        return new Enemy(name, health, damage);
    }
    
    //getters
	public POI getPlayerLocation() {
		return playerLocation;
	}
	
	public void setPlayerLocation(POI poi, Player player) {
		playerLocation = poi;

	    System.out.println("You arrive at the " + poi.getName());

	    UI.printStatus(player, this, Game.getWorldState());
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
    
    public List<String> getAllEnemies() {
    	return allEnemies; 
    }
}
