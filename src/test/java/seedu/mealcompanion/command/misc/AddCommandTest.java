package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ingredients.AddCommand;


import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author TJW0911
class AddCommandTest {
    @Test
    public void addNewIngredientAndAddToExistingIngredient() {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        new AddCommand("apple", "2").execute(mealCompanionSession);
        assertEquals(2, mealCompanionSession.getIngredients().get(0).getQuantity());
        new AddCommand("apple", "3").execute(mealCompanionSession);
        assertEquals(5, mealCompanionSession.getIngredients().get(0).getQuantity());
    }

    @Test
    public void addNewAmountExceedsMaxIngredients() {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        new AddCommand("apple", "9999").execute(mealCompanionSession);
        assertEquals(9999, mealCompanionSession.getIngredients().get(0).getQuantity());
        new AddCommand("apple", "2").execute(mealCompanionSession);
        assertEquals(9999, mealCompanionSession.getIngredients().get(0).getQuantity());
    }

    @Test
    public void addInputTooSmallOrTooBig() {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        new AddCommand("egg", "101").execute(mealCompanionSession);
        assertEquals(1, mealCompanionSession.getIngredients().size());
        new AddCommand("apple", "10001").execute(mealCompanionSession);
        new AddCommand("apple", "-1").execute(mealCompanionSession);
        assertEquals(1, mealCompanionSession.getIngredients().size());
    }
}
