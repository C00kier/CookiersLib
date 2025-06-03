package pawel.cookier.ignaczak.cookierslib.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import pawel.cookier.ignaczak.cookierslib.repositories.items.ItemCreator;
import pawel.cookier.ignaczak.cookierslib.repositories.items.ItemManager;

import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link ItemCreator} interface for generating customized {@link ItemStack} instances
 * with optional metadata such as display name, lore, enchantments, namespaced data, and model data.
 */
public class ItemCreatorImpl implements ItemCreator {

    private final ItemManager itemManager;

    /**
     * Constructs a new ItemCreatorImpl.
     *
     * @param itemManager the item manager used for applying additional metadata (e.g. enchantments, namespaced keys).
     */
    public ItemCreatorImpl(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    /**
     * Creates a customized {@link ItemStack} with optional display name, lore, enchantments,
     * namespaced key data, and model data.
     *
     * @param material              the {@link Material} type of the item.
     * @param name                  the display name of the item (nullable).
     * @param amount                the quantity of items in the stack (nullable).
     * @param lore                  the lore text shown on hover (nullable).
     * @param enchantmentsWithLevels map of {@link Enchantment} to level, applied to the item (nullable).
     * @param namespacedKey         the key under which custom data is stored (nullable).
     * @param namespacedValue       the custom value to store in the item's persistent data container (nullable).
     * @param dataType              the {@link PersistentDataType} representing the type of namespacedValue (nullable).
     * @param customModelId         the custom model ID used for custom resource packs (nullable).
     * @param yourPlugin            the instance of your {@link JavaPlugin}, required for namespaced key usage (nullable).
     * @param <T>                   the primitive type used in {@link PersistentDataType} (e.g., Integer, String).
     * @param <Z>                   the complex type stored (e.g., Integer, String).
     * @return the fully configured {@link ItemStack}.
     */
    @Override
    public <T, Z> ItemStack createCustomItem(Material material,
                                             String name,
                                             Integer amount,
                                             List<String> lore,
                                             Map<Enchantment, Integer> enchantmentsWithLevels,
                                             String namespacedKey,
                                             Z namespacedValue,
                                             PersistentDataType<T, Z> dataType,
                                             Integer customModelId,
                                             JavaPlugin yourPlugin) {

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (amount != null) {
            item.setAmount(amount);
        }

        if (meta == null) {
            return item;
        }

        if (name != null) {
            meta.setDisplayName(name);
        }

        if (lore != null) {
            meta.setLore(lore);
        }

        if (enchantmentsWithLevels != null && !enchantmentsWithLevels.isEmpty()) {
            itemManager.applyEnchantmentsToItemMeta(meta, enchantmentsWithLevels);
        }

        if (namespacedKey != null && namespacedValue != null && yourPlugin != null && dataType != null) {
            itemManager.addNamespacedKeyToItemMeta(
                    yourPlugin,
                    meta,
                    namespacedKey,
                    dataType,
                    namespacedValue
            );
        }

        if (customModelId != null) {
            meta.setCustomModelData(customModelId);
        }

        item.setItemMeta(meta);

        return item;
    }
}
