package pawel.cookier.ignaczak.cookierslib.repositories.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public interface ItemManager {
    void applyEnchantmentsToItemMeta(ItemMeta meta, Map<Enchantment, Integer> enchantmentsWithLevels);

    void applyEnchantmentsToItemStack(ItemStack itemStack, Map<Enchantment, Integer> enchantmentsWithLevels);

    <T, Z> void addNamespacedKeyToItemMeta(
            JavaPlugin plugin,
            ItemMeta meta,
            String key,
            PersistentDataType<T, Z> dataType,
            Z value
    );

    <T, Z> void addNamespacedKeyToItemStack(
            JavaPlugin plugin,
            ItemStack itemStack,
            String key,
            PersistentDataType<T, Z> dataType,
            Z value
    );

    <T, Z> Z getNamespacedKeyValueFromItemStack(
            JavaPlugin plugin,
            ItemStack itemStack,
            String key,
            PersistentDataType<T, Z> dataType
    );

    <T, Z> boolean doesItemStackContainValueForNamespacedKey(
            JavaPlugin plugin,
            ItemStack itemStack,
            String key,
            PersistentDataType<T, Z> dataType,
            Z expectedValue
    );

    void setDisplayNameToItemStack(ItemStack itemStack, String displayName);

    void setEmptyStringAsDisplayName(ItemStack itemStack);

    void setLoreToItemStack(ItemStack itemStack, List<String> lore);

    boolean hasItemStacksSameEnchantments(ItemStack item1, ItemStack item2);

    boolean areItemStackListsEqual(List<ItemStack> list1, List<ItemStack> list2);
}
