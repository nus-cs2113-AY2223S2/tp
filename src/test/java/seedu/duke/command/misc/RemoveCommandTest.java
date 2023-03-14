package seedu.duke.command.misc;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeSession;
import seedu.duke.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class RemoveCommandTest {
    @Test
    public void removeSameNumberAsQuantity() {
        DukeSession dukeSession = new DukeSession();
        Ingredient ingredient = new Ingredient("apple", 2.0);
        dukeSession.getIngredients().add(ingredient);
        assertEquals(1, dukeSession.getIngredients().size());
        new RemoveCommand("apple", "2").execute(dukeSession);
        assertEquals(0, dukeSession.getIngredients().size());
    }

    @Test
    public void removeLesserThanFridgeQuantity() {
        DukeSession dukeSession = new DukeSession();
        Ingredient ingredient = new Ingredient("apple", 9.0);
        dukeSession.getIngredients().add(ingredient);
        assertEquals(9, dukeSession.getIngredients().get(0).getQuantity());
        new RemoveCommand("apple", "2").execute(dukeSession);
        assertEquals(7, dukeSession.getIngredients().get(0).getQuantity());
        new RemoveCommand("apple", "4").execute(dukeSession);
        assertEquals(3, dukeSession.getIngredients().get(0).getQuantity());
    }
}
