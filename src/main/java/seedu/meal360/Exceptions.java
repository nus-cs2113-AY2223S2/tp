package seedu.meal360;

public class Exceptions {

    // ingredient not found exception
    public static class IngredientNotFoundException extends Exception {
        public IngredientNotFoundException() {
            super("Ingredient not found");
        }
    }

}
