package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

//@@author jingyaaa

/**
 * Represents the "recipe find" command
 */

public class RecipeFindCommand extends ExecutableCommand {

    String findTerm;

    public RecipeFindCommand(String findTerm) {
        this.findTerm = findTerm;
    }

    /**
     * Check if recipe name in a recipe list contains the search term
     *
     * @param mealCompanionSession the MealCompanionSession containing the recipe list
     * @return true if there are recipe names that contain search term, else false
     */
    private boolean haveRecipes(MealCompanionSession mealCompanionSession) {
        RecipeList recipeList = mealCompanionSession.getRecipes();
        String matchTerm = findTerm.toLowerCase();
        int index = 1;
        boolean foundRecipe = false;
        for (Recipe recipe : recipeList.getRecipes()) {
            String recipeName = recipe.getName().toLowerCase();
            if (recipeName.contains(matchTerm)) {
                foundRecipe = true;
                mealCompanionSession.getUi().printMessage(index + ". " + recipe.getName());
            }
            index++;
        }
        return foundRecipe;
    }

    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            boolean foundRecipe = haveRecipes(mealCompanionSession);
            mealCompanionSession.getUi().printMessage("These are the recipes found:");
            if (!foundRecipe) {
                mealCompanionSession.getUi().printMessage("There are no recipes found!");
            }
        } catch (NullPointerException e) {
            mealCompanionSession.getUi().printMessage("Please input a valid recipe search term!");
        }
    }
}
