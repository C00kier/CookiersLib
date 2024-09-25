package pawel.cookier.ignaczak.cookierslib.position;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pawel.cookier.ignaczak.cookierslib.repositories.position.IPositionUtility;

import java.util.HashSet;

public class PositionUtility implements IPositionUtility {

    @Override
    public HashSet<Player> getPlayersInRadius(Location position, double radius) {
        HashSet<Player> playersInRadius = new HashSet<>();

        World world = position.getWorld();

        if (world != null) {
            for (Player player : world.getPlayers()) {
                if (player.getLocation().distance(position) <= radius) {
                    playersInRadius.add(player);
                }
            }
        }

        return playersInRadius;
    }
}
