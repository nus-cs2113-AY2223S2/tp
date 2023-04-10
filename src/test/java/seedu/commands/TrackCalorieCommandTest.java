package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidDateException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TrackCalorieCommandTest {
    private TrackCalorieCommand trackCalorieCommand;
    private final ExerciseStorage exerciseStorage = new ExerciseStorage("./data/exerciseData.csv");
    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final UserStorage userStorage = new UserStorage("./data/userData.csv");

    @Test
    public void trackCalories_validOneLineInput_shouldNotThrowException() {
        GeneralUi ui = new GeneralUi();
        String commandWord = "track";
        String userInput = "track /start 01/01/2022 /end 10/01/2022";
        trackCalorieCommand = new TrackCalorieCommand(commandWord, userInput);
        assertDoesNotThrow(() -> {
            trackCalorieCommand.execute(ui, foodStorage, mealStorage, userStorage, exerciseStorage);
        });
    }

    @Test
    public void trackCalories_validDisplayAllInput_shouldNotThrowException() {
        GeneralUi ui = new GeneralUi();
        String commandWord = "track";
        String userInput = "track all";
        trackCalorieCommand = new TrackCalorieCommand(commandWord, userInput);
        assertDoesNotThrow(() -> {
            trackCalorieCommand.execute(ui, foodStorage, mealStorage, userStorage, exerciseStorage);
        });
    }

    @Test
    public void trackCalories_missingArguments_expectMissingArgumentsException() {
        GeneralUi ui = new GeneralUi();
        TrackCalorieCommand command = new TrackCalorieCommand("track", "track /start 10/01/2022");
        MissingArgumentsException thrown = assertThrows(MissingArgumentsException.class, () -> {
            command.execute(ui, foodStorage, mealStorage, userStorage, exerciseStorage);
        });
        String expectedErrorMessage = "Oops! Missing argument /end for command track";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test
    public void trackCalories_invalidDate_expectInvalidDateException() {
        GeneralUi ui = new GeneralUi();
        TrackCalorieCommand command = new TrackCalorieCommand("track",
                "track /start 10/01/2022 /end 10/13/2022");
        InvalidDateException thrown = assertThrows(InvalidDateException.class, () -> {
            command.execute(ui, foodStorage, mealStorage, userStorage, exerciseStorage);
        });
        String expectedErrorMessage = "Oops! 10/13/2022 is not a valid date! Please format the date as: d/M/yyyy.\n" +
                "Also check whether the date you've entered exists!";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

}
