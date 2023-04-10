package seedu.commands;

import seedu.exceptions.LifeTrackerException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

/**
 * Superclass for commands
 */
public abstract class Command {
    private boolean isExit;

    public Command(){
        setIsExit(false);
    }

    /**
     * For exit command
     * @param exit
     */
    public void setIsExit(boolean exit) {
        this.isExit = exit;
    }

    /**
     * To determine whether to exit LifeTracker
     * @return true if exiting application, false otherwise
     */
    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(GeneralUi ui , FoodStorage foodStorage,
                                 MealStorage mealStorage, UserStorage userStorage, ExerciseStorage exerciseStorage)
            throws LifeTrackerException;
}
