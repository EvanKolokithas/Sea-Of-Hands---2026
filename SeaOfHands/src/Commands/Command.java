package Commands;

import seaOfHands.Game;

public interface Command {
	//represents one complete player action
	void execute (Game game) throws Exception;
}
