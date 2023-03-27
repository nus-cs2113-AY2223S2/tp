package seedu.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.definitions.MealTypes;

public class InvalidMealExceptionTest {
    @Test
    void createInvalidMealException_emptyInput_expectCorrectErrorMessage() {
        String input = "testMealType";
        InvalidMealException error = new InvalidMealException(input);
        String expectedErrorMessage = System.lineSeparator() + "Invalid meal type: " + input +
                "! Supported meal types: " + MealTypes.getSupportedMealTypes();
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
