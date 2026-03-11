package commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import seaOfHands.Player;
import seaOfHands.World;
import seaOfHands.WorldState;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(Command cmd) {
        commands.put(cmd.getName().toLowerCase(), cmd);
    }

    public Command getCommand(String name) {
        return commands.get(name.toLowerCase());
    }

    public Set<String> getCommandNames() {
        return commands.keySet();
    }

    /**
     * Verb Noun Parser
     * 
     * @param input
     * @param player
     * @param world
     * @param worldState
     */
    public void executeCommand(String input, Player player, World world, WorldState worldState) {
        String[] parts = input.trim().split("\\s+");
        if (parts.length == 0) return;
        String verb = parts[0].toLowerCase();
        Command cmd = commands.get(verb);
        if (cmd != null) {
            cmd.execute(parts, player, world, worldState);
        } else {
            System.out.println("Unknown command: " + verb);
        }
    }
}