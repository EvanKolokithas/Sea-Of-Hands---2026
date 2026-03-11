package seaOfHands;

import java.util.Scanner;

import commands.CommandManager;
import commands.UseCommand;

public class Combat {

    /**
     * Starts combat with the given player and enemy.
     */
    public static void startCombat(Player player, Enemy enemy, Scanner scanner) {
    	
        double enemyTotalHealth = enemy.getHealth();
        CommandManager manager = new CommandManager();
        
            while (enemy.getHealth() > 0 && player.getHealth() > 0) {

                UI.printCombat(enemy, player);

                String choice = scanner.nextLine().trim();
                	
                
                switch (choice) {
                	case "help":
                		manager.executeCommand(choice, player, Game.getWorld(), Game.getWorldState());
                    	continue;
                    	
                	case "inventory":
                		manager.executeCommand(choice, player, Game.getWorld(), Game.getWorldState());
                    	continue;
                    	
                	case "status":
                		manager.executeCommand(choice, player, Game.getWorld(), Game.getWorldState());
                    	continue;

                    case "1":
                        attack(player, enemy);
                        break;

                    case "2":
                        System.out.println("Type the item name (or 'cancel'):");
                        String itemName = scanner.nextLine().trim();
                        UseCommand useCmd = new UseCommand();
                        useCmd.execute(("use " + itemName).split(" "), player, Game.getWorld(), Game.getWorldState());
                        // Enemy turn handled inside UseCommand
                        continue; // Skip extra enemy attack here

                    case "3":
                        if (attemptEscape(enemy, enemyTotalHealth)) {
                            System.out.println("You escape the combat!");
                            Game.getWorldState().endCombat();
                            return;
                        } else {
                            System.out.println("You fail to escape!");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Pick 1, 2, or 3.");
                        continue;
                }

                // Enemy attacks if still alive
                if (enemy.getHealth() > 0) {
                    enemyTurn(player, enemy);
                }
            }
        

        if (player.getHealth() <= 0) {
            System.out.println("You collapse under the enemy's assault...");
            Game.stopGame();
        } else if (enemy.getHealth() <= 0) {
            System.out.println(enemy.getName() + " collapses back into the tide.");
            Game.getWorldState().endCombat();
        }
    }

    /**
     * Player attacks enemy using equipped weapon if any, or default damage.
     */
    private static void attack(Player player, Enemy enemy) {
        int damage = 1; // base damage
        if (player.getEquippedWeapon() != null) {
            damage = player.getEquippedWeapon().getDamage();
            if(Math.random() < player.getEquippedWeapon().getBreakChance()) {
            	System.out.println("Your " + player.getEquippedWeapon().getName() + " broke dealing + 2 damage.");
            	damage += 2;
            	player.equipWeapon(null);
            }
        }

        enemy.takeDamage(damage);
        System.out.println("You strike for " + damage + " damage.");
    }

    /**
     * Enemy attacks the player.
     */
    public static void enemyTurn(Player player, Enemy enemy) {
        int damage = enemy.getDamage();
        player.takeDamage(damage);
        System.out.println(enemy.getName() + " hits you for " + damage + " damage.");
    }

    /**
     * Attempt to escape combat based on enemy health.
     * More likely to escape when enemy is low.
     */
    private static boolean attemptEscape(Enemy enemy, double enemyTotalHealth) {
        double healthRatio = (double) enemy.getHealth() / enemyTotalHealth;
        double escapeChance = 0.2 + (1 - healthRatio) * 0.6; // 0.2 to 0.8
        return Math.random() < escapeChance;
    }
}