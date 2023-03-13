package seedu.meal360;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

class Meal360Test {

    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testViewRecipe() {
        RecipeList recipes = new RecipeList();
        HashMap<String, Integer> testIngredients = new HashMap<>();
        testIngredients.put("test ingredient", 100);
        Recipe testR = new Recipe("test recipe name", testIngredients);
        recipes.addRecipe(testR);

        Parser parser = new Parser();
        Recipe recipe = parser.parseViewRecipe(new String[]{"view", "1"}, recipes);
        assertTrue(recipe.getName().equals("test recipe name"));
        assertTrue(recipe.getIngredients().get("test ingredient") == 100);
    }

    @Test
    public void testListRecipe() {
        RecipeList recipes = new RecipeList();
        Parser parser = new Parser();

        HashMap<String, Integer> testIngredients = new HashMap<>();
        testIngredients.put("test ingredient1", 10);
        testIngredients.put("test ingredient2", 20);
        testIngredients.put("test ingredient3", 30);
        Recipe testR = new Recipe("test recipe1", testIngredients);
        recipes.addRecipe(testR);

        RecipeList recipeListToPrint = parser.parseListRecipe(recipes);
        assertTrue(recipeListToPrint.size() == 1);
        int order = 1;
        for (Recipe recipe : recipes) {
            if (order == 1) {
                assertTrue(recipe.getNumOfIngredients() == 3);
            }
            order++;
        }
    }
}
