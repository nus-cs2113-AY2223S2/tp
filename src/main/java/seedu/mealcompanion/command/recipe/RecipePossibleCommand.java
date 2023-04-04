package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

//@@author ngyida

/**
 * Represents the "recipe possible" command.
 */
public class RecipePossibleCommand extends RecipeCommand {
    /**
     * List all recipes that can be made using ingredients that are available.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of recipes and ingredients
     */
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            IngredientList fridgeIngredients = mealCompanionSession.getIngredients();
            RecipeList recipes = mealCompanionSession.getRecipes();
            RecipeList possibleRecipes = new RecipeList();
            for (Recipe recipe : recipes.getRecipes()) {
                if (canMakeRecipe(recipe, fridgeIngredients)
                        && !hasAllergen(recipe, mealCompanionSession.getAllergens())) {
                    possibleRecipes.add(recipe);
                }
            }

            if (possibleRecipes.isEmpty()) {
                mealCompanionSession.getUi().printMessage("You cannot make any recipe :c");
                return;
            }

            mealCompanionSession.getUi().printMessage("Here are the recipe(s) that you can make:");
            for (Recipe recipe : possibleRecipes.getRecipes()) {
                String recipeName = recipe.getName();
                int index = recipes.findIndex(recipeName);
                mealCompanionSession.getUi().printMessage(Integer.toString(index + 1) + ". " + recipeName);
            }
        } catch (MealCompanionException e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
