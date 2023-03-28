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

    /**
     * Find the index of a recipe in dukeSession list of recipes.
     * @param name the name of the recipe to look for
     * @param dukeSession the dukeSession containing the list of recipes to look in
     * @return index of recipe if recipe is found, else return -1
     */
    private int findIndex(String name, MealCompanionSession dukeSession) throws MealCompanionException {
        RecipeList recipes = dukeSession.getRecipes();
        int index = 1;
        for (Recipe recipe : recipes.getRecipes()) {
            if (recipe.getName().equals(name)) {
                return index;
            }
            index++;
        }
        throw new MealCompanionException("Recipe not found!");
    }


    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            if (this.argument == null) {
                throw new MealCompanionException("Missing argument!");
            }

            int index;
            if (!isNum(this.argument)) {
                index = findIndex(this.argument, mealCompanionSession) - 1;
            } else {
                index = Integer.parseInt(this.argument) - 1;
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

