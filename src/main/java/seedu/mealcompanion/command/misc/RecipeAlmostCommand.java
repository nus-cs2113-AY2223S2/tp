package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

public class RecipeAlmostCommand extends RecipePossibleCommand{

    private int almostCanMakeRecipe(Recipe recipe, IngredientList indredients) {
        IngredientList recipeIngredients = recipe.getIngredients();
        int missingIngredientCount = 0;
        for (Ingredient recipeIngredient : recipeIngredients.getIngredients()) {
            if (!hasEnoughIngredient(recipeIngredient, indredients)) {
                missingIngredientCount += 1;
            }
        }
        return missingIngredientCount;
    }

    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        RecipeList recipeList = mealCompanionSession.getRecipes();
        int indexCount = 1;
        mealCompanionSession.getUi().printMessage("These are the recipes that you almost can make: ");
        for (Recipe recipe : recipeList.getRecipes()) {
            int numberOfMissingIngredients = almostCanMakeRecipe(recipe, mealCompanionSession.getIngredients());
            if (numberOfMissingIngredients > 0 && numberOfMissingIngredients <= 2) {
                mealCompanionSession.getUi().printMessage(indexCount + ". " + recipe.getName()
                        + " (number of missing ingredients: "
                        + numberOfMissingIngredients + ")");
                indexCount++;
            }
        }
        if (indexCount > 1) {
            mealCompanionSession.getUi().printMessage("For more information on the ingredients that you are missing, " +
                    "try command: recipe need <recipe_name>");
        } else {
            mealCompanionSession.getUi().printMessage("There are no recipes that you almost can make!");
        }
    }
}
