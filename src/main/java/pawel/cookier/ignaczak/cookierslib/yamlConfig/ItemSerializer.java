package pawel.cookier.ignaczak.cookierslib.yamlConfig;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * A utility class to serialize ItemStacks to a YAML file.
 */
public class ItemSerializer {

    /**
     * Saves a single ItemStack to a specified path within a specified file.
     * If the file or directories don't exist, they will be created.
     *
     * @param itemToSave The ItemStack object to serialize.
     * @param fileName   The name of the file to save to (e.g., "items.yml").
     * @param pathInFile The key/path inside the YAML file where the item will be stored (e.g., "my_special_item").
     */
    public void saveItemToFile(JavaPlugin plugin, ItemStack itemToSave, String fileName, String pathInFile) {
        File file = new File(plugin.getDataFolder(), fileName);

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set(pathInFile, itemToSave);

        try {
            config.save(file);
            plugin.getLogger().info("Successfully saved item to " + fileName + " at path '" + pathInFile + "'");
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save item to file " + fileName);
            e.printStackTrace();
        }
    }
}
