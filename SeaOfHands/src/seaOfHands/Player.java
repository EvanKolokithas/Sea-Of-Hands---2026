package seaOfHands;

public class Player extends Entity implements Damagable, Damager{
	
	//vars
	
	private int energy;
	private Inventory inv = new Inventory();
	
	//constructor
	
	public Player(int health, int energy) {
		super(health);
		
		this.energy = energy;
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
	
}
