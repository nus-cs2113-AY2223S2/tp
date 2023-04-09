package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.Recipe;

import java.util.ArrayList;

//@@author jingyaaa

/**
 * Represents the "recipe need" possible
 */
public class RecipeNeedCommand extends RecipeCommand {
    Recipe recipe;

    public RecipeNeedCommand(Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Check if user possess sufficient quantity of ingredient needed.
     *
     * @param targetIngredient   the ingredient to check for
     * @param ingredientInFridge ingredient list containing user's ingredients to check in
     * @return additional quantity needed
     */
    private int additionalQuantityNeeded(Ingredient targetIngredient, IngredientList ingredientInFridge) {
        try {
            int quantityPossessed;
            quantityPossessed = ingredientInFridge.get(targetIngredient.getMetadata().getName()).getQuantity();
            int quantityNeeded = targetIngredient.getQuantity();
            if (quantityNeeded > quantityPossessed) {
                return quantityNeeded - quantityPossessed;
            }
            return 0;
        } catch (MealCompanionException e) {
            return targetIngredient.getQuantity();
        }
    }

    /**
     * List all ingredients that are insufficient to make a specific recipe
     *
     * @param mealCompanionSession the session containing list of recipes and user's ingredient list
     */
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        IngredientList ingredientsInFridge = mealCompanionSession.getIngredients();
        IngredientList ingredients = recipe.getIngredients();
        ArrayList<Ingredient> ingredientsInRecipe = ingredients.getIngredients();
        boolean isMissing = false;
        int index = 1;
        for (Ingredient ingredient : ingredientsInRecipe) {
            int quantityNeeded = additionalQuantityNeeded(ingredient, ingredientsInFridge);
            if (quantityNeeded > 0 && index == 1) {
                mealCompanionSession.getUi().printMessage("These are the ingredient(s) you are missing:");
            }
            if (quantityNeeded > 0) {
                mealCompanionSession.getUi().printMessage(index +
                        ". " + ingredient.getMetadata().getName() + " (quantity: " +
                        quantityNeeded + ")");
                isMissing = true;
                index++;
            }
        }
        if (!isMissing) {
            mealCompanionSession.getUi().printMessage("You have all the ingredients to make this recipe!");
        }
    }
}
