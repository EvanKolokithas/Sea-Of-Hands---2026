package seaOfHands;

public abstract class Entity {
	
	protected int health;
	
	public boolean isAlive() {
		return this.health > 0;
	}
	
	public Entity(int health) {
		this.health = health;
	}

}
