package seedu.exceptions;

import seedu.definitions.MealTypes;

public class InvalidMealException extends LifeTrackerException {
    public InvalidMealException(String input) {
        super(System.lineSeparator() + "Invalid meal type: " + input +
            "! Supported meal types: " + MealTypes.getSupportedMealTypes());
    }
    
}
