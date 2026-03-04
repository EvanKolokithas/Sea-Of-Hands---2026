package seaOfHands;

public class Player extends Entity implements Damagable, Damager{
	
	//vars
	
	private int energy;
	private Inventory inv = new Inventory();
	private int sanity;
	
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
	
	public int getStamina() {
		return this.energy;
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
	    this.health = health;
	}
	
	
	//reset energy
	
	public void resetEnergy() {
		energy = 5;
	}
	
	public void resetEnergy(int energy) {
		this.energy = energy;
	}
}
