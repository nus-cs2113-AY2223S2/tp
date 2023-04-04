package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.IngredientMetadata;
import seedu.mealcompanion.recipe.Recipe;

import java.util.List;

/**
 * Represents the "recipe possible" command.
 */
//@@author ngyida
public abstract class RecipeCommand extends ExecutableCommand {
    /**
     * Check if an ingredientList has a sufficient amount of an ingredient.
     *
     * @param recipeIngredient  the ingredient to look for
     * @param fridgeIngredients the list of ingredients to check in
     * @return true if the list of ingredients have sufficient quantity of that ingredient, false otherwise
     */
    public boolean hasEnoughIngredient(Ingredient recipeIngredient, IngredientList fridgeIngredients) {
        String recipeIngredientName = recipeIngredient.getMetadata().getName();
        double recipeIngredientQty = recipeIngredient.getQuantity();
        for (Ingredient fridgeIngredient : fridgeIngredients.getIngredients()) {
            if (fridgeIngredient.getMetadata().getName().equals(recipeIngredientName)
                    && fridgeIngredient.getQuantity() >= recipeIngredientQty) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a recipe contains any allergen.
     * @param recipe the recipe to check for allergen
     * @param allergens the allergens to be checked against
     * @return false if recipe has no allergen. Else, true.
     */
    public boolean hasAllergen(Recipe recipe, List<IngredientMetadata> allergens) {
        IngredientList recipeIngredients = recipe.getIngredients();
        for (Ingredient recipeIngredient : recipeIngredients.getIngredients()) {
            if (allergens.contains(recipeIngredient.getMetadata())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a recipe can be made using a list of ingredients given and exclude recipes that contain any allergens.
     *
     * @param recipe the recipe to be made
     * @param fridgeIngredients the list of ingredients used to make the recipe
     * @return true if the recipe can be made using the list of ingredients without any allergens, false otherwise
     */
    public boolean canMakeRecipe(Recipe recipe, IngredientList fridgeIngredients) {
        IngredientList recipeIngredients = recipe.getIngredients();
        for (Ingredient recipeIngredient : recipeIngredients.getIngredients()) {
            if (!hasEnoughIngredient(recipeIngredient, fridgeIngredients)) {
                return false;
            }
        }
        return true;
    }
}
