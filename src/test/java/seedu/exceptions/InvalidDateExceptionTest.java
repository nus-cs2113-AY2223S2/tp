package seedu.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.constants.DateConstants;

public class InvalidDateExceptionTest {
    @Test
    void createInvalidDateException_emptyInput_expectCorrectErrorMessage() {
        String dateString = "testDate";
        InvalidDateException error = new InvalidDateException(dateString);
        String expectedErrorMessage = "Oops! " + dateString + " is not a valid date!" + 
                " Please format the date as: " + DateConstants.PARSE_FORMAT;
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
