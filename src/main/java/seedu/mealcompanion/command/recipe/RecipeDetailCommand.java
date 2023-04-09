package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.recipe.Recipe;

//@@author ngyida

/**
 * Represents the "recipe detail" command.
 */
public class RecipeDetailCommand extends RecipeCommand {
    Recipe recipe;

    public RecipeDetailCommand(Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Get the recipe specified by the index in the argument and print out its details.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of recipes, ingredients, and allergens
     */
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        mealCompanionSession.getUi().printMessage(recipe.toString());
    }
}
