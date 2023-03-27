package seedu.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidCommandExceptionTest {
    @Test
    void createInvalidCommandException_emptyInput_expectCorrectErrorMessage() {
        InvalidCommandException error = new InvalidCommandException();
        String expectedErrorMessage = "Please enter a valid command.";
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
