package seedu.duke.command.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;

/**
 * Represents the "recipe possible" command.
 */
public class RecipePossibleCommand extends ExecutableCommand {

    /**
     * Check if an ingredientList has a sufficient amount of an ingredient.
     *
     * @param ingredient     the ingredient to look for
     * @param ingredientList the list of ingredients to check in
     * @return true if the list of ingredients have sufficient quantity of that ingredient, false otherwise
     */
    private boolean hasEnoughIngredient(Ingredient ingredient, IngredientList ingredientList) {
        String recipeIngredientName = ingredient.getMetadata().getName();
        double recipeIngredientQty = ingredient.getQuantity();
        for (Ingredient fridgeIngredient : ingredientList.getIngredients()) {
            if (fridgeIngredient.getMetadata().getName().equals(recipeIngredientName)
                    && fridgeIngredient.getQuantity() >= recipeIngredientQty) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a recipe can be made using a list of ingredients given.
     *
     * @param recipe            the recipe to be made
     * @param fridgeIngredients the list of ingredients used to make the recipe
     * @return true if the recipe can be made using the list of ingredients, false otherwise
     */
    private boolean canMakeRecipe(Recipe recipe, IngredientList fridgeIngredients) {
        IngredientList recipeIngredients = recipe.getIngredients();
        for (Ingredient recipeIngredient : recipeIngredients.getIngredients()) {
            if (!hasEnoughIngredient(recipeIngredient, fridgeIngredients)) {
                return false;
            }
        }
        return true;
    }

    /**
     * List all recipes that can be made using ingredients that are available.
     *
     * @param dukeSession the DukeSession containing the list of recipes and ingredients
     */
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
