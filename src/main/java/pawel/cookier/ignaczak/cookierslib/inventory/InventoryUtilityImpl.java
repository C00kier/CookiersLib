package pawel.cookier.ignaczak.cookierslib.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pawel.cookier.ignaczak.cookierslib.repositories.inventory.InventoryUtility;

import java.util.Optional;

public class InventoryUtilityImpl implements InventoryUtility {

    @Override
    public boolean hasEmptyInventorySlot(Player player) {
        Inventory inventory = player.getInventory();

        return inventory.firstEmpty() != -1;
    }

    @Override
    public Optional<Integer> getSlotIndexBasedOnItemStack(Player player, ItemStack targetItem){
        ItemStack[] contents = player.getInventory().getContents();

        for (int i = 0; i < contents.length; i++) {
            ItemStack currentItem = contents[i];

            if (currentItem == null || currentItem.getType() == Material.AIR) {
                continue;
            }

            if (currentItem.equals(targetItem)) {
                return Optional.of(i);
            }
        }

        return Optional.empty();
    }

    @Override
    public void populateInventoryWithItemStack(Inventory inventory, ItemStack itemStack){
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, itemStack);
        }
    }

    @Override
    public void populateEmptySpacesWithItemStack(Inventory inventory, ItemStack itemStack){
        for (int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) != null) continue;
            inventory.setItem(i, itemStack);
        }
    }

    @Override
    public boolean hasEnoughMaterialInInventory(Player player, Material material, int neededAmount) {
        int totalAmount = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null || item.getType() != material) continue;

            totalAmount += item.getAmount();
            if (totalAmount >= neededAmount) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeMaterialFromInventory(Player player, Material material, int amountToRemove) {
        ItemStack[] contents = player.getInventory().getContents();

        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];

            if (item != null && item.getType() == material) {
                int stackAmount = item.getAmount();

                if (stackAmount <= amountToRemove) {
                    amountToRemove -= stackAmount;
                    contents[i] = null;
                } else {
                    item.setAmount(stackAmount - amountToRemove);
                    break;
                }
            }
        }

        player.getInventory().setContents(contents);
        player.updateInventory();
    }
}
