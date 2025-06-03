package pawel.cookier.ignaczak.cookierslib.repositories.yamlConfig;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public interface YamlMessageUtility {

    String getColorizedMessage(FileConfiguration config, String key);

    String getColorizedMessage(FileConfiguration config, String key, String defaultMessage);

    List<String> getColorizedMessagesFromList(FileConfiguration config, String key);
}
