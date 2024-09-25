package pawel.cookier.ignaczak.cookierslib.repositories;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public interface IITemCreator {

    ItemStack createCustomItem(Material material,
                               String name,
                               Integer amount,
                               List<String> lore,
                               Map<Enchantment, Integer> enchantmentsWithLevels,
                               String namespacedKey,
                               String namespacedValue,
                               Integer customModelId,
                               JavaPlugin yourPlugin);
}
