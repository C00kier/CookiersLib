package pawel.cookier.ignaczak.cookierslib.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;


public class InventoryUtility{

    /**
     * Checks whether the player has at least one empty slot in their inventory.
     *
     * @param player the player whose inventory will be checked.
     * @return true if an empty slot is found, false otherwise.
     */
    public boolean hasEmptyInventorySlot(Player player) {
        Inventory inventory = player.getInventory();

        return inventory.firstEmpty() != -1;
    }

    /**
     * Gets the first slot index where the specified item is found in the player's inventory.
     *
     * @param player      the player whose inventory is searched.
     * @param targetItem  the item to look for.
     * @return an {@link Optional} containing the index if found, or empty if not found.
     */
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

    /**
     * Fills the entire inventory with the specified {@link ItemStack}, overriding any existing items.
     *
     * @param inventory the inventory to fill.
     * @param itemStack the item to populate the inventory with.
     */
    public void populateInventoryWithItemStack(Inventory inventory, ItemStack itemStack){
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, itemStack);
        }
    }

    /**
     * Populates only the empty (null) slots in the inventory with the specified {@link ItemStack}.
     *
     * @param inventory the inventory to partially populate.
     * @param itemStack the item to place in empty slots.
     */
    public void populateEmptySpacesWithItemStack(Inventory inventory, ItemStack itemStack){
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null) continue;
            inventory.setItem(i, itemStack);
        }
    }

    /**
     * Checks whether the player has a sufficient amount of a specific material in their inventory.
     *
     * @param player        the player to check.
     * @param material      the type of material to count.
     * @param neededAmount  the required quantity.
     * @return true if the player has at least the needed amount, false otherwise.
     */
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

    /**
     * Removes the specified amount of a given material from the player's inventory.
     * Will remove items across multiple stacks if needed.
     *
     * @param player         the player whose inventory will be modified.
     * @param material       the material to remove.
     * @param amountToRemove the total amount to remove.
     */
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
