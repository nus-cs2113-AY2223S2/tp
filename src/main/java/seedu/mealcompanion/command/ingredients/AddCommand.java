package seedu.mealcompanion.command.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.exception.CommandRunException;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.IngredientMetadata;

//@@author TJW0911

/**
 * Represents the "add" command.
 */
public class AddCommand extends ExecutableCommand {

    private static final int MAX_INGREDIENTS = 10000;
    IngredientMetadata ingredient;
    int amount;

    public AddCommand(IngredientMetadata ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    /**
     * Adds a specified quantity of ingredient already in the ingredients list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity             the quantity of ingredient to be added
     * @param index                the index of the ingredient in the ingredients list
     */

    private void addToExistingIngredients(MealCompanionSession mealCompanionSession, int quantity, int index)
            throws CommandRunException {
        IngredientList ingredients = mealCompanionSession.getIngredients();
        int newQuantity = ingredients.get(index).getQuantity() + quantity;
        if (newQuantity > MAX_INGREDIENTS) {
            throw new CommandRunException("OOPS, new total ingredient amount cannot exceed 10000");
        }
        ingredients.get(index).setQuantity(newQuantity);
        mealCompanionSession.getUi().printMessage("Here is the new quantity of the ingredient:");
        mealCompanionSession.getUi().printMessage(String.valueOf(ingredients.get(index)));
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(ingredients);
    }

    /**
     * Adds a specified quantity of a new ingredient to the ingredients list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity             the quantity of ingredient to be added
     * @param metadata             the metadata of the ingredient
     */

    private void addNewIngredient(MealCompanionSession mealCompanionSession, int quantity, IngredientMetadata metadata)
            throws CommandRunException {
        Ingredient ingredient = new Ingredient(metadata, quantity);
        mealCompanionSession.getIngredients().add(ingredient);
        mealCompanionSession.getUi().printMessage("the following ingredient has been added");
        mealCompanionSession.getUi().printMessage(String.valueOf(ingredient));
        mealCompanionSession.getIngredientStorage().writeIngredientToFile(ingredient);
    }


    /**
     * Add an ingredient of specified quantity to ingredients list.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     */

    public void execute(MealCompanionSession mealCompanionSession) throws CommandRunException {
        if (amount <= 0 || amount > MAX_INGREDIENTS) {
            throw new CommandRunException("OOPS, quantity provided must be greater than 0 and not exceed 10000");
        }
        int indexOfExistingIngredient = mealCompanionSession.getIngredients().findIndex(ingredient.getName());
        if (indexOfExistingIngredient == -1) {
            addNewIngredient(mealCompanionSession, amount, ingredient);
        } else {
            addToExistingIngredients(mealCompanionSession, amount, indexOfExistingIngredient);
        }
    }
}
