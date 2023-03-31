package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.recipe.Recipe;

//@@author Jjzeng123
public class RecipeFavouriteCommand extends RecipeCommand {
    String argument;
    public RecipeFavouriteCommand(String argument)  {
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
            recipe.setFavourite();
        } catch (MealCompanionException e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
