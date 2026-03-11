package commands;

import seaOfHands.Game;
import seaOfHands.Player;
import seaOfHands.World;
import seaOfHands.WorldState;

public class EndTurnCommand implements Command {

	
    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {
    	Game.endTurn(player); // call your existing endTurn logic
    	if(Game.isRunning()) {
    		Game.printStatus(player);
    	}
    }

    @Override
    public String getName() {
        return "endturn";
    }
    
    @Override
    public String getDescription() {
    	return "Ends the players turn";
    }

	
}