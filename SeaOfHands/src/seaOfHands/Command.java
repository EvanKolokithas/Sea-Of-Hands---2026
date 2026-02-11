package seaOfHands;

public interface Command {
	//represents one complete player action
	void execute (Game game) throws Exception;
}
