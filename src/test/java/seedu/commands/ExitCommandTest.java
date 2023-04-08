package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exceptions.LifeTrackerException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExitCommandTest {
    private ExitCommand exitCommand;
    private final ExerciseStorage exerciseStorage = new ExerciseStorage("./data/exerciseData.csv");
    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final UserStorage userStorage = new UserStorage("./data/userData.csv");

    @BeforeEach
    public void setUp() {
        exitCommand = new ExitCommand();
    }

    @Test
    public void testExecute() throws LifeTrackerException {
        GeneralUi ui = new GeneralUi();
        try {
            exitCommand.execute(ui,foodStorage, mealStorage, userStorage, exerciseStorage);
            assertTrue(exitCommand.isExit());
        } catch (LifeTrackerException e) {
            throw new LifeTrackerException("Unexpected exception thrown.");
        }
    }
}
