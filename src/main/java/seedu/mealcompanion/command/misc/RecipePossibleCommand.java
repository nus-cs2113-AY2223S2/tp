package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
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
        IngredientList fridgeIngredients = mealCompanionSession.getIngredients();
        RecipeList recipes = mealCompanionSession.getRecipes();
        int index = 1;
        mealCompanionSession.getUi().printMessage("Here are the recipe(s) that you can make:");
        for (Recipe recipe : recipes.getRecipes()) {
            if (canMakeRecipe(recipe, fridgeIngredients)) {
                mealCompanionSession.getUi().printMessage(Integer.toString(index) + ". " + recipe.getName());
            }
            index++;
        }
    }
}
