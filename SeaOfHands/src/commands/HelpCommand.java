package commands;

import seaOfHands.Player;
import seaOfHands.World;
import seaOfHands.WorldState;

public class HelpCommand implements Command {

    private final CommandManager manager;

    // pass the command manager so we can list commands
    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args, Player player, World world, WorldState worldState) {
        if (args.length == 1) {
            // no extra args: list all commands
        	System.out.println("===== COMMANDS =====");

        	int count = 0;
        	for (String command : manager.getCommandNames()) {
        	    System.out.printf("%-12s", command);// 12 chars left aligned
        	    count++;

        	    if (count % 4 == 0) {
        	        System.out.println();
        	    }
        	}

        	System.out.println("\n====================");
            System.out.println("Type 'help <command>' for more details about a specific command.");
        } else {
            // show help for a specific command
            String target = args[1].toLowerCase();
            Command cmd = manager.getCommand(target);
            if (cmd != null) {
                System.out.println("Help for command '" + target + "':");
                // You could implement a getDescription() method in Command
                System.out.println(cmd.getDescription());
            } else {
                System.out.println("No command named '" + target + "' found.");
            }
        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows a list of commands or detailed info about a specific command.";
    }
}