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
        assertEquals(1, DukeSession.ingredients.size());
        new RemoveCommand("apple", "2").execute(dukeSession);
        assertEquals(0, DukeSession.ingredients.size());
    }

    @Test
    public void removeLesserThanFridgeQuantity() {
        DukeSession dukeSession = new DukeSession();
        Ingredient ingredient = new Ingredient("apple", 9);
        dukeSession.ingredients.add(ingredient);
        assertEquals(9, dukeSession.ingredients.get(0).getQuantity());
        new RemoveCommand("apple", "2").execute(dukeSession);
        assertEquals(7, dukeSession.ingredients.get(0).getQuantity());
        new RemoveCommand("apple", "4").execute(dukeSession);
        assertEquals(3, dukeSession.ingredients.get(0).getQuantity());
    }
}
