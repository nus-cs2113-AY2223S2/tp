package seedu.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidArgumentsExceptionTest {
    @Test
    void createInvalidArgumentsException_emptyInput_expectCorrectErrorMessage() {
        InvalidArgumentsException error = new InvalidArgumentsException(
                "testCommand", "/testArgument");
        String expectedErrorMessage = "Error: Invalid arguments for /testArgument for command testCommand";
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
