package pawel.cookier.ignaczak.cookierslib.repositories.position;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;

public interface PositionUtility {
    Set<Player> getPlayersInRadius(Location position, double radius);
}
