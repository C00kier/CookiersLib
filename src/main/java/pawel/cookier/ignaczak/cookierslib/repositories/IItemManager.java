package pawel.cookier.ignaczak.cookierslib.repositories;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public interface IItemManager {
    void applyEnchantmentsToItemMeta(ItemMeta meta, Map<Enchantment, Integer> enchantmentsWithLevels);

    void addNamespacedKeyStringToItemMeta(JavaPlugin plugin, ItemMeta meta, String key, String value);
}
