package seedu.mealcompanion.command.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.exception.CommandRunException;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.IngredientMetadata;

//@@author TJW0911

/**
 * Represents the "remove" command.
 */

public class RemoveCommand extends ExecutableCommand {

    private static int indexOfExistingIngredient;
    IngredientMetadata ingredient;
    int amount;

    public RemoveCommand(IngredientMetadata ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }


    /**
     * Validates if the remove command can be executed with the arguments provided and throws
     * exception when it cannot be done
     *
     * @param quantity    the quantity of ingredient to be removed
     * @param ingredients the list of ingredients the user has
     * @param name        the name of the ingredient the user inputted
     * @throws MealCompanionException when user input results in unexpected behaviour
     */
    private static void validateInput(int quantity, IngredientList ingredients, String name)
            throws CommandRunException {
        if (indexOfExistingIngredient == -1) {
            throw new CommandRunException("OOPS, ingredient is not in fridge");
        }
        if (quantity > ingredients.get(indexOfExistingIngredient).getQuantity()) {
            throw new CommandRunException("OOPS, quantity to remove is more than quantity in the fridge");
        }
    }

    /**
     * Removes a specified quantity of an ingredient from the ingredient list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity             the quantity of ingredient to be removed
     * @param metadata             the metadata of the ingredient to be removed
     */

    private static void removeIngredient(MealCompanionSession mealCompanionSession, int quantity,
                                         IngredientMetadata metadata, IngredientList ingredients) {
        int fridgeQuantity = ingredients.get(indexOfExistingIngredient).getQuantity();
        assert fridgeQuantity >= quantity : "fridgeQuantity should be more than quantity to be removed";
        int newQuantity = fridgeQuantity - quantity;
        ingredients.get(indexOfExistingIngredient).setQuantity(newQuantity);
        mealCompanionSession.getUi().printMessage(
                String.format("Success! new quantity of %s is %d", metadata.getName().toLowerCase(), newQuantity));
        if (newQuantity == 0) {
            ingredients.remove(indexOfExistingIngredient);
            mealCompanionSession.getUi().printMessage(
                    String.format("All %s has been removed", metadata.getName().toLowerCase()));
        }
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(ingredients);
    }

    /**
     * Executes the remove command
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     */

    public void execute(MealCompanionSession mealCompanionSession) throws CommandRunException {
        IngredientList ingredients = mealCompanionSession.getIngredients();
        indexOfExistingIngredient = ingredients.findIndex(ingredient.getName());
        validateInput(amount, ingredients, ingredient.getName());
        removeIngredient(mealCompanionSession, amount, ingredient, ingredients);
    }
}
