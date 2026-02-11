package seaOfHands;

public class Weapon extends Item implements Usable {

	//Weapons are items that are used to attack enemies
	
	//vars
	private int damage;
	private int breakChance;
	
	//constructor
	public Weapon(String name, String desc, int damage, int breakChance) {
		super(name, desc);
		
		this.damage = damage;
		this.breakChance = breakChance;
	}
	
	//Methods
	
	public int getDamage() {
		return damage;
	}

	public int getBreakChance() {
		return breakChance;
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
