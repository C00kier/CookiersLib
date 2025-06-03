package pawel.cookier.ignaczak.cookierslib.models;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * A wrapper class for handling custom YAML configuration files in a Bukkit plugin.
 */
public class CustomConfig {

    private final JavaPlugin plugin;
    private final String fileName;
    private final File file;
    private FileConfiguration config;

    /**
     * Creates a new instance of CustomConfig for a specific configuration file.
     *
     * @param plugin   the plugin instance.
     * @param fileName the name of the YAML file (e.g., "settings.yml").
     */
    public CustomConfig(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);
        loadConfig();
    }

    /**
     * Loads the configuration file from the plugin's data folder.
     * If it doesn't exist, the default resource is saved from the JAR.
     */
    public void loadConfig() {
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Gets the loaded {@link FileConfiguration} object.
     *
     * @return the current FileConfiguration instance.
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Saves the configuration to disk.
     * If saving fails, logs an error and prints the stack trace.
     */
    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save config file: " + file.getName());
            e.printStackTrace();
        }
    }
}
