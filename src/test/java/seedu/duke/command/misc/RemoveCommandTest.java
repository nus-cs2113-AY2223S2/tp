package seedu.duke.command.misc;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeSession;
import seedu.duke.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class RemoveCommandTest {
    @Test
    public void removeSameNumberAsQuantity() {
        DukeSession dukeSession = new DukeSession();
        Ingredient ingredient = new Ingredient("apple", 2);
        dukeSession.ingredients.add(ingredient);
        assertEquals(DukeSession.ingredients.size(), 1);
        new RemoveCommand("apple", "2").execute(dukeSession);
        assertEquals(DukeSession.ingredients.size(), 0);
    }

    @Test
    public void removeLesserThanFridgeQuantity() {
        DukeSession dukeSession = new DukeSession();
        Ingredient ingredient = new Ingredient("apple", 9);
        dukeSession.ingredients.add(ingredient);
        assertEquals(dukeSession.ingredients.get(0).getQuantity(), 9);
        new RemoveCommand("apple", "2").execute(dukeSession);
        assertEquals(dukeSession.ingredients.get(0).getQuantity(), 7);
        new RemoveCommand("apple", "4").execute(dukeSession);
        assertEquals(dukeSession.ingredients.get(0).getQuantity(), 3);
    }
}
