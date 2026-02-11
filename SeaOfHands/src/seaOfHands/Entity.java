package seaOfHands;

public abstract class Entity {
	
	protected int health;
	
	protected boolean isAlive() {
		return this.health > 0;
	}
	
	protected Entity(int health) {
		this.health = health;
	}

}
