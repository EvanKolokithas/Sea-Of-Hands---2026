package seaOfHands;

public class Enemy extends Entity implements Damagable, Damager {
	
	private int damage;
	private String name;
	
	public Enemy(String name, int health, int damage) {
		super(health);
		
		this.name = name;
		this.damage = damage;
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void takeDamage(int damage) {
		health = Math.max(health - damage, 0);
	}
	
	//getters
	public int getHealth() {
		return health;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public String getName() {
		return name;
	}
}
