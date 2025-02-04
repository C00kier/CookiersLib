package pawel.cookier.ignaczak.cookierslib.yamlConfig;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import pawel.cookier.ignaczak.cookierslib.repositories.yamlConfig.IYamlMessageUtility;

import java.util.List;
import java.util.stream.Collectors;

public class YamlMessageUtility implements IYamlMessageUtility {

    @Override
    public String getColorizedMessage(FileConfiguration config, String key) {
        if (config == null) {
            throw new IllegalStateException("YamlMessageUtility: FileConfiguration has not been set!");
        }

        String errorMessage = ("[%s] config key not found").formatted(key);
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
