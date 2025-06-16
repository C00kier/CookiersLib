package pawel.cookier.ignaczak.cookierslib.items;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager{

    /**
     * Applies enchantments to an {@link ItemMeta} object.
     *
     * @param meta the item meta to modify.
     * @param enchantmentsWithLevels a map of enchantments and their corresponding levels.
     */
    public void applyEnchantmentsToItemMeta(ItemMeta meta, Map<Enchantment, Integer> enchantmentsWithLevels) {
        for (Enchantment enchantment : enchantmentsWithLevels.keySet()) {
            int enchantmentLevel = enchantmentsWithLevels.get(enchantment);
            if (enchantment != null && enchantmentLevel > 0) {
                meta.addEnchant(enchantment, enchantmentLevel, true);
            }
        }
    }

    /**
     * Applies enchantments directly to an {@link ItemStack}.
     *
     * @param itemStack the item to enchant.
     * @param enchantmentsWithLevels a map of enchantments and their corresponding levels.
     */
    public void applyEnchantmentsToItemStack(ItemStack itemStack, Map<Enchantment, Integer> enchantmentsWithLevels) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return;

        applyEnchantmentsToItemMeta(meta, enchantmentsWithLevels);
        itemStack.setItemMeta(meta);
    }

    /**
     * Adds a namespaced key-value pair to an {@link ItemMeta}.
     *
     * @param plugin the plugin instance used to create the key.
     * @param meta the item meta to modify.
     * @param key the key name.
     * @param dataType the {@link PersistentDataType} for the value.
     * @param value the value to store.
     */
    public <T, Z> void addNamespacedKeyToItemMeta(
            JavaPlugin plugin,
            ItemMeta meta,
            String key,
            PersistentDataType<T, Z> dataType,
            Z value
    ) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        dataContainer.set(namespacedKey, dataType, value);
    }

    /**
     * Adds a namespaced key-value pair directly to an {@link ItemStack}.
     *
     * @param plugin the plugin instance.
     * @param itemStack the item to modify.
     * @param key the key name.
     * @param dataType the {@link PersistentDataType}.
     * @param value the value to store.
     */
    public <T, Z> void addNamespacedKeyToItemStack(
            JavaPlugin plugin,
            ItemStack itemStack,
            String key,
            PersistentDataType<T, Z> dataType,
            Z value
    ) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return;

        addNamespacedKeyToItemMeta(plugin, meta, key, dataType, value);
        itemStack.setItemMeta(meta);
    }

    /**
     * Retrieves a value from an {@link ItemStack}'s persistent data container using a namespaced key.
     *
     * @param plugin the plugin instance.
     * @param itemStack the item to read.
     * @param key the key to look for.
     * @param dataType the data type expected.
     * @return the stored value or {@code null} if not found.
     */
    public <T, Z> Z getNamespacedKeyValueFromItemStack(
            JavaPlugin plugin,
            ItemStack itemStack,
            String key,
            PersistentDataType<T, Z> dataType
    ) {
        if (itemStack == null) return null;

        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return null;

        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        return meta.getPersistentDataContainer().get(namespacedKey, dataType);
    }

    /**
     * Checks if a given {@link ItemStack} contains a specific value under a namespaced key.
     *
     * @param plugin the plugin instance.
     * @param itemStack the item to check.
     * @param key the key to look for.
     * @param dataType the data type used.
     * @param expectedValue the expected value.
     * @return {@code true} if the value matches; otherwise, {@code false}.
     */
    public <T, Z> boolean doesItemStackContainValueForNamespacedKey(
            JavaPlugin plugin,
            ItemStack itemStack,
            String key,
            PersistentDataType<T, Z> dataType,
            Z expectedValue
    ) {
        Z actualValue = getNamespacedKeyValueFromItemStack(plugin, itemStack, key, dataType);

        if (actualValue == null) return false;

        return actualValue.equals(expectedValue);
    }
    /**
     * Checks whether the given ItemStack has a persistent data value associated with the specified NamespacedKey and data type.
     *
     * @param plugin     The JavaPlugin instance used to create the NamespacedKey.
     * @param itemStack  The ItemStack to check.
     * @param key        The string key to look for, namespaced under the plugin.
     * @param type       The PersistentDataType to check for (e.g., PersistentDataType.STRING).
     * @param <T>        The primitive type of the data (e.g., String, Integer).
     * @param <Z>        The complex type of the data (e.g., String, Integer).
     * @return {@code true} if the key exists in the item's persistent data container and is of the specified type; otherwise, {@code false}.
     */
    public <T, Z> boolean hasNamespacedKey(JavaPlugin plugin, ItemStack itemStack, String key, PersistentDataType<T, Z> type) {
        if (itemStack == null || !itemStack.hasItemMeta()) {
            return false;
        }

        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) {
            return false;
        }

        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        PersistentDataContainer container = meta.getPersistentDataContainer();

        return container.has(namespacedKey, type);
    }


    /**
     * Sets the display name of an {@link ItemStack}.
     *
     * @param itemStack the item to modify.
     * @param displayName the new display name.
     */
    public void setDisplayNameToItemStack(ItemStack itemStack, String displayName) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(displayName);
            itemStack.setItemMeta(meta);
        }
    }

    /**
     * Sets an empty colored string as the display name.
     *
     * @param itemStack the item to modify.
     */
    public void setEmptyStringAsDisplayName(ItemStack itemStack) {
        setDisplayNameToItemStack(itemStack, String.valueOf(ChatColor.DARK_GRAY));
    }

    /**
     * Sets the lore (hover text) on an {@link ItemStack}.
     *
     * @param itemStack the item to modify.
     * @param lore the lore lines.
     */
    public void setLoreToItemStack(ItemStack itemStack, List<String> lore){
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null) return;
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }

    /**
     * Compares whether two {@link ItemStack}s have identical enchantments.
     *
     * @param item1 the first item.
     * @param item2 the second item.
     * @return {@code true} if enchantments are equal; otherwise, {@code false}.
     */
    public boolean hasItemStacksSameEnchantments(ItemStack item1, ItemStack item2) {
        if (item1 == null || item2 == null) {
            return false;
        }

        ItemMeta meta1 = item1.getItemMeta();
        ItemMeta meta2 = item2.getItemMeta();

        if (meta1 == null || meta2 == null) {
            return false;
        }

        Map<Enchantment, Integer> enchantments1;
        Map<Enchantment, Integer> enchantments2;

        if (meta1 instanceof EnchantmentStorageMeta && meta2 instanceof EnchantmentStorageMeta) {
            // For enchanted books
            enchantments1 = ((EnchantmentStorageMeta) meta1).getStoredEnchants();
            enchantments2 = ((EnchantmentStorageMeta) meta2).getStoredEnchants();
        } else {
            // For regular items
            enchantments1 = meta1.getEnchants();
            enchantments2 = meta2.getEnchants();
        }

        return enchantments1.equals(enchantments2);
    }

    /**
     * Compares whether two lists of {@link ItemStack}s contain the same items and quantities.
     *
     * @param list1 the first list.
     * @param list2 the second list.
     * @return {@code true} if the lists are equivalent; otherwise, {@code false}.
     */
    public boolean areItemStackListsEqual(List<ItemStack> list1, List<ItemStack> list2) {
        Map<ItemStack, Integer> combinedList1 = combineItemStacks(list1);
        Map<ItemStack, Integer> combinedList2 = combineItemStacks(list2);

        if (combinedList1.size() != combinedList2.size()) {
            return false;
        }

        for (Map.Entry<ItemStack, Integer> entry : combinedList1.entrySet()) {
            ItemStack item = entry.getKey();
            int amount1 = entry.getValue();

            if (!combinedList2.containsKey(item)) {
                return false;
            }

            int amount2 = combinedList2.get(item);
            if (amount1 != amount2) {
                return false;
            }
        }

        return true;
    }

    /**
     * Combines a list of {@link ItemStack}s into a map with single-item keys and total quantities as values.
     * Used for inventory comparison.
     *
     * @param items the list to combine.
     * @return a map of unique {@link ItemStack}s to their total amount.
     */
    private Map<ItemStack, Integer> combineItemStacks(List<ItemStack> items) {
        Map<ItemStack, Integer> combinedItems = new HashMap<>();

        for (ItemStack item : items) {
            if (item == null) {
                continue;
            }

            ItemStack singleItem = item.clone();
            singleItem.setAmount(1);

            combinedItems.merge(singleItem, item.getAmount(), Integer::sum);
        }

        return combinedItems;
    }
}
