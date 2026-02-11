package seaOfHands;

public class Consumable extends Item implements Usable {
	
	//Consumables are items consumed to restore health or energy
	
	//vars
	private int healthRestore;
	private int energyRestore;
	
	//constructor
	//could do overloaded constructor (one for health one for energy one for both)?
	
	public Consumable(String name, String desc, int healthRestore, int energyRestore) {
		super(name, desc);
		
		this.healthRestore = healthRestore;
		this.energyRestore = energyRestore;
	}
	
	//Methods
	
	public int getHealthRestore() {
		return healthRestore;
	}
	
	public int getEnergyRestore() {
		return energyRestore;
	}

	@Override
	public void use(Player player) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canUse(Player player) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUseDescription() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConsumable() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
