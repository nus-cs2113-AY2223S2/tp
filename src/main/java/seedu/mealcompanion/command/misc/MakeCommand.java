package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

//@@author TJW0911
/**
 * Represents the "make" command.
 */
public class MakeCommand extends RecipeCommand {

    String name;

    public MakeCommand(String argument) {
        this.name = argument;
    }

    //@@author TJW0911

    /**
     * Removes a specified quantity of an ingredient from the ingredient list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity the quantity of ingredient to be removed
     * @param name the name of the ingredient to be removed
     */

    private void removeIngredient(MealCompanionSession mealCompanionSession, Double quantity, String name) {
        int indexOfExistingIngredient = mealCompanionSession.getIngredients().findIndex(name);
        double fridgeQuantity = mealCompanionSession.getIngredients().get(indexOfExistingIngredient).getQuantity();
        assert fridgeQuantity >= quantity: "fridgeQuantity should be more than quantity to be removed";
        double newQuantity = fridgeQuantity - quantity;
        mealCompanionSession.getIngredients().get(indexOfExistingIngredient).setQuantity(newQuantity);
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
     * @param recipe the recipe that is to be made
     */
    private void makeRecipe(MealCompanionSession mealCompanionSession, Recipe recipe) {
        IngredientList recipeIngredients = recipe.getIngredients();
        for (int i = 0; i < recipeIngredients.size(); i += 1) {
            double quantityToRemove = recipeIngredients.get(i).getQuantity();
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
            if(name == null || name.trim().isEmpty()) {
                throw new MealCompanionException("recipe name cannot be blank");
            }
            Recipe recipe = recipes.getRecipe(name);
            IngredientList fridgeIngredients = mealCompanionSession.getIngredients();
            if (canMakeRecipe(recipe, fridgeIngredients)) {
                makeRecipe(mealCompanionSession, recipe);
            } else {
                throw new MealCompanionException("Ingredients in inventory is insufficient");
            }
        } catch (Exception e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
