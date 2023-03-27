package seedu.commands;

import java.io.IOException;

import seedu.exceptions.LifeTrackerException;
import seedu.logger.LogFileHandler;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

public class ExitCommand extends Command{
    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        setIsExit(true);
        ui.printGoodbye();
        LogFileHandler.logInfo("User exited the programme.");
        try {
            mealStorage.write();
            userStorage.write();
        } catch (IOException e) {
            throw new LifeTrackerException("Error saving databases!");
        }
    }
}
