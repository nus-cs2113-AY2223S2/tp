package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ingredients.RemoveCommand;
import seedu.mealcompanion.exception.CommandRunException;
import seedu.mealcompanion.exception.InvalidIngredientNameException;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.recipe.IngredientDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author TJW0911
public class RemoveCommandTest {
    @Test
    public void removeSameNumberAsQuantity()
            throws MealCompanionException, InvalidIngredientNameException, CommandRunException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        Ingredient ingredient = new Ingredient("apple", 2);
        mealCompanionSession.getIngredients().add(ingredient);
        assertEquals(1, mealCompanionSession.getIngredients().size());
        new RemoveCommand(IngredientDatabase.getDbInstance().getKnownIngredient("apple"), 2)
                .execute(mealCompanionSession);
        assertEquals(0, mealCompanionSession.getIngredients().size());
    }

    @Test
    public void removeLesserThanFridgeQuantity()
            throws MealCompanionException, InvalidIngredientNameException, CommandRunException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        Ingredient ingredient = new Ingredient("apple", 9);
        mealCompanionSession.getIngredients().add(ingredient);
        assertEquals(9, mealCompanionSession.getIngredients().get(0).getQuantity());
        new RemoveCommand(IngredientDatabase.getDbInstance().getKnownIngredient("apple"), 2)
                .execute(mealCompanionSession);
        assertEquals(7, mealCompanionSession.getIngredients().get(0).getQuantity());
        new RemoveCommand(IngredientDatabase.getDbInstance().getKnownIngredient("apple"), 4)
                .execute(mealCompanionSession);
        assertEquals(3, mealCompanionSession.getIngredients().get(0).getQuantity());
    }
}
