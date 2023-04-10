package seedu.pettracker.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ExitCommandTest {

    @Test
    public void exitBooleanValue_isTrue_success() {
        Command exitCommand = new ExitCommand();
        assertTrue(exitCommand.isExit());
    }
}
