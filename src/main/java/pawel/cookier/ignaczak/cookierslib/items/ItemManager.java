package pawel.cookier.ignaczak.cookierslib.items;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import pawel.cookier.ignaczak.cookierslib.repositories.IItemManager;

import java.util.Map;

public class ItemManager implements IItemManager {

    @Override
    public void applyEnchantmentsToItemMeta(ItemMeta meta, Map<Enchantment, Integer> enchantmentsWithLevels) {
        for (Enchantment enchantment : enchantmentsWithLevels.keySet()) {
            int enchantmentLevel = enchantmentsWithLevels.get(enchantment);
            if (enchantment != null && enchantmentLevel > 0) {
                meta.addEnchant(enchantment, enchantmentLevel, true);
            }
        }
    }

    @Override
    public void addNamespacedKeyStringToItemMeta(JavaPlugin plugin, ItemMeta meta, String key, String value) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        dataContainer.set(namespacedKey, PersistentDataType.STRING, value);
    }

}
