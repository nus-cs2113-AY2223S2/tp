package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

//@@author ngyida

/**
 * Represents the "recipe all" command.
 */
public class RecipeAllCommand extends RecipeCommand {
    /**
     * List all recipes.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of recipes
     */
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        RecipeList recipes = mealCompanionSession.getRecipes();
        if (recipes.isEmpty()) {
            mealCompanionSession.getUi().printMessage("There is no recipe available.");
            return;
        }
        int index = 1;
        mealCompanionSession.getUi().printMessage("Here is the full list of recipes:");
        for (Recipe recipe : recipes.getRecipes()) {
            mealCompanionSession.getUi().printMessage(recipe.getFavStatus() + Integer.toString(index) + ". "
                    + recipe.getName());
            index++;
        }
    }
}
