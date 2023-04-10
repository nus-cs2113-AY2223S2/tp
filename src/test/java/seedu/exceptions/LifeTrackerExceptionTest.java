package seedu.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LifeTrackerExceptionTest {
    @Test
    void createLifeTrackerException_emptyInput_expectCorrectErrorMessage() {
        String errorMessage = "testErrorMessage";
        LifeTrackerException error = new LifeTrackerException(errorMessage);
        String expectedErrorMessage = errorMessage;
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
