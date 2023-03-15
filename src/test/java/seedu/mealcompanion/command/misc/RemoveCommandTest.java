package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class RemoveCommandTest {
    @Test
    public void removeSameNumberAsQuantity() throws MealCompanionException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        Ingredient ingredient = new Ingredient("apple", 2.0);
        mealCompanionSession.getIngredients().add(ingredient);
        assertEquals(1, mealCompanionSession.getIngredients().size());
        new RemoveCommand("apple", "2").execute(mealCompanionSession);
        assertEquals(0, mealCompanionSession.getIngredients().size());
    }

    @Test
    public void removeLesserThanFridgeQuantity() throws MealCompanionException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        Ingredient ingredient = new Ingredient("apple", 9.0);
        mealCompanionSession.getIngredients().add(ingredient);
        assertEquals(9, mealCompanionSession.getIngredients().get(0).getQuantity());
        new RemoveCommand("apple", "2").execute(mealCompanionSession);
        assertEquals(7, mealCompanionSession.getIngredients().get(0).getQuantity());
        new RemoveCommand("apple", "4").execute(mealCompanionSession);
        assertEquals(3, mealCompanionSession.getIngredients().get(0).getQuantity());
    }
}
