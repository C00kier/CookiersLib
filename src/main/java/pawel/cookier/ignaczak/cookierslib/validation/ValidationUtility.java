package pawel.cookier.ignaczak.cookierslib.validation;

import org.bukkit.Sound;

import java.util.List;

/**
 * Implementation of {@link ValidationUtility} that provides various utility methods
 * for validating common types used in plugin development.
 */
public class ValidationUtility{

    /**
     * Checks if the provided lore list is non-null.
     *
     * @param loreList the list of lore strings
     * @return true if the list is not null, false otherwise
     */
    public boolean isLoreValid(List<String> loreList) {
        return loreList != null;
    }

    /**
     * Validates a string to ensure it is non-null and not empty.
     *
     * @param value the string to validate
     * @return true if the string is non-null and not empty, false otherwise
     */
    public boolean isStringValueValid(String value) {
        return value != null && !value.isEmpty();
    }

    /**
     * Checks if a Double value is positive or zero.
     *
     * @param value the Double to check
     * @return true if value is not null and ≥ 0, false otherwise
     */
    public boolean isDoublePositiveOrZero(Double value) {
        return value != null && value >= 0;
    }

    /**
     * Checks if an integer value is positive or zero.
     *
     * @param value the int to check
     * @return true if value ≥ 0, false otherwise
     */
    public boolean isIntPositiveOrZero(int value) {
        return value >= 0;
    }

    /**
     * Validates that a given {@link Sound} is not null.
     *
     * @param sound the sound to validate
     * @return true if sound is not null, false otherwise
     */
    public boolean isSoundValid(Sound sound) {
        return sound != null;
    }

    /**
     * Checks whether the number of provided arguments matches the expected quantity.
     *
     * @param quantity the expected number of arguments
     * @param args     the actual arguments array
     * @return true if the length of args equals quantity, false otherwise
     */
    public boolean hasCorrectArgsQuantity(int quantity, String[] args) {
        return args.length == quantity;
    }
}
