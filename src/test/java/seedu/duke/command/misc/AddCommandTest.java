package seedu.duke.command.misc;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeSession;
import seedu.duke.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.command.misc.AddCommand.findIndex;

class AddCommandTest {
    @Test
    public void itemIsInListAndItemNotInList() {
        DukeSession dukeSession = new DukeSession();
        Ingredient ingredient = new Ingredient("apple", 2);
        dukeSession.ingredients.add(ingredient);
        assertEquals(0,findIndex("apple"));
        assertEquals(-1, findIndex("orange"));
    }

    @Test
    public void addNewIngredientAndAddToExistingIngredient() {
        DukeSession dukeSession = new DukeSession();
        new AddCommand("apple", "2").execute(dukeSession);
        assertEquals(2, dukeSession.ingredients.get(0).getQuantity());
        new AddCommand("apple", "3").execute(dukeSession);
        assertEquals(5, dukeSession.ingredients.get(0).getQuantity());
    }
}
