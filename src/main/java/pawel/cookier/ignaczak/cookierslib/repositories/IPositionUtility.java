package pawel.cookier.ignaczak.cookierslib.repositories;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;

public interface IPositionUtility {
    HashSet<Player> getPlayersInRadius(Location position, double radius);
}
