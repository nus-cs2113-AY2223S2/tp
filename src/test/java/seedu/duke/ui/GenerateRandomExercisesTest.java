package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.GenerateRandomCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GenerateRandomExercisesTest {
    /**
     * Checks if user input is of the correct format
     */
    @Test

    void testInputNumber(){

        assertEquals("Please key in a valid numeric input!" , new GenerateRandomCommand("a"));
        assertEquals("Your generate description cannot be empty!" , new GenerateRandomCommand(""));
    }
}
