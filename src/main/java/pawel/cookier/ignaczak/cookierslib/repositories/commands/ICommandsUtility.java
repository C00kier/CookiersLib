package pawel.cookier.ignaczak.cookierslib.repositories.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public interface ICommandsUtility {
    void registerCommandWithTabCompleter(JavaPlugin plugin, String commandName, CommandExecutor executor);
}
