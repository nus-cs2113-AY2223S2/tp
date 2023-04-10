package seedu.fst.utils;

import seedu.fst.exceptions.FSTException;
import seedu.fst.exceptions.IllegalValueException;
import seedu.fst.food.Unit;

import java.time.format.DateTimeParseException;

//@@author DavidVin357
/**
 * Utility class for validating arguments to Food constructor
 */
public class Validator {

    public static final String DATE_IS_NOT_VALID = "Date is not valid";
    public static final String QUANTITY_IS_NOT_VALID = "Quantity is not valid";
    public static final String UNIT_IS_NOT_VALID = "Unit is not valid";

    public static boolean isExpiryDateValid(String date) throws FSTException {
        try {
            DateFormatter.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(DATE_IS_NOT_VALID);
        }
        return true;

    }

    public static boolean isQuantityValid(Double q) throws FSTException {
        if (q < 0 || q > 9999) {
            throw new FSTException(QUANTITY_IS_NOT_VALID);
        }
        return true;
    }

    public static boolean isUnitValid(String unitName) throws FSTException {
        for (Unit u : Unit.values()) {
            if (u.abbreviation.equals(unitName)) {
                return true;
            }
        }
        throw new FSTException(UNIT_IS_NOT_VALID);
    }
}

