package commands;

import seaOfHands.Player;
import seaOfHands.World;
import seaOfHands.WorldState;

public interface Command {
	//represents one complete player action
	void execute(String[] args, Player player, World world, WorldState worldState);
    String getName(); // optional, for parsing
	String getDescription();
}
