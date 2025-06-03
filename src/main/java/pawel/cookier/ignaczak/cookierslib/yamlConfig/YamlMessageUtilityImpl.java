package pawel.cookier.ignaczak.cookierslib.yamlConfig;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import pawel.cookier.ignaczak.cookierslib.repositories.yamlConfig.YamlMessageUtility;

import java.util.List;
import java.util.stream.Collectors;

public class YamlMessageUtilityImpl implements YamlMessageUtility {

    @Override
    public String getColorizedMessage(FileConfiguration config, String key) {
        if (config == null) {
            throw new IllegalStateException("YamlMessageUtility: FileConfiguration has not been set!");
        }

        String errorMessage = String.format("[%s] config key not found",key);
        String configMessage = config.getString(key, errorMessage);
        return ChatColor.translateAlternateColorCodes('&', configMessage);
    }

    @Override
    public String getColorizedMessage(FileConfiguration config, String key, String defaultMessage) {
        if (config == null) {
            throw new IllegalStateException("YamlMessageUtility: FileConfiguration has not been set!");
        }
        String configMessage = config.getString(key, defaultMessage);
        return ChatColor.translateAlternateColorCodes('&', configMessage);
    }

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
