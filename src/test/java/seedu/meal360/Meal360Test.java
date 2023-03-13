package seedu.meal360;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String input;
        String[] command;
        RecipeList recipeListToPrint;

        HashMap<String, Integer> testIngredients1 = new HashMap<>();
        testIngredients1.put("test ingredient1-1", 10);
        testIngredients1.put("test ingredient1-2", 20);
        testIngredients1.put("test ingredient1-3", 30);
        Recipe testRecipe1 = new Recipe("test recipe1", testIngredients1);
        recipes.addRecipe(testRecipe1);

        HashMap<String, Integer> testIngredients2 = new HashMap<>();
        testIngredients2.put("test ingredient2-1", 10);
        testIngredients2.put("test ingredient2-2", 20);
        Recipe testRecipe2 = new Recipe("test recipe2", testIngredients2);
        recipes.addRecipe(testRecipe2);

        input = "list";
        command = input.split(" ",2);
        recipeListToPrint = parser.parseListRecipe(command, recipes);
        assertEquals(2, recipeListToPrint.size());
        int order = 1;
        for (Recipe recipe : recipes) {
            switch(order) {
            case 1: assertEquals(3, recipe.getNumOfIngredients());
                break;
            case 2: assertEquals(2, recipe.getNumOfIngredients());
                break;
            default: assertTrue(false);
            }
            order++;
        }

        input = "list test ingredient2-1";
        command = input.split(" ",2);
        recipeListToPrint = parser.parseListRecipe(command, recipes);
        assertEquals(1, recipeListToPrint.size());
        assertEquals(2, recipeListToPrint.get(0).getNumOfIngredients());
    }
}
