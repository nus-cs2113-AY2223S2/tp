package seedu.mealcompanion.recipe;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.ingredient.IngredientList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeTest {
    @Test
    void testToString_correctStringFormat() {
        Recipe recipe = new Recipe("Dummy Recipe", false, 0, 0, 0,
            new IngredientList(), new InstructionList());
        String expectedString =
                "Recipe for Dummy Recipe" + System.lineSeparator() + "Calories: 0" + System.lineSeparator() +
                        "Ingredients:" + System.lineSeparator() + "Instructions:" + System.lineSeparator();
        assertEquals(recipe.toString(), expectedString);
    }
}
