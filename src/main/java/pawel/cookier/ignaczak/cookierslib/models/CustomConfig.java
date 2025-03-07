package pawel.cookier.ignaczak.cookierslib.models;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private final JavaPlugin plugin;
    private final String fileName;
    private File file;
    private FileConfiguration config;

    public CustomConfig(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
    }

    public void loadConfig() {
        if (fileName.equalsIgnoreCase("config.yml")) {
            plugin.saveDefaultConfig();
            this.config = plugin.getConfig();
        } else {
            file = new File(plugin.getDataFolder(), fileName);
            if (!file.exists()) {
                plugin.saveResource(fileName, false);
            }
            this.config = YamlConfiguration.loadConfiguration(file);
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        if (fileName.equalsIgnoreCase("config.yml")) {
            plugin.saveConfig();
        } else {
            try {
                config.save(file);
            } catch (IOException e) {
                plugin.getLogger().severe("Could not save config file: " + file.getName());
                e.printStackTrace();
            }
        }
    }

    public void reloadConfig() {
        if (fileName.equalsIgnoreCase("config.yml")) {
            plugin.reloadConfig();
            config = plugin.getConfig();
        } else {
            config = YamlConfiguration.loadConfiguration(file);
        }
    }
}
