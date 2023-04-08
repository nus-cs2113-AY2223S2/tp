package seedu.meal360;

import java.util.ArrayList;
import java.util.HashMap;
import seedu.meal360.exceptions.IngredientNotFoundException;

public class WeeklyPlan extends HashMap<String, Integer> {

    /**
     * Adds a recipe to the weekly plan.
     *
     * @author jaredoong
     * @param recipeMap the recipe to be added.
     * @throws IllegalArgumentException if the total quantity of the recipe exceeds 1000.
     */
    public void addPlans(WeeklyPlan recipeMap) throws IllegalArgumentException {
        final boolean[] validNumber = {true};
        recipeMap.forEach((recipe, count) -> {
            if (validNumber[0] && this.containsKey(recipe)) {
                int newCount = this.get(recipe) + count;
                validNumber[0] = (newCount > 1000) ? false : true;
            }
        });

        if (validNumber[0]) {
            recipeMap.forEach((recipe, count) -> {
                if (this.containsKey(recipe)) {
                    this.put(recipe, this.get(recipe) + count);
                } else {
                    this.put(recipe, count);
                }
            });
        } else {
            throw new IllegalArgumentException("Total quantity cannot exceed 1000!");
        }
    }

    /**
     * Deletes a recipe from the weekly plan.
     *
     * @author jaredoong
     * @param recipeMap the recipe to be deleted.
     * @throws IllegalArgumentException if the recipe does not exist in the weekly plan.
     */
    public void deletePlans(WeeklyPlan recipeMap) throws IllegalArgumentException {
        recipeMap.forEach((recipe, count) -> {
            if (this.containsKey(recipe)) {
                int currentCount = this.get(recipe);
                int newCount = currentCount - count;
                this.remove(recipe);
                if (newCount > 0) {
                    this.put(recipe, newCount);
                }
            } else {
                throw new IllegalArgumentException("Recipe does not exist in weekly plan!");
            }
        });
    }

    public void clearPlan() {
        this.clear();
    }

    /**
     * Checks if the weekly plan contains any invalid recipes.
     *
     * @author jaredoong
     * @param recipeList the list of recipes.
     * @return true if the weekly plan contains no invalid recipes, false otherwise.
     */
    public boolean checkValidity(RecipeList recipeList) {
        ArrayList<String> toRemove = new ArrayList<>();
        this.forEach((recipe, count) -> {
            if (recipeList.findByName(recipe) == null) {
                toRemove.add(recipe);
            }
        });

        if (toRemove.size() == 0) {
            return true;
        }
        toRemove.forEach(recipe -> this.remove(recipe));
        return false;
    }

    /**
     * Marks a recipe in the weekly plan as done.
     *
     * @author jaredoong
     * @param recipeName      the name of the recipe to be marked as done.
     * @param recipeList      the list of recipes.
     * @param userIngredients the list of ingredients.
     * @throws IngredientNotFoundException if the ingredient does not exist in the list of ingredients.
     */
    public void markDone(String recipeName, RecipeList recipeList, IngredientList userIngredients)
            throws IngredientNotFoundException {
        HashMap<String, Integer> ingredients = recipeList.findByName(recipeName).getIngredients();

        for (String ingredient : ingredients.keySet()) {
            Ingredient ingredientToDelete = new Ingredient(ingredient, ingredients.get(ingredient),
                    "01/01/2023");
            userIngredients.deleteIngredient(ingredientToDelete);
        }

        if (this.get(recipeName) == 1) {
            this.remove(recipeName);
        } else {
            this.put(recipeName, this.get(recipeName) - 1);
        }
    }
}
