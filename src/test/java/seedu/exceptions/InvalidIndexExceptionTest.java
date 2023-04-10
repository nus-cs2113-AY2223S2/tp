package seedu.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidIndexExceptionTest {
    @Test
    void createInvalidIndexException_emptyInput_expectCorrectErrorMessage() {
        int index = -1;
        InvalidIndexException error = new InvalidIndexException(index);
        String expectedErrorMessage = index + " is not a valid index!";
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
