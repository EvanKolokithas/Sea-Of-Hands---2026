package commands;

import seaOfHands.*;

public class EquipCommand implements Command {

    @Override
    public String getName() {
        return "equip";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {

        if(args.length < 2) {
            System.out.println("Usage: Equip <Weapon Name>");
            return;
        }

        String weaponName = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));

        for(Item item : player.getInventory().getItems()) {

            if(item instanceof Weapon weapon &&
               weapon.getName().equalsIgnoreCase(weaponName)) {

                player.equipWeapon(weapon);
                System.out.println(weapon.getName() + " equipped.");
                return;
            }
        }

        System.out.println("You don't have that weapon.");
    }

	@Override
	public String getDescription() {
		return "Equips a weapon to attack with: equip <weapon name>" ;
	}
}