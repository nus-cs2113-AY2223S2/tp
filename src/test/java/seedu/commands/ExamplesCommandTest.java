package seedu.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.exceptions.LifeTrackerException;
import seedu.ui.ExampleUi;


class ExamplesCommandTest {

    @Test
    void printExamples_validInput_expectException() throws LifeTrackerException {
        ExampleUi ui = new ExampleUi();
        ExamplesCommand examplesCommand = new ExamplesCommand("examples", " exercise");
        assertDoesNotThrow(() -> {
            examplesCommand.printExamples("exercise", ui);
        });
    }

    @Test
    void printExamples_invalidInput_expectException() throws LifeTrackerException {
        ExampleUi ui = new ExampleUi();
        ExamplesCommand examplesCommand = new ExamplesCommand("examples", " food");
        LifeTrackerException thrown = assertThrows(LifeTrackerException.class, () -> {
            examplesCommand.printExamples("food", ui);
        });

        String expectedErrorMessage = "You can only input exercise/meal for this command!";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }
}
