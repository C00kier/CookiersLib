package pawel.cookier.ignaczak.cookierslib.repositories.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public interface IITemCreator {

    <T, Z> ItemStack createCustomItem(Material material,
                                      String name,
                                      Integer amount,
                                      List<String> lore,
                                      Map<Enchantment, Integer> enchantmentsWithLevels,
                                      String namespacedKey,
                                      Z namespacedValue,
                                      PersistentDataType<T, Z> dataType,
                                      Integer customModelId,
                                      JavaPlugin yourPlugin);
}
