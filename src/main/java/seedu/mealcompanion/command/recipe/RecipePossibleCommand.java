package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.IngredientMetadata;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;
import seedu.mealcompanion.ui.MealCompanionUI;

import java.util.List;

//@@author ngyida

/**
 * Represents the "recipe possible" command.
 */
public class RecipePossibleCommand extends RecipeCommand {

    /**
     * Get the list of possibles recipes that can be made using the available ingredients in the session.
     *
     * @param fridgeIngredients the list of available ingredients
     * @param recipes           the list of all recipes
     * @param allergens         the list of allergens
     * @return a list of possible recipes
     */
    private RecipeList getPossibleRecipes(IngredientList fridgeIngredients, RecipeList recipes,
                                          List<IngredientMetadata> allergens) {
        RecipeList possibleRecipes = new RecipeList();
        for (Recipe recipe : recipes.getRecipes()) {
            if (canMakeRecipe(recipe, fridgeIngredients) && !hasAllergen(recipe, allergens)) {
                possibleRecipes.add(recipe);
            }
        }
        return possibleRecipes;
    }

    /**
     * Print the list of possible recipes with their corresponding recipe index number
     *
     * @param possibleRecipes the list of possible recipes to print
     * @param allRecipes      the list of all recipes with their corresponding index number
     * @param ui              UI for printing
     * @throws MealCompanionException if index of recipe is not found
     */
    private void printPossibleRecipes(RecipeList possibleRecipes, RecipeList allRecipes,
                                      MealCompanionUI ui) throws MealCompanionException {
        if (possibleRecipes.isEmpty()) {
            ui.printMessage("You cannot make any recipe :c");
            return;
        }
        ui.printMessage("Here are the recipe(s) that you can make:");
        for (Recipe recipe : possibleRecipes.getRecipes()) {
            String recipeName = recipe.getName();
            int index = allRecipes.findIndex(recipeName);
            ui.printMessage(Integer.toString(index + 1) + ". " + recipeName);
        }
    }

    /**
     * List all recipes that can be made using ingredients that are available.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of recipes and ingredients
     */
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            IngredientList fridgeIngredients = mealCompanionSession.getIngredients();
            RecipeList recipes = mealCompanionSession.getRecipes();
            List<IngredientMetadata> allergens = mealCompanionSession.getAllergens();
            RecipeList possibleRecipes = getPossibleRecipes(fridgeIngredients, recipes, allergens);
            printPossibleRecipes(possibleRecipes, recipes, mealCompanionSession.getUi());
        } catch (MealCompanionException e) {
            mealCompanionSession.getUi().printMessage(e.getMessage());
        }
    }
}
