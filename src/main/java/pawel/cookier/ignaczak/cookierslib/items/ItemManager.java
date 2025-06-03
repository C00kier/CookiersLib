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
import pawel.cookier.ignaczak.cookierslib.repositories.items.IItemManager;

import java.util.HashMap;
import java.util.List;
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
    public void applyEnchantmentsToItemStack(ItemStack itemStack, Map<Enchantment, Integer> enchantmentsWithLevels) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return;

        applyEnchantmentsToItemMeta(meta, enchantmentsWithLevels);
        itemStack.setItemMeta(meta);
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public void setDisplayNameToItemStack(ItemStack itemStack, String displayName) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(displayName);
            itemStack.setItemMeta(meta);
        }
    }

    @Override
    public void setEmptyStringAsDisplayName(ItemStack itemStack) {
        setDisplayNameToItemStack(itemStack, String.valueOf(ChatColor.DARK_GRAY));
    }

    @Override
    public void setLoreToItemStack(ItemStack itemStack, List<String> lore){
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null) return;
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }

    @Override
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

    @Override
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
