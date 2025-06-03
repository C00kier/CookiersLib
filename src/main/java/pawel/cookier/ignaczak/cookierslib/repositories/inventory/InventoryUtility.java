package pawel.cookier.ignaczak.cookierslib.repositories.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public interface InventoryUtility {
    boolean hasEmptyInventorySlot(Player player);

    Optional<Integer> getSlotIndexBasedOnItemStack(Player player, ItemStack targetItem);

    void populateInventoryWithItemStack(Inventory inventory, ItemStack itemStack);

    void populateEmptySpacesWithItemStack(Inventory inventory, ItemStack itemStack);
}
