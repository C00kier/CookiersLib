package pawel.cookier.ignaczak.cookierslib.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import pawel.cookier.ignaczak.cookierslib.repositories.items.IITemCreator;

import java.util.List;
import java.util.Map;

public class ItemCreator implements IITemCreator {

    private final ItemManager itemManager;

    public ItemCreator(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public ItemStack createCustomItem(Material material,
                                      String name,
                                      Integer amount,
                                      List<String> lore,
                                      Map<Enchantment, Integer> enchantmentsWithLevels,
                                      String namespacedKey,
                                      String namespacedValue,
                                      Integer customModelId,
                                      JavaPlugin yourPlugin) {

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();


        if (amount > 1) {
            item.setAmount(amount);
        }

        if (meta == null) {
            return item;
        }

        if (name != null) {
            meta.setDisplayName(name);
        }

        if (lore.size() > 0) {
            meta.setLore(lore);
        }

        if (enchantmentsWithLevels.size() > 0) {
            itemManager.applyEnchantmentsToItemMeta(meta, enchantmentsWithLevels);
        }

        if (namespacedKey != null && namespacedValue != null && yourPlugin != null) {
            itemManager.addNamespacedKeyStringToItemMeta(yourPlugin, meta, namespacedKey, namespacedValue);
        }

        meta.setCustomModelData(customModelId);
        item.setItemMeta(meta);

        return item;
    }

}
