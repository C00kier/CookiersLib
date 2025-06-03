package pawel.cookier.ignaczak.cookierslib.yamlConfig;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import pawel.cookier.ignaczak.cookierslib.repositories.yamlConfig.YamlMessageUtility;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link YamlMessageUtility} for retrieving and colorizing
 * messages from a YAML configuration file.
 */
public class YamlMessageUtilityImpl implements YamlMessageUtility {

    /**
     * Retrieves and colorizes a string message from the config by a given key.
     *
     * @param config the FileConfiguration object
     * @param key the configuration key
     * @return the colorized message, or a formatted error message if the key is not found
     * @throws IllegalStateException if config is null
     */
    @Override
    public String getColorizedMessage(FileConfiguration config, String key) {
        if (config == null) {
            throw new IllegalStateException("YamlMessageUtility: FileConfiguration has not been set!");
        }

        String errorMessage = String.format("[%s] config key not found",key);
        String configMessage = config.getString(key, errorMessage);
        return ChatColor.translateAlternateColorCodes('&', configMessage);
    }

    /**
     * Retrieves and colorizes a string message from the config using a fallback default message.
     *
     * @param config the FileConfiguration object
     * @param key the configuration key
     * @param defaultMessage the fallback message if the key is not found
     * @return the colorized message
     * @throws IllegalStateException if config is null
     */
    @Override
    public String getColorizedMessage(FileConfiguration config, String key, String defaultMessage) {
        if (config == null) {
            throw new IllegalStateException("YamlMessageUtility: FileConfiguration has not been set!");
        }
        String configMessage = config.getString(key, defaultMessage);
        return ChatColor.translateAlternateColorCodes('&', configMessage);
    }

    /**
     * Retrieves and colorizes a list of string messages from the config.
     *
     * @param config the FileConfiguration object
     * @param key the configuration key pointing to a list
     * @return a list of colorized messages
     * @throws IllegalStateException if config is null
     */
    @Override
    public List<String> getColorizedMessagesFromList(FileConfiguration config, String key) {
        if (config == null) {
            throw new IllegalStateException("YamlMessageUtility: FileConfiguration has not been set!");
        }
        List<String> messages = config.getStringList(key);
        return messages.stream()
                .map(message -> ChatColor.translateAlternateColorCodes('&', message))
                .collect(Collectors.toList());
    }
}
