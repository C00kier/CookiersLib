package pawel.cookier.ignaczak.cookierslib;

import org.bukkit.plugin.java.JavaPlugin;
import pawel.cookier.ignaczak.cookierslib.inventory.InventoryUtility;
import pawel.cookier.ignaczak.cookierslib.items.ItemCreator;
import pawel.cookier.ignaczak.cookierslib.items.ItemManager;
import pawel.cookier.ignaczak.cookierslib.position.PositionUtility;
import pawel.cookier.ignaczak.cookierslib.validation.ValidationUtility;

public final class CookiersLib extends JavaPlugin {

    private ItemManager itemManager;
    private ItemCreator itemCreator;
    private PositionUtility positionUtility;
    private ValidationUtility validationUtility;
    private InventoryUtility inventoryUtility;


    @Override
    public void onEnable() {
        itemManager = new ItemManager();
        itemCreator = new ItemCreator(itemManager);
        positionUtility = new PositionUtility();
        validationUtility = new ValidationUtility();
        inventoryUtility = new InventoryUtility();

        getLogger().info("CookiersLib: enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("CookiersLib: disabled");
    }

    public CookiersLib getInstance(){
        return this;
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
        return inventoryUtility;
    }
}
