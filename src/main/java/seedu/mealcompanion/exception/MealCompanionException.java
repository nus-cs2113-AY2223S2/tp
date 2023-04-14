package seedu.mealcompanion.exception;

/**
 * Class to throw exceptions specific to MealCompanion
 * when unexpected behaviour occurs during runtime.
 */
public class MealCompanionException extends Exception {

    /**
     * Constructor for MealCompanionException that informs user about problems and errors.
     *
     * @param message The error message to be printed to user.
     */
    public MealCompanionException(String message) {
        super(message);
    }
}
