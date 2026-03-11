package seaOfHands;

public class Weapon extends Item implements Usable {

	//Weapons are items that are used to attack enemies
	
	//vars
	private int damage;
	private double breakChance;
	
	//constructor
	public Weapon(String name, String desc, int sanity, int damage, double breakChance) {
		super(name, desc, sanity);
		
		this.damage = damage;
		this.breakChance = breakChance;
	}
	
	//Methods
	
	public int getDamage() {
		return damage;
	}

	public double getBreakChance() {
		return breakChance;
	}
	
	
	
	@Override
	public String getInfo() {
		return name + " : " + desc + "\nDamage " + damage + " : Break Chance " + (breakChance * 100) + "%";
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
