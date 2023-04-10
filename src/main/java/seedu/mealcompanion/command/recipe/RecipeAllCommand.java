package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.recipe.RecipeList;

//@@author ngyida

/**
 * Represents the "recipe all" command.
 */
public class RecipeAllCommand extends RecipeCommand {

    /**
     * Get a formatted string of the list of recipe names.
     *
     * @param recipes the list of recipes to be formatted into a list
     * @return a formatted list of the recipe names if there is at least 1 recipe. Else, return a message indicating
     *     no recipe available.
     */
    public String getAllRecipeNameList(RecipeList recipes) {
        String recipeNameList = recipes.getRecipeNameList();
        if (recipeNameList.equals("")) {
            return "There is no recipe available.";
        }
        return "Here is the full list of recipes:" + System.lineSeparator() + recipeNameList;
    }

    /**
     * List all recipes.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of recipes
     */
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        RecipeList recipes = mealCompanionSession.getRecipes();
        String recipeNameList = getAllRecipeNameList(recipes);
        mealCompanionSession.getUi().printMessage(recipeNameList);
    }
}
