package seedu.mealcompanion.recipe;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class RecipeListTest {
    @Test
    void createRecipeList() {
        new RecipeList();
    }

    @Test
    void testFindIndex() throws MealCompanionException {
        RecipeList recipes = new RecipeList();
        // make recipe for cup of water
        IngredientList waterIngredients = new IngredientList();
        waterIngredients.add(new Ingredient("water", 100));
        Recipe cupOfWater = new Recipe("Cup of Water", false, 0, 0, 0,
                waterIngredients, new InstructionList());
        // make recipe for raw chicken
        IngredientList chickenIngredients = new IngredientList();
        chickenIngredients.add(new Ingredient("chicken", 5));
        Recipe chicken = new Recipe("Raw Chicken", false, 0, 0, 0,
                chickenIngredients, new InstructionList());
        // add tea and water recipe to recipe list
        recipes.add(cupOfWater);
        recipes.add(chicken);
        assertEquals(0, recipes.findIndex("Cup of Water"));
        assertEquals(1, recipes.findIndex("Raw Chicken"));
        try {
            recipes.findIndex("Burger");
            fail();
        } catch (MealCompanionException e) {
            // test pass and do nothing
        }
    }
}
