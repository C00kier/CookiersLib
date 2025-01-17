package pawel.cookier.ignaczak.cookierslib.repositories.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public interface IItemManager {
    void applyEnchantmentsToItemMeta(ItemMeta meta, Map<Enchantment, Integer> enchantmentsWithLevels);

    void applyEnchantmentsToItemStack(ItemStack itemStack, Map<Enchantment, Integer> enchantmentsWithLevels);

    void addNamespacedKeyStringToItemMeta(JavaPlugin plugin, ItemMeta meta, String key, String value);

    void addNamespacedKeyStringToItemStack(JavaPlugin plugin, ItemStack itemStack, String key, String value);

    String getNamespacedKeyValueFromItemStack(JavaPlugin plugin, ItemStack itemStack, String key);

    boolean doesItemStackContainsValueForNamespacedKey(JavaPlugin plugin, ItemStack itemStack, String key, String value);

    void setDisplayNameToItemStack(ItemStack itemStack, String displayName);

    void setEmptyStringAsDisplayName(ItemStack itemStack);

    void setLoreToItemStack(ItemStack itemStack, List<String> lore);

    boolean hasItemStacksSameEnchantments(ItemStack item1, ItemStack item2);

    boolean areItemStackListsEqual(List<ItemStack> list1, List<ItemStack> list2);
}
