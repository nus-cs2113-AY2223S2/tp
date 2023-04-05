package seedu.badmaths.trigograph;

import org.junit.jupiter.api.Test;
import seedu.badmaths.IllegalTodoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrigoGraphVisualiserTest {
    @Test
    void eqnWithInvalidTrigoGivesInvalidState() {
        try {
            TrigoGraphVisualiser visualiser = new TrigoGraphVisualiser(1.0, 2.5, 1.67,
                    9, "tann");
            visualiser.startVisualiser();
            assertThrows(IllegalTodoException.class, () -> {
                visualiser.isValidState();
            });
        } catch (IllegalTodoException e) {
            System.out.println("Wrong trigger.");
        }
    }

    @Test
    void eqnWithValidTanGivesValidState() {
        try {
            TrigoGraphVisualiser visualiser_tan = new TrigoGraphVisualiser(1.0, 2.5, 1.67,
                    9, "tan");
            assertEquals(true, visualiser_tan.isValidState());
        } catch (IllegalTodoException e) {
            System.out.println("Wrong trigger.");
        }
    }

    @Test
    void eqnWithValidSinGivesValidState() {
        try {
            TrigoGraphVisualiser visualiser_sin = new TrigoGraphVisualiser(1.0, 2.5, 1.67,
                    9, "sin");
            assertEquals(true, visualiser_sin.isValidState());
        } catch (IllegalTodoException e) {
            System.out.println("Wrong trigger.");
        }
    }
}
