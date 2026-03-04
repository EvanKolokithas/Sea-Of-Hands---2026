package seaOfHands;

import java.util.function.*;


public class FunctionalInterfaceDemo {

    public static void main(String[] args) {

    	System.out.println("Running functional interface demo...");
    	
    	//Enemy for demo
    	Enemy enemy = new Enemy("testEnemy", (int)(Math.random() * 10), 3);
    	
    	//Player for demo (half health)
    	Player player = new Player(5, 5, 1);
    	
    	
    	/**
    	 * Demonstrates a Supplier functional interface
    	 * returns a random amount of damage
    	 */   	 
    	Supplier<Integer> randomDamage = () -> (int)(Math.random() * 10) + 1;
    	
    	int damage = randomDamage.get();
    	System.out.println("You take " + damage +" damage.");
    	
    	
    	/**
    	 * Demonstrates a Predicate functional interface
    	 * Tests whether an enemy is alive or not
    	 */
    	
    	Predicate<Enemy> isAlive = e -> e.getHealth() > 0;
    	
    	
    	
    	if(isAlive.test(enemy)) {
    		System.out.println(enemy.getName() + " is still alive");
    	}
    	else {
    		System.out.println(enemy.getName() + " is not alive");
    	}
    	
    	
    	/**
    	 * Demonstrates a Consumer functional interface
    	 * Applies damage to an enemy
    	 */
    	
    	Consumer<Enemy> damageEnemy = e -> e.takeDamage(5);
    	
    	
    	damageEnemy.accept(enemy);
    	
    	
    	/**
    	 * Demonstrates a Function functional interface
    	 * Converts an enemy to a string
    	 */
    	Function<Enemy, String> enemyToString = e -> e.getName() + " (HP: " + e.getHealth() + ")";
    	
    	System.out.println(enemyToString.apply(enemy));
    	
    	
    	/**
    	 * Demonstrates a UnaryOperator functional interface
    	 * Heals the player 2 hp
    	 */
    	UnaryOperator<Integer> healPlayer = hp -> hp + 2;
    	
    	player.setHealth(healPlayer.apply(player.getHealth()));
    	
    	System.out.println("Player healed 2 health and is now at " + player.getHealth() + " health.");
    	

    }
}