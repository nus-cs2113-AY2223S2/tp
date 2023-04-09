package seedu.mealcompanion.exception;

public class InvalidIngredientNameException extends Exception {
    private String ingredientName;

    public InvalidIngredientNameException(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
