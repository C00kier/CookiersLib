package pawel.cookier.ignaczak.cookierslib;

import org.bukkit.plugin.java.JavaPlugin;

public final class CookiersLib extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("CookiersLib: enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("CookiersLib: disabled");
    }
}
