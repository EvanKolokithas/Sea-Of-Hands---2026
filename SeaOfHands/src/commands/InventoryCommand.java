package commands;

import seaOfHands.Player;
import seaOfHands.World;
import seaOfHands.WorldState;

public class InventoryCommand implements Command {

    @Override
    public String getName() {
        return "inventory";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {
        player.getInventory().printInventory();
    }

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}