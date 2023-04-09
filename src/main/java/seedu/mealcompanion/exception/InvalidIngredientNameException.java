package seedu.mealcompanion.exception;

/**
 * Class to throw exceptions specific to MealCompanion
 * when an ingredient name is not found in the database
 */
public class InvalidIngredientNameException extends Exception {
    private String ingredientName;

    public InvalidIngredientNameException(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public String getMessage() {
        return this.ingredientName + " is not a valid ingredient.";
    }
}
