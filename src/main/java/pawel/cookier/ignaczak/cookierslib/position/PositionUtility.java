package pawel.cookier.ignaczak.cookierslib.position;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Implementation of the {@link PositionUtility} interface for handling player-related spatial queries.
 */
public class PositionUtility{

    /**
     * Retrieves all players within a specified radius of a given location.
     *
     * @param position the center location to search around
     * @param radius   the maximum distance from the center to include players
     * @return a {@link Set} of {@link Player} objects located within the radius
     *         of the provided position. Returns an empty set if the world is null.
     */
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
