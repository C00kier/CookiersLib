package pawel.cookier.ignaczak.cookierslib.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pawel.cookier.ignaczak.cookierslib.repositories.inventory.IInventoryUtility;

public class InventoryUtility implements IInventoryUtility {

    @Override
    public boolean hasEmptyInventorySlot(Player player) {
        Inventory inventory = player.getInventory();

        for (int i = 0; i < 36; i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType().isAir()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Integer getFirstEmptySlotIndex(Player player) {
        Inventory inventory = player.getInventory();

        for (int i = 0; i < 36; i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType().isAir()) {
                return i;
            }
        }

        return null;
    }

    @Override
    public Integer getSlotIndexBasedOnItemStack(Player player, ItemStack targetItem){
        ItemStack[] contents = player.getInventory().getContents();

        for (int i = 0; i < contents.length; i++) {
            ItemStack currentItem = contents[i];

            if (currentItem == null || currentItem.getType() == Material.AIR) {
                continue;
            }

            if (currentItem.equals(targetItem)) {
                return i;
            }
        }

        return null;
    }
}
