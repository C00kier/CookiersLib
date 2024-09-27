package pawel.cookier.ignaczak.cookierslib.repositories.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IInventoryUtility{
    boolean hasEmptyInventorySlot(Player player);

    Integer getFirstEmptySlotIndex(Player player);

    Integer getSlotIndexBasedOnItemStack(Player player, ItemStack targetItem);
}
