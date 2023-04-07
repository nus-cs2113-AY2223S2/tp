package seedu.commands;

import seedu.constants.DateConstants;
import seedu.exceptions.InvalidDateException;
import seedu.exceptions.InvalidArgumentsException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.ExerciseStorage;

import org.junit.jupiter.api.Test;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.ui.GeneralUi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class AddExerciseCommandTest {
    private final ExerciseStorage exerciseStorage = new ExerciseStorage("./data/exerciseData.csv");
    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final GeneralUi ui = new GeneralUi();

    @Test
    void addExercise_invalidDateIncluded_expectInvalidDateException() {
        String commandWord = "exercise";
        String userInput = "exercise /type Running /description 5km /calories 400 /on 3/13/2023";
        AddExerciseCommand command = new AddExerciseCommand(commandWord, userInput);
        InvalidDateException thrown = assertThrows(InvalidDateException.class, () -> {
            command.execute(ui, null, null, null, exerciseStorage);
        });
        String expectedErrorMessage = "Oops! 3/13/2023 is not a valid date!" +
                " Please format the date as: " + DateConstants.PARSE_FORMAT + "." +
                    "\n" + "Also check whether the date you've entered exists!";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test
    void addExercise_invalidCalorieIncluded_expectInvalidArgumentsException() {
        String commandWord = "exercise";
        String userInput = "exercise /type Running /description 5km /calories wronginput /on 3/13/2023";
        AddExerciseCommand command = new AddExerciseCommand(commandWord, userInput);
        InvalidArgumentsException thrown = assertThrows(InvalidArgumentsException.class, () -> {
            command.execute(null, null, null, null, exerciseStorage);
        });
        String expectedErrorMessage = "Error: Invalid arguments for /calories for command exercise";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test
    void addExercise_validExerciseAdd_expectNoExceptions() {
        String commandWord = "exercise";
        String userInput = "exercise /type Running /description 5km /calories 400 /on 3/1/2023";
        AddExerciseCommand command = new AddExerciseCommand(commandWord, userInput);
        assertDoesNotThrow(() -> {
            command.execute(ui, null, mealStorage, null, exerciseStorage);
        });
    }

    @Test 
    void addExercise_missingType_expectMissingArgumentsException() {
        String commandWord = "exercise";
        String userInput = "exercise /description 5km /calories 400 /on 3/13/2023";
        AddExerciseCommand command = new AddExerciseCommand(commandWord, userInput);
        MissingArgumentsException thrown = assertThrows(MissingArgumentsException.class, () -> {
            command.execute(null, null, null, null, exerciseStorage);
        });
        String expectedErrorMessage = "Oops! Missing argument /type for command exercise";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test 
    void addExercise_missingDescription_expectMissingArgumentsException() {
        String commandWord = "exercise";
        String userInput = "exercise /type Running /calories 400 /on 3/1/2023";
        AddExerciseCommand command = new AddExerciseCommand(commandWord, userInput);
        MissingArgumentsException thrown = assertThrows(MissingArgumentsException.class, () -> {
            command.execute(null, null, null, null, exerciseStorage);
        });
        String expectedErrorMessage = "Oops! Missing argument /description for command exercise";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test 
    void addExercise_missingCalories_expectMissingArgumentsException() {
        String commandWord = "exercise";
        String userInput = "exercise /type Running /description 5km /on 3/1/2023";
        AddExerciseCommand command = new AddExerciseCommand(commandWord, userInput);
        MissingArgumentsException thrown = assertThrows(MissingArgumentsException.class, () -> {
            command.execute(null, null, null, null, exerciseStorage);
        });
        String expectedErrorMessage = "Oops! Missing argument /calories for command exercise";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test 
    void addExercise_missingDate_expectMissingArgumentsException() {
        String commandWord = "exercise";
        String userInput = "exercise /type Running /description 5km /calories 400";
        AddExerciseCommand command = new AddExerciseCommand(commandWord, userInput);
        MissingArgumentsException thrown = assertThrows(MissingArgumentsException.class, () -> {
            command.execute(null, null, null, null, exerciseStorage);
        });
        String expectedErrorMessage = "Oops! Missing argument /on for command exercise";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }
}
