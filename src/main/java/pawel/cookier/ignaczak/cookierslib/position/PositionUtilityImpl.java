package pawel.cookier.ignaczak.cookierslib.position;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pawel.cookier.ignaczak.cookierslib.repositories.position.PositionUtility;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PositionUtilityImpl implements PositionUtility {

    @Override
    public Set<Player> getPlayersInRadius(Location position, double radius) {
        Set<Player> playersInRadius = ConcurrentHashMap.newKeySet();

        World world = position.getWorld();

        if (world == null) return playersInRadius;

        for (Player player : world.getPlayers()) {
            if (player.getLocation().distance(position) <= radius) {
                playersInRadius.add(player);
            }
        }

        return playersInRadius;
    }
}
