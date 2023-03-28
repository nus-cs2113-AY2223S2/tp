package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

//@@author ngyida
/**
 * Represents the "recipe detail" command.
 */
public class RecipeDetailCommand extends ExecutableCommand {

    String argument;
    public RecipeDetailCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Check if a string is a number
     * @param argument the string to check for
     * @return true if string is number, false otherwise
     */
    private boolean isNum(String argument) {
        return argument.matches("[0-9]+");  //match a number with optional '-' and decimal.
    }

    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            if (this.argument == null) {
                throw new MealCompanionException("Missing argument!");
            }

            int index;

            if (!isNum(this.argument)) {
                index = mealCompanionSession.getRecipes().findIndex(this.argument);
            } else {
                index = Integer.parseInt(this.argument) - 1; // 1-based user index decremented for 0-based index
            }
            
            if (index < 0 || index >= mealCompanionSession.getRecipes().size()) {
                throw new MealCompanionException("Recipe index out of range!");
            }
            assert index >= 0 && index < mealCompanionSession.getRecipes().size();

            Recipe recipe = mealCompanionSession.getRecipes().getRecipe(index);
            mealCompanionSession.getUi().printMessage(recipe.toString());
        } catch (MealCompanionException e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}

