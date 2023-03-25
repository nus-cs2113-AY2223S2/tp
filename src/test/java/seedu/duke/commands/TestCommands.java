package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.logic.commands.GenerateFilterCommand;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * This class test the handling of commands and checks if commands throw appropriate errors.
 */
public class TestCommands {
    /**
     * Tests the GenerateFilterCommand and checks if command have a valid argument to number of exercises in the user
     * input. If number of exercise is not present in user input, an exception should be thrown, none otherwise.
     */
    @Test
    public void testUserFilterCommand () {

        String[] invalidCommands = {"this is an invalid command", "this is another invalid command"};
        assertThrows(DukeError.class, () -> {
            new GenerateFilterCommand(invalidCommands);
        });
        String[] validCommands = {"body", "3"};
        assertDoesNotThrow(() -> {
            new GenerateFilterCommand(validCommands);
        });
    }

    /**
     * Tests the executeCommand of GenerateFilterCommand and checks if command can execute and throw the appropriate
     * error such as when an event that the user inputs an invalid filter argument.
     */
    @Test
    public void testExecuteFilterCommand () {
        String[] invalidCommands = {"An", "invalid", "command", "3"};
        Ui ui = new Ui();
        GenerateExercise generateExercise = new GenerateExercise();
        assertThrows(DukeError.class, () -> {
            GenerateFilterCommand generateFilterCommand = new GenerateFilterCommand(invalidCommands);
            generateFilterCommand.executeCommand(ui, generateExercise);
        });
        String[] validCommands = {"easy", "upper", "3"};
        assertDoesNotThrow(() -> {
            GenerateFilterCommand generateFilterCommand = new GenerateFilterCommand(validCommands);
            generateFilterCommand.executeCommand(ui, generateExercise);
        });
    }

}
