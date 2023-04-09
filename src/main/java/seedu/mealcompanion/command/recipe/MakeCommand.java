package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.CommandRunException;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.Recipe;

//@@author TJW0911

/**
 * Represents the "make" command.
 */
public class MakeCommand extends RecipeCommand {

    Recipe recipe;

    public MakeCommand(Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Removes a specified quantity of an ingredient from the ingredient list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity             the quantity of ingredient to be removed
     * @param name                 the name of the ingredient to be removed
     */

    private void removeIngredient(MealCompanionSession mealCompanionSession, int quantity, String name) {
        IngredientList ingredients = mealCompanionSession.getIngredients();
        int indexOfExistingIngredient = ingredients.findIndex(name);
        int fridgeQuantity = ingredients.get(indexOfExistingIngredient).getQuantity();
        assert fridgeQuantity >= quantity : "fridgeQuantity should be more than quantity to be removed";
        int newQuantity = fridgeQuantity - quantity;
        ingredients.get(indexOfExistingIngredient).setQuantity(newQuantity);
        mealCompanionSession.getUi().printMessage(
                String.format("%s has been consumed. New quantity of %s is %d", name, name, newQuantity));
        if (newQuantity == 0) {
            ingredients.remove(indexOfExistingIngredient);
            mealCompanionSession.getUi().printMessage(String.format("All %s has been removed", name));
        }
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(ingredients);
    }

    /**
     * Removes the ingredients needed to make the specified recipe from inventory
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param recipe               the recipe that is to be made
     */
    private void makeRecipe(MealCompanionSession mealCompanionSession, Recipe recipe) {
        IngredientList recipeIngredients = recipe.getIngredients();
        for (int i = 0; i < recipeIngredients.size(); i += 1) {
            int quantityToRemove = recipeIngredients.get(i).getQuantity();
            String name = recipeIngredients.get(i).getMetadata().getName();
            removeIngredient(mealCompanionSession, quantityToRemove, name);
        }
        mealCompanionSession.getUi().printMessage("Success! The ingredients needed were removed from inventory");
    }

    /**
     * Executes the "make" command
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients and recipes
     */

    public void execute(MealCompanionSession mealCompanionSession) throws CommandRunException {
        IngredientList fridgeIngredients = mealCompanionSession.getIngredients();
        if (canMakeRecipe(recipe, fridgeIngredients)
                && !hasAllergen(recipe, mealCompanionSession.getAllergens())) {
            makeRecipe(mealCompanionSession, recipe);
        } else {
            throw new CommandRunException("Current ingredients is insufficient or recipe contains allergens");
        }
    }
}
