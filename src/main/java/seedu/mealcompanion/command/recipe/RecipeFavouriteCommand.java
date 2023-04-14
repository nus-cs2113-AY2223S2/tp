package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.recipe.Recipe;

//@@author Jjzeng123
public class RecipeFavouriteCommand extends RecipeCommand {
    Recipe recipe;

    public RecipeFavouriteCommand(Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Check if a string is a number
     *
     * @param argument the string to check for
     * @return true if string is number, false otherwise
     */
    private boolean isNum(String argument) {
        return argument.matches("[0-9]+");  //match a number with optional '-' and decimal.
    }

    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        recipe.setFavourite();
        mealCompanionSession.getUi().printMessage("Success! Added this recipe to favourites: ");
        mealCompanionSession.getUi().printMessage(recipe.getName());
    }
}
