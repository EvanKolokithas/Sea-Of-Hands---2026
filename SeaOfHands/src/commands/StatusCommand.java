package commands;

import seaOfHands.Game;
import seaOfHands.Player;
import seaOfHands.UI;
import seaOfHands.World;
import seaOfHands.WorldState;
import seaOfHands.POI;

public class StatusCommand implements Command {

    @Override
    public String getName() {
        return "status";
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {

        UI.printStatus(player, world, worldState);
    }


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}