package pawel.cookier.ignaczak.cookierslib.world;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldUtility{

    /**
     * Checks whether the {@link GameRule#KEEP_INVENTORY} is enabled in the player's current world.
     *
     * @param player the player whose world to check
     * @return true if keepInventory is enabled, false if disabled or undefined
     */
    public boolean isKeepInventoryEnabledOnPlayersWorld(Player player) {
        World world = player.getWorld();
        Boolean keepInventory = world.getGameRuleValue(GameRule.KEEP_INVENTORY);

        return keepInventory != null && keepInventory;
    }
}
