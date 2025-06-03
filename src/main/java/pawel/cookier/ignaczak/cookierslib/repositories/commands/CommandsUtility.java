package pawel.cookier.ignaczak.cookierslib.repositories.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public interface CommandsUtility {
    void registerCommandWithTabCompleter(JavaPlugin plugin, String commandName, CommandExecutor executor, TabCompleter tabCompleter);

    void registerCommandWithoutTabCompleter(JavaPlugin plugin, String commandName, CommandExecutor executor);

    void registerDynamicCommand(String commandName, CommandExecutor executor, TabCompleter tabCompleter, List<String> permissions, JavaPlugin plugin);
}
