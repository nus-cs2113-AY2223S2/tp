package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;

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
}
