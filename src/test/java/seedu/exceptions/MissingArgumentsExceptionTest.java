package seedu.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MissingArgumentsExceptionTest {
    @Test
    void createMissingArgumentsException_emptyInput_expectCorrectErrorMessage() {
        String argument = "/testArgument";
        String commandWord = "testCommand";
        MissingArgumentsException error = new MissingArgumentsException(commandWord, argument);
        String expectedErrorMessage = "Oops! Missing argument " + argument + " for command " + commandWord;
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
