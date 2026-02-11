package seaOfHands;

public class CommandParser {

	public Command parse(String input) {
		//cleans up input and splits on any white space
		String[] tokens = input.trim().toLowerCase().split("\\s+");
		
		if(tokens.length == 0) {
			System.out.println("Invalid Command");
			return null;
		}
		
		String verb = tokens[0];
		//TODO implement switch statement for all commands
		
		return null;
		
	}
}
