package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.IngredientMetadata;
import seedu.mealcompanion.recipe.Instruction;
import seedu.mealcompanion.recipe.InstructionList;
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
     * Executes the recipe random command
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
            mealCompanionSession.getUi().printMessage(recipe.toString());
        } catch (MealCompanionException e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
