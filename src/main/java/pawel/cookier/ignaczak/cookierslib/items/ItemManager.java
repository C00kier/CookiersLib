package pawel.cookier.ignaczak.cookierslib.items;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
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

    @Override
    public String getNamespacedKeyValueFromItemStack(JavaPlugin plugin, ItemStack itemStack, String key) {
        if (itemStack == null) return null;

        ItemMeta meta = itemStack.getItemMeta();

        if (meta == null) return null;

        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);

        return meta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
    }

    @Override
    public boolean doesItemStackContainsValueForNamespacedKey(JavaPlugin plugin,
                                                              ItemStack itemStack,
                                                              String key,
                                                              String value) {
        String keyValue = getNamespacedKeyValueFromItemStack(plugin, itemStack, key);

        if (keyValue == null) return false;

        return keyValue.equals(value);
    }

}
