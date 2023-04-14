package seedu.mealcompanion.recipe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecipeListTest {
    private static RecipeList recipes = new RecipeList();

    @BeforeAll
    static void createRecipes() {
        try {
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
            // add water and chicken recipe to recipe list
            recipes.add(cupOfWater);
            recipes.add(chicken);
        } catch (MealCompanionException e) {
            System.out.println("Failed to initialise recipes list");
        }
    }

    @Test
    void testFindIndex_recipeExist() throws MealCompanionException {
        assertEquals(0, recipes.findIndex("Cup of Water"));
        assertEquals(1, recipes.findIndex("Raw Chicken"));
    }

    @Test
    void testFindIndex_recipeNameInOtherCases() throws MealCompanionException {
        assertEquals(0, recipes.findIndex("cUp of water"));
        assertEquals(1, recipes.findIndex("raw chicken"));
    }

    @Test
    void testFindIndex_recipeMissing() {
        assertThrows(MealCompanionException.class, ()-> recipes.findIndex("Burger"));
    }

    @Test
    void testFindIndex_invalidRecipeName() {
        assertThrows(MealCompanionException.class, ()-> recipes.findIndex("1"));
        assertThrows(MealCompanionException.class, ()-> recipes.findIndex("cup of water "));
    }
}
