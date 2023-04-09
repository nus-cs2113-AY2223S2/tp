package seedu.mealcompanion.ingredient;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.exception.MealCompanionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientListTest {
    @Test
    public void findIndexTest() throws MealCompanionException {
        IngredientList ingredients = new IngredientList();
        Ingredient ingredient1 = new Ingredient("apple", 2);
        Ingredient ingredient2 = new Ingredient("egg", 1);

        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        assertEquals(0, ingredients.findIndex("apple"));
        assertEquals(1, ingredients.findIndex("egg"));
        assertEquals(-1, ingredients.findIndex("bread"));
    }
}
