package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.exceptions.ExtraArgumentsException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest {
    private ListCommand listCommand;
    private GeneralUi ui = new GeneralUi();
    private final ExerciseStorage exerciseStorage = new ExerciseStorage("./data/exerciseData.csv");
    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final UserStorage userStorage = new UserStorage("./data/userData.csv");

    @Test
    public void listCommand_missingOrExtraOrInvalidArguments_expectExceptionThrown() {
        assertThrows(MissingArgumentsException.class, () -> new ListCommand("list", "list"));
        assertThrows(MissingArgumentsException.class, () -> new ListCommand("list", "list "));
        assertThrows(ExtraArgumentsException.class, () ->
                new ListCommand("list", "list arg1 arg2"));
    }

    @Test
    public void listCommand_invalidArgument_expectExceptionThrown() throws LifeTrackerException {
        listCommand = new ListCommand("list", "list savings");
        assertThrows(LifeTrackerException.class, () ->
                listCommand.execute(ui, foodStorage, mealStorage, userStorage, exerciseStorage));
    }

    @Test
    public void listMeals_validInput_expectNoExceptionsThrown() throws LifeTrackerException {
        listCommand = new ListCommand("list", "list meals");
        assertDoesNotThrow(() -> listCommand.execute(ui, foodStorage, mealStorage, userStorage, exerciseStorage));
    }
}
