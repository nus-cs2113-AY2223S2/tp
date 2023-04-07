package seedu.mealcompanion.command.recipe;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;
import java.util.Random;

//@@author ngyida
public class RecipeRandomCommand extends RecipeCommand {

    /**
     * Get a random index of RecipeList
     *
     * @param recipes the RecipeList to get the random index from
     * @return random index of RecipeList
     * @throws MealCompanionException if RecipeList is empty and has no valid index
     */
    private int getRandomIndex(RecipeList recipes) throws MealCompanionException {
        // throw exception if there is no existing recipe
        if (recipes.size() == 0) {
            throw new MealCompanionException("No recipe is available!");
        }

        Random rand = new Random();
        int index = rand.nextInt(recipes.size());
        assert(index >= 0 && index < recipes.size()) : "index is out of range!";
        return index;
    }

    /**
     * Get a random index and print out the recipe specified by the index.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     */
    @Override
    public void execute(MealCompanionSession mealCompanionSession){
        try {
            RecipeList recipes = mealCompanionSession.getRecipes();
            int index = getRandomIndex(recipes);
            Recipe recipe = recipes.getRecipe(index);
            mealCompanionSession.getUi().printMessage("A random recipe is chosen:");
            if (hasAllergen(recipe, mealCompanionSession.getAllergens())) {
                mealCompanionSession.getUi().printMessage("IMPORTANT: You are allergic to the ingredient(s) in this " +
                        "recipe. Proceed with care or run command again to get a different random recipe.");
            }
            mealCompanionSession.getUi().printMessage(recipe.toString());
        } catch (MealCompanionException e) {
            mealCompanionSession.getUi().printMessage(e.getMessage());
        }
    }
}
