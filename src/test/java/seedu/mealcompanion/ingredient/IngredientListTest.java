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

    //@@author ngyida
    @Test
    void testToString_correctStringFormat() {
        IngredientList ingredients = new IngredientList();
        try {
            ingredients.add(new Ingredient("water", 100));
            ingredients.add(new Ingredient("chicken", 2));
        } catch (MealCompanionException e) {
            System.out.println("Failed to create ingredients");
            return;
        }
        String expectedString =
                "1. 100 ml water" + System.lineSeparator() +  "2. 2 pieces chicken" + System.lineSeparator();
        assertEquals(ingredients.toString(), expectedString);
    }
}
