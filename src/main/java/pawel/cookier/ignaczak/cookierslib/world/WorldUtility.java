package pawel.cookier.ignaczak.cookierslib.world;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pawel.cookier.ignaczak.cookierslib.repositories.world.IWorldUtility;

public class WorldUtility implements IWorldUtility {

    @Override
    public boolean isKeepInventoryEnabledOnPlayersWorld(Player player) {
        World world = player.getWorld();
        Boolean keepInventory = world.getGameRuleValue(GameRule.KEEP_INVENTORY);

        return keepInventory != null && keepInventory;
    }
}
