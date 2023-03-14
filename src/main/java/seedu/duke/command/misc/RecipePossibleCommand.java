package seedu.duke.command.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;

public class RecipePossibleCommand extends ExecutableCommand {

    private boolean hasEnoughIngredient(Ingredient recipeIngredient, IngredientList fridgeIngredients) {
        String recipeIngredientName = recipeIngredient.getMetadata().getName();
        double recipeIngredientQty = recipeIngredient.getQuantity();
        for (Ingredient fridgeIngredient : fridgeIngredients.getIngredients()) {
            if (fridgeIngredient.getMetadata().getName().equals(recipeIngredientName) &&
                    fridgeIngredient.getQuantity() >= recipeIngredientQty) {
                return true;
            }
        }
        return false;
    }
    private boolean canMakeRecipe(Recipe recipe, IngredientList fridgeIngredients) {
        IngredientList recipeIngredients = recipe.getIngredients();
        for (Ingredient recipeIngredient : recipeIngredients.getIngredients()) {
            if (!hasEnoughIngredient(recipeIngredient, fridgeIngredients)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void execute(DukeSession dukeSession) {
        IngredientList fridgeIngredients = dukeSession.getIngredients();
        RecipeList recipes = dukeSession.getRecipes();
        int index = 1;
        dukeSession.getUi().printMessage("Here are the recipe(s) that you can make:");
        for (Recipe recipe : recipes.getRecipes()) {
            if (canMakeRecipe(recipe, fridgeIngredients)) {
                dukeSession.getUi().printMessage(Integer.toString(index) + ". " + recipe.getName());
                index++;
            }
        }
    }
}
