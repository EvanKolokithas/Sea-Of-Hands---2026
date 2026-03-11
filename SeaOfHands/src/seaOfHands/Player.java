package seaOfHands;

public class Player extends Entity implements Damagable, Damager{
	
	//vars
	
	private int energy;
	private Inventory inv = new Inventory();
	private int sanity;
	private Weapon equippedWeapon;
	
	//constructor
	
	public Player(int health, int energy, int sanity) {
		super(health);
		
		this.energy = energy;
		this.sanity = sanity;
	}

	//Methods
	
	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void takeDamage(int damage) {
		this.health -= damage;
		
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getEnergy() {
		return this.energy;
	}
	
	public void useEnergy() {
		energy --;
		System.out.println("Energy -1");
	}
	
	public int getSanity() {
		return this.sanity;
	}
	
	public void incSanity() {
		sanity ++;
	}
	
	//health
	//TODO healable interface?
	
	public void setHealth(int health) {
	    this.health = Math.min(health, 10);
	}
	
	
	//reset energy
	
	public void resetEnergy() {
		energy = 5;
	}
	
	public void resetEnergy(int energy) {
		this.energy = energy;
	
	}
	public void setEnergy(int energy) {
		if (energy != 6){
			this.energy = Math.min(energy, 5);

		}
	}
	
	public Inventory getInventory() {
		return inv;
	}
	public Weapon getEquippedWeapon() {
	    return equippedWeapon;
	}

	public void equipWeapon(Weapon weapon) {
	    equippedWeapon = weapon;
	}
}
