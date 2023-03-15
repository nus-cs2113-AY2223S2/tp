package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

/**
 * Represents the "recipe all" command.
 */
public class RecipeAllCommand extends ExecutableCommand {
    /**
     * List all recipes.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of recipes
     */
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        RecipeList recipes = mealCompanionSession.getRecipes();
        int index = 1;
        mealCompanionSession.getUi().printMessage("Here is the full list of recipes:");
        for (Recipe recipe : recipes.getRecipes()) {
            mealCompanionSession.getUi().printMessage(Integer.toString(index) + ". " + recipe.getName());
            index++;
        }
    }
}
