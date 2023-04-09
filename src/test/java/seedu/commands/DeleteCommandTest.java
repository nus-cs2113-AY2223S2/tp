package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.constants.DateConstants;
import seedu.definitions.MealTypes;
import seedu.entities.Exercise;
import seedu.entities.Meal;
import seedu.exceptions.InvalidIndexException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;


class DeleteCommandTest {
    private DeleteCommand deleteCommand;
    private final ExerciseStorage exerciseStorage = new ExerciseStorage("./data/exerciseData.csv");
    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final UserStorage userStorage = new UserStorage("./data/userData.csv");

    @Test
    void deleteMeal_validMealToDelete_expectNoExceptions() throws LifeTrackerException {
        GeneralUi ui = new GeneralUi();
        String testDateInput = "01/01/2023";
        LocalDate testDate = LocalDate.parse(testDateInput, DateConstants.PARSE_DTF);
        Meal meal = new Meal(foodStorage.getFoods(), testDate, MealTypes.LUNCH);
        mealStorage.saveMeal(meal);
        deleteCommand = new DeleteCommand("delete", "delete /meal 1");
        deleteCommand.execute(ui, foodStorage, mealStorage, userStorage, exerciseStorage);
        assertDoesNotThrow(() -> {
            deleteCommand.execute(ui, null, mealStorage, null, exerciseStorage);
        });
        assertFalse(mealStorage.getMeals().contains(meal));
    }

    @Test
    void deleteExercise_validExerciseToDelete_expectNoExceptions() throws LifeTrackerException {
        GeneralUi ui = new GeneralUi();
        String testDateInput = "01/01/2023";
        LocalDate testDate = LocalDate.parse(testDateInput, DateConstants.PARSE_DTF);
        Exercise exercise = new Exercise("run", "3km", 800, testDate);
        exerciseStorage.saveExercise(exercise);
        deleteCommand = new DeleteCommand("delete", "delete /exercise 1");
        deleteCommand.execute(ui, foodStorage, mealStorage, userStorage, exerciseStorage);
        assertDoesNotThrow(() -> {
            deleteCommand.execute(ui, null, mealStorage, null, exerciseStorage);
        });
        assertFalse(exerciseStorage.getExercises().contains(exercise));
    }

    @Test
    void deleteExercise_missingIndex_expectMissingArgumentsException() throws LifeTrackerException {
        GeneralUi ui = new GeneralUi();
        String commandWord = "delete";
        String userInput = "delete";
        MissingArgumentsException thrown = assertThrows(MissingArgumentsException.class, () -> {
            DeleteCommand command = new DeleteCommand(commandWord, userInput);
            command.execute(ui, null, mealStorage, null, exerciseStorage);
        });
        String expectedErrorMessage = "Oops! Missing argument [/meal, /exercise] for command delete";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test
    void deleteMeal_missingType_expectInvalidIndexException() throws LifeTrackerException {
        GeneralUi ui = new GeneralUi();
        int currentMealsSize = mealStorage.getMealCount();
        int testCase = currentMealsSize + 1;
        String commandWord = "delete";
        String userInput = "delete /meal " + testCase;
        InvalidIndexException thrown = assertThrows(InvalidIndexException.class, () -> {
            DeleteCommand command = new DeleteCommand(commandWord, userInput);
            command.execute(ui, null, mealStorage, null, exerciseStorage);
        });
        String expectedErrorMessage = testCase + " is not a valid index!";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }
}
