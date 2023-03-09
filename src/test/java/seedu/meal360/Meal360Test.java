package seedu.meal360;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class Meal360Test {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testDeleteRecipe() {
        RecipeList recipeList = new RecipeList();
        // add new recipe to list
        HashMap<String, Integer> ingredients = new HashMap<>();
        Recipe newRecipe = new Recipe("", ingredients);
        recipeList.addRecipe(newRecipe);
        try {
            int oldRecipeListSize = recipeList.size();
            // delete recipe
            recipeList.deleteRecipe(recipeList.indexOf(newRecipe));
            int newRecipeListSize = recipeList.size();
            // check if recipe list size is 1 less than before
            assertEquals(oldRecipeListSize - 1, newRecipeListSize);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Recipe was not deleted", e.getMessage());
        }
    }
}
