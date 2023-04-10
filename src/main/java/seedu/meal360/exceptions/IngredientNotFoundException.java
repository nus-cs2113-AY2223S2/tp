package seedu.meal360.exceptions;

public class IngredientNotFoundException extends Exception {
    public IngredientNotFoundException(String message) {
        super(message);
    }

    public IngredientNotFoundException() {
        super("Ingredient not found");
    }
}
