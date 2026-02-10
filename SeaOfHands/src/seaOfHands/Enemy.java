package seaOfHands;

public class Enemy extends Entity implements Damagable, Damager {
	
	private int damage;
	
	public Enemy(int health, int damage) {
		super(health);
		
		this.damage = damage;
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
		health -= damage;
	}
	
	//getters
	public int getHealth() {
		return this.health;
	}
	
	public int getDamage() {
		return this.damage;
	}
}
