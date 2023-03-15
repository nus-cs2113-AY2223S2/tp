package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {
    @Test
    public void itemIsInListAndItemNotInList() throws MealCompanionException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        Ingredient ingredient = new Ingredient("apple", 2.0);
        mealCompanionSession.getIngredients().add(ingredient);

        AddCommand command = new AddCommand("apple", "2.0");

        assertEquals(0,command.findIndex(mealCompanionSession, "apple"));
        assertEquals(-1,command.findIndex(mealCompanionSession, "orange"));
    }

    @Test
    public void addNewIngredientAndAddToExistingIngredient() {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        new AddCommand("apple", "2").execute(mealCompanionSession);
        assertEquals(2, mealCompanionSession.getIngredients().get(0).getQuantity());
        new AddCommand("apple", "3").execute(mealCompanionSession);
        assertEquals(5, mealCompanionSession.getIngredients().get(0).getQuantity());
    }
}
