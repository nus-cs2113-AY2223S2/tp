package seedu.commands;

import seedu.exceptions.LifeTrackerException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

public abstract class Command {
    private boolean isExit;

    public Command(){
        setIsExit(false);
    }

    public void setIsExit(boolean exit) {
        this.isExit = exit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(GeneralUi ui , FoodStorage foodStorage,
                                 MealStorage mealStorage, UserStorage userStorage, ExerciseStorage exerciseStorage)
            throws LifeTrackerException;
}
