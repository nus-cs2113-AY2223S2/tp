package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

//@@author jingyaaa

/**
 * Represents the "recipe almost" command.
 */

public class RecipeAlmostCommand extends RecipeCommand {

    /**
     * Check the number of missing ingredients to make a recipe in the ingredient list.
     *
     * @param recipe      target recipe to check
     * @param ingredients ingredient list containing
     * @return return number of missing ingredients to make a recipe
     */

    private int almostCanMakeRecipe(Recipe recipe, IngredientList ingredients) {
        IngredientList recipeIngredients = recipe.getIngredients();
        int missingIngredientCount = 0;
        for (Ingredient recipeIngredient : recipeIngredients.getIngredients()) {
            if (!hasEnoughIngredient(recipeIngredient, ingredients)) {
                missingIngredientCount += 1;
            }
        }
        return missingIngredientCount;
    }

    /**
     * List all recipes that almost can be made using the ingredients available.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of recipes and ingredients
     */

    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        RecipeList recipeList = mealCompanionSession.getRecipes();
        int indexCount = 1;
        int recipeNumber = 1;
        mealCompanionSession.getUi().printMessage("These are the recipes that you almost can make: ");
        for (Recipe recipe : recipeList.getRecipes()) {
            int numberOfMissingIngredients = almostCanMakeRecipe(recipe, mealCompanionSession.getIngredients());
            if (numberOfMissingIngredients > 0 && numberOfMissingIngredients <= 3) {
                mealCompanionSession.getUi().printMessage(recipeNumber + ". " + recipe.getName()
                        + " (number of missing ingredients: "
                        + numberOfMissingIngredients + ")");
                indexCount++;
            }
            recipeNumber++;
        }
        if (indexCount > 1) {
            mealCompanionSession.getUi().printMessage("For more information on the ingredients that you are missing, " +
                    "try command: recipe need <recipe_name>");
        } else {
            mealCompanionSession.getUi().printMessage("There are no recipes that you almost can make!");
        }
    }
}
