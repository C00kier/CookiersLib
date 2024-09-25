package pawel.cookier.ignaczak.cookierslib.repositories.validation;

import org.bukkit.Sound;

import java.util.List;

public interface IValidationUtility {
    boolean isLoreValid(List<String> loreList);

    boolean isStringValueValid(String value);

    boolean isDoublePositiveOrZero(Double value);

    boolean isIntPositiveOrZero(int value);

    boolean isSoundValid(Sound sound);

    boolean hasCorrectArgsQuantity(int quantity, String[] args);
}
