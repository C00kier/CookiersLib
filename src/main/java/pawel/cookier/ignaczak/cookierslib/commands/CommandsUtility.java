package pawel.cookier.ignaczak.cookierslib.commands;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class CommandsUtility {

    /**
     * Registers a command from plugin.yml with both a {@link CommandExecutor}
     * and a {@link TabCompleter}.
     *
     * @param plugin       The JavaPlugin instance.
     * @param commandName  The name of the command as defined in plugin.yml.
     * @param executor     The logic to execute when the command is run.
     * @param tabCompleter The logic for tab completion suggestions.
     * @throws NullPointerException if the command is not found in plugin.yml.
     */
    public void registerCommandWithTabCompleter(JavaPlugin plugin, String commandName, CommandExecutor executor, TabCompleter tabCompleter) {
        Objects.requireNonNull(plugin.getCommand(commandName),
                "Command not found: " + commandName).setExecutor(executor);
        Objects.requireNonNull(plugin.getCommand(commandName),
                "Command not found: " + commandName).setTabCompleter(tabCompleter);
    }

    /**
     * Registers a command from plugin.yml with only a {@link CommandExecutor}.
     *
     * @param plugin      The JavaPlugin instance.
     * @param commandName The name of the command as defined in plugin.yml.
     * @param executor    The logic to execute when the command is run.
     * @throws NullPointerException if the command is not found in plugin.yml.
     */
    public void registerCommandWithoutTabCompleter(JavaPlugin plugin, String commandName, CommandExecutor executor) {
        Objects.requireNonNull(plugin.getCommand(commandName),
                "Command not found: " + commandName).setExecutor(executor);
    }

    /**
     * Dynamically registers a command at runtime using reflection, without requiring plugin.yml entry.
     * Includes permission checks and optional tab completion.
     *
     * @param commandName The name of the command.
     * @param executor    The command logic to be executed.
     * @param tabCompleter The tab completer logic for suggestions.
     * @param permissions A list of required permissions. If null or empty, the command is unrestricted.
     * @param plugin      The plugin instance registering the command.
     */
    public void registerDynamicCommand(String commandName, CommandExecutor executor, TabCompleter tabCompleter, List<String> permissions, JavaPlugin plugin) {
        try {
            Field commandMapField = getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            SimpleCommandMap commandMap = (SimpleCommandMap) commandMapField.get(getServer());

            String pluginPrefix = plugin.getName().toLowerCase();

            Command command = new Command(commandName) {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
                    if (permissions != null && !permissions.isEmpty()) {
                        boolean hasPermission = permissions.stream().anyMatch(sender::hasPermission);
                        if (!hasPermission) {
                            sender.sendMessage("§cYou do not have permission to use this command!");
                            return true;
                        }
                    }
                    return executor.onCommand(sender, this, label, args);
                }

                @Override
                public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, String[] args) {
                    if (permissions != null && !permissions.isEmpty()) {
                        boolean hasPermission = permissions.stream().anyMatch(sender::hasPermission);
                        if (!hasPermission) {
                            return new ArrayList<>();
                        }
                    }
                    List<String> result = tabCompleter.onTabComplete(sender, this, alias, args);
                    return result == null ? new ArrayList<>() : result;
                }

                @Override
                public boolean testPermissionSilent(@NotNull CommandSender sender) {
                    if (permissions != null && !permissions.isEmpty()) {
                        return permissions.stream().anyMatch(sender::hasPermission);
                    }
                    return true;
                }
            };

            commandMap.register(pluginPrefix, command);
            getLogger().info("Command registered: " + pluginPrefix + ":" + commandName + " (Permissions: " + (permissions != null ? permissions : "None") + ")");

        } catch (Exception e) {
            e.printStackTrace();
            getLogger().warning(String.format("Failed to register command %s dynamically!", commandName));
        }
    }
}
