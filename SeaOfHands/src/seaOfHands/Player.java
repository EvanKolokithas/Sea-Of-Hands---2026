package seaOfHands;

public class Player extends Entity implements Damagable, Damager{
	
	
	private int stamina;
	//private Inventory inv = new Inventory();
	
	
	
	public Player(int health, int stamina) {
		super(health);
		
		this.stamina = stamina;
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void takeDamage(int damage) {
		this.health -= damage;
		
	}
	
	//getters
	public int getHealth() {
		return this.health;
	}
	
	public int getStamina() {
		return this.stamina;
	}
	
}
