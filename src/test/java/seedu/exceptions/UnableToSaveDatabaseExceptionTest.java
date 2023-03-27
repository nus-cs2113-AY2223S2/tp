package seedu.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UnableToSaveDatabaseExceptionTest {
    @Test
    void createUnableToSaveDatabaseException_emptyInput_expectCorrectErrorMessage() {
        String databaseName = "testDatabase";
        UnableToSaveDatabaseException error = new UnableToSaveDatabaseException(databaseName);
        String expectedErrorMessage = "Unable to save " + databaseName;
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
