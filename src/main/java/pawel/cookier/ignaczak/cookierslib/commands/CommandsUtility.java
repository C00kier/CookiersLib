package pawel.cookier.ignaczak.cookierslib.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import pawel.cookier.ignaczak.cookierslib.repositories.commands.ICommandsUtility;

import java.util.Objects;

public class CommandsUtility implements ICommandsUtility {

    @Override
    public void registerCommandWithTabCompleter(JavaPlugin plugin, String commandName, CommandExecutor executor) {
        Objects.requireNonNull(plugin.getCommand(commandName),
                "Command not found: " + commandName).setExecutor(executor);
        Objects.requireNonNull(plugin.getCommand(commandName),
                "Command not found: " + commandName).setTabCompleter((TabCompleter) executor);
    }
}
