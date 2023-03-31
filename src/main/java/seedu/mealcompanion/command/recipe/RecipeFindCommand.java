package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

//@@author jingyaaa

public class RecipeFindCommand extends ExecutableCommand {

    String findTerm;

    public RecipeFindCommand(String findTerm) {
        this.findTerm = findTerm;
    }

    private boolean findRecipes(MealCompanionSession mealCompanionSession) {
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
            boolean foundRecipe = findRecipes(mealCompanionSession);
            mealCompanionSession.getUi().printMessage("These are the recipes found:");
            if (!foundRecipe) {
                mealCompanionSession.getUi().printMessage("There are no recipes found!");
            }
        } catch (NullPointerException e) {
            mealCompanionSession.getUi().printMessage("Please input a valid recipe search term!");
        }
    }
}
