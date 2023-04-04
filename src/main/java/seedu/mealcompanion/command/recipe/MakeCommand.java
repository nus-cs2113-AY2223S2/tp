package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

//@@author TJW0911

/**
 * Represents the "make" command.
 */
public class MakeCommand extends RecipeCommand {

    String recipeNumber;

    public MakeCommand(String argument) {
        this.recipeNumber = argument;
    }

    /**
     * Removes a specified quantity of an ingredient from the ingredient list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity             the quantity of ingredient to be removed
     * @param name                 the name of the ingredient to be removed
     */

    private void removeIngredient(MealCompanionSession mealCompanionSession, int quantity, String name) {
        int indexOfExistingIngredient = mealCompanionSession.getIngredients().findIndex(name);
        int fridgeQuantity = mealCompanionSession.getIngredients().get(indexOfExistingIngredient).getQuantity();
        assert fridgeQuantity >= quantity : "fridgeQuantity should be more than quantity to be removed";
        int newQuantity = fridgeQuantity - quantity;
        mealCompanionSession.getIngredients().get(indexOfExistingIngredient).setQuantity(newQuantity);
        mealCompanionSession.getUi().printMessage(
                String.format("%s has been consumed. New quantity of %s is %d", name, name, newQuantity));
        if (newQuantity == 0) {
            mealCompanionSession.getIngredients().remove(indexOfExistingIngredient);
            mealCompanionSession.getUi().printMessage(String.format("All %s has been removed", name));
        }
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(mealCompanionSession.getIngredients());
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

    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            RecipeList recipes = mealCompanionSession.getRecipes();
            if (recipeNumber == null || recipeNumber.trim().isEmpty()) {
                throw new MealCompanionException("OOPS, recipe number cannot be empty");
            }
            int recipeIndex = Integer.parseInt(recipeNumber) - 1;
            Recipe recipe = recipes.getRecipe(recipeIndex);
            IngredientList fridgeIngredients = mealCompanionSession.getIngredients();
            if (canMakeRecipe(recipe, fridgeIngredients)
                    && !hasAllergen(recipe, mealCompanionSession.getAllergens())) {
                makeRecipe(mealCompanionSession, recipe);
            } else {
                throw new MealCompanionException("Ingredients in inventory is insufficient");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            mealCompanionSession.getUi().printMessage("Oops, please input a valid recipe number!");
        } catch (Exception e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
