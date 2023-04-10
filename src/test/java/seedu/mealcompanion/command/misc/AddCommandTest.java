package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ingredients.AddCommand;
import seedu.mealcompanion.exception.CommandRunException;
import seedu.mealcompanion.exception.InvalidIngredientNameException;
import seedu.mealcompanion.recipe.IngredientDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author TJW0911
class AddCommandTest {
    @Test
    public void addNewIngredientAndAddToExistingIngredient()
            throws InvalidIngredientNameException, CommandRunException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        new AddCommand(IngredientDatabase.getDbInstance().getKnownIngredient("apple"),
                2).execute(mealCompanionSession);
        assertEquals(2, mealCompanionSession.getIngredients().get(0).getQuantity());
        new AddCommand(IngredientDatabase.getDbInstance().getKnownIngredient("apple"),
                3).execute(mealCompanionSession);
        assertEquals(5, mealCompanionSession.getIngredients().get(0).getQuantity());
    }

    @Test
    public void addNewAmountExceedsMaxIngredients() throws InvalidIngredientNameException, CommandRunException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        new AddCommand(IngredientDatabase.getDbInstance().getKnownIngredient("apple"), 9999)
                .execute(mealCompanionSession);
        assertEquals(9999, mealCompanionSession.getIngredients().get(0).getQuantity());

        assertThrows(CommandRunException.class, () ->
                new AddCommand(IngredientDatabase.getDbInstance().getKnownIngredient("apple"), 2)
                        .execute(mealCompanionSession));
        assertEquals(9999, mealCompanionSession.getIngredients().get(0).getQuantity());
    }

    @Test
    public void addInputTooSmallOrTooBig() throws InvalidIngredientNameException, CommandRunException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        new AddCommand(IngredientDatabase.getDbInstance().getKnownIngredient("egg"), 101).execute(mealCompanionSession);
        assertEquals(1, mealCompanionSession.getIngredients().size());
    }
}
