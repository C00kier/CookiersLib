package pawel.cookier.ignaczak.cookierslib;

import pawel.cookier.ignaczak.cookierslib.commands.CommandsUtilityImpl;
import pawel.cookier.ignaczak.cookierslib.inventory.InventoryUtilityImpl;
import pawel.cookier.ignaczak.cookierslib.items.ItemCreatorImpl;
import pawel.cookier.ignaczak.cookierslib.items.ItemManagerImpl;
import pawel.cookier.ignaczak.cookierslib.position.PositionUtilityImpl;
import pawel.cookier.ignaczak.cookierslib.repositories.commands.CommandsUtility;
import pawel.cookier.ignaczak.cookierslib.repositories.inventory.InventoryUtility;
import pawel.cookier.ignaczak.cookierslib.repositories.items.ItemCreator;
import pawel.cookier.ignaczak.cookierslib.repositories.items.ItemManager;
import pawel.cookier.ignaczak.cookierslib.repositories.position.PositionUtility;
import pawel.cookier.ignaczak.cookierslib.repositories.validation.ValidationUtility;
import pawel.cookier.ignaczak.cookierslib.repositories.yamlConfig.YamlMessageUtility;
import pawel.cookier.ignaczak.cookierslib.validation.ValidationUtilityImpl;
import pawel.cookier.ignaczak.cookierslib.yamlConfig.YamlMessageUtilityImpl;

public class CookiersLib {

    private final ItemManager itemManager;
    private final ItemCreator itemCreator;
    private final PositionUtility positionUtility;
    private final ValidationUtility validationUtility;
    private final InventoryUtility inventoryUtility;
    private final CommandsUtility commandsUtility;
    private final YamlMessageUtility yamlMessageUtility;

    public CookiersLib() {
        this.itemManager = new ItemManagerImpl();
        this.itemCreator = new ItemCreatorImpl(itemManager);
        this.positionUtility = new PositionUtilityImpl();
        this.validationUtility = new ValidationUtilityImpl();
        this.inventoryUtility = new InventoryUtilityImpl();
        this.commandsUtility = new CommandsUtilityImpl();
        this.yamlMessageUtility = new YamlMessageUtilityImpl();
    }

    public YamlMessageUtility getYamlMessageUtility() {
        return yamlMessageUtility;
    }

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
