package seedu.duke.command.misc;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeSession;
import seedu.duke.Ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.command.misc.AddCommand.isInList;

class AddCommandTest {
    @Test
    public void itemIsInList() {
        DukeSession dukeSession = new DukeSession();
        Ingredient ingredient = new Ingredient("apple", 2);
        DukeSession.Ingredients.add(ingredient);
        assertTrue(isInList("apple"));
    }

    @Test
    public void ItemIsNotInList() {
        DukeSession dukeSession = new DukeSession();
        Ingredient ingredient = new Ingredient("orange", 2);
        DukeSession.Ingredients.add(ingredient);
        assertFalse(isInList("apple"));
    }

}
