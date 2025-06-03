package pawel.cookier.ignaczak.cookierslib.validation;

import org.bukkit.Sound;
import pawel.cookier.ignaczak.cookierslib.repositories.validation.ValidationUtility;

import java.util.List;

public class ValidationUtilityImpl implements ValidationUtility {
    @Override
    public boolean isLoreValid(List<String> loreList) {
        return loreList != null;
    }

    @Override
    public boolean isStringValueValid(String value) {
        return value != null && !value.isEmpty();
    }

    @Override
    public boolean isDoublePositiveOrZero(Double value) {
        return value != null && value >= 0;
    }

    @Override
    public boolean isIntPositiveOrZero(int value) {
        return value >= 0;
    }

    @Override
    public boolean isSoundValid(Sound sound) {
        return sound != null;
    }

    @Override
    public boolean hasCorrectArgsQuantity(int quantity, String[] args) {
        return args.length == quantity;
    }
}
