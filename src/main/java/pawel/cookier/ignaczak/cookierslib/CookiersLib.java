package pawel.cookier.ignaczak.cookierslib;

import org.bukkit.plugin.java.JavaPlugin;
import pawel.cookier.ignaczak.cookierslib.items.ItemCreator;
import pawel.cookier.ignaczak.cookierslib.items.ItemManager;

public final class CookiersLib extends JavaPlugin {

    private ItemManager itemManager;
    private ItemCreator itemCreator;

    @Override
    public void onEnable() {
        itemManager = new ItemManager();
        itemCreator = new ItemCreator(itemManager);

        getLogger().info("CookiersLib: enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("CookiersLib: disabled");
    }

    public ItemManager getItemManager() {
        return this.itemManager;
    }

    public ItemCreator getItemCreator() {
        return this.itemCreator;
    }
}
