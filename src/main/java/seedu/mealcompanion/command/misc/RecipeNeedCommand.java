package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.Recipe;

import java.util.ArrayList;

//@@author jingyaaa

public class RecipeNeedCommand extends ExecutableCommand{

    String recipeName;

    public RecipeNeedCommand(String recipeName) {
        this.recipeName = recipeName;
    }

    private double additionalQuantityNeeded(Ingredient targetIngredient, IngredientList ingredientInFridge) {
        try {
            double quantityPossessed;
            quantityPossessed = ingredientInFridge.get(targetIngredient.getMetadata().getName()).getQuantity();
            double quantityNeeded = targetIngredient.getQuantity();
            if (quantityNeeded > quantityPossessed) {
                return quantityNeeded - quantityPossessed;
            }
            return 0;
        } catch (MealCompanionException e) {
            return targetIngredient.getQuantity();
        }
    }

    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            Recipe targetRecipe = mealCompanionSession.getRecipes().getRecipe(recipeName);
            IngredientList ingredientsInFridge = mealCompanionSession.getIngredients();
            IngredientList ingredients = targetRecipe.getIngredients();
            ArrayList<Ingredient> ingredientsInRecipe = ingredients.getIngredients();
            boolean isMissing = false;
            int index = 1;
            mealCompanionSession.getUi().printMessage("These are the ingredients you need:");
            for (Ingredient ingredient : ingredientsInRecipe) {
                double quantityNeeded = additionalQuantityNeeded(ingredient, ingredientsInFridge);
                if (quantityNeeded > 0) {
                    mealCompanionSession.getUi().printMessage(Integer.toString(index) +
                            ". " + ingredient.getMetadata().getName() + " (quantity: " +
                            Double.toString(quantityNeeded) + ")");
                    isMissing = true;
                    index++;
                }
            }
            if (!isMissing) {
                mealCompanionSession.getUi().printMessage("You have all the ingredients to make this recipe!");
            }
        } catch (MealCompanionException e) {
            mealCompanionSession.getUi().printMessage("Please input a valid recipe name!");
        }
    }
}
