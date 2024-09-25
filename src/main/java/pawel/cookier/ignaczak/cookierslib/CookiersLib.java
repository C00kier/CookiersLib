package pawel.cookier.ignaczak.cookierslib;

import pawel.cookier.ignaczak.cookierslib.commands.CommandsUtility;
import pawel.cookier.ignaczak.cookierslib.inventory.InventoryUtility;
import pawel.cookier.ignaczak.cookierslib.items.ItemCreator;
import pawel.cookier.ignaczak.cookierslib.items.ItemManager;
import pawel.cookier.ignaczak.cookierslib.position.PositionUtility;
import pawel.cookier.ignaczak.cookierslib.validation.ValidationUtility;

public final class CookiersLib {

    private final ItemManager itemManager;
    private final ItemCreator itemCreator;
    private final PositionUtility positionUtility;
    private final ValidationUtility validationUtility;
    private final InventoryUtility inventoryUtility;
    private final CommandsUtility commandsUtility;

    public CookiersLib() {
        this.itemManager = new ItemManager();
        this.itemCreator = new ItemCreator(itemManager);
        this.positionUtility = new PositionUtility();
        this.validationUtility = new ValidationUtility();
        this.inventoryUtility = new InventoryUtility();
        this.commandsUtility = new CommandsUtility();
    }

    // Getter methods for your utilities
    public ItemManager getItemManager() {
        return this.itemManager;
    }

    public ItemCreator getItemCreator() {
        return this.itemCreator;
    }

    public PositionUtility getPositionUtility() {
        return this.positionUtility;
    }

    public ValidationUtility getValidationUtility() {
        return this.validationUtility;
    }

    public InventoryUtility getInventoryUtility() {
        return this.inventoryUtility;
    }

    public CommandsUtility getCommandsUtility() {
        return commandsUtility;
    }
}
